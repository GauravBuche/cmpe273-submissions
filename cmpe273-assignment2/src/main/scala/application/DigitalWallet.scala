package application

import org.springframework.context.annotation.Configuration
import resources.BankDetails
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.filter.ShallowEtagHeaderFilter
import org.springframework.boot._
import org.springframework.boot.autoconfigure._
import org.springframework.stereotype._
import org.springframework.web.bind.annotation._
import java.util.ArrayList
import scala.collection.JavaConversions._
import resources.User
import resources.IdCard
import resources.WebLogin
import resources.BankAccount
import resources.IdCardSequence
import resources.WebLoginIdSequence
import resources.BankAccountIdSequence
import java.util.HashMap
import org.springframework.http._
import java.util.Calendar
import org.joda.time._
import javax.validation.Valid
import java.util.ArrayList
import java.util.List
import serializers.CustomIdCardDateSerializer
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.springframework.boot.context.embedded.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.web.filter.ShallowEtagHeaderFilter
import serializers.CustomUserIdPutSerializer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration
import com.mongodb.MongoClient
import com.mongodb.Connection
import com.mongodb.DB
import com.mongodb.DBCollection
import com.mongodb.BasicDBObject
import com.mongodb.DBObject
import com.mongodb.DBCursor
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.data.mongodb.core.MongoOperations
import dbconfiguration.MongoDBConfiguration
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.core.query.BasicQuery
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.MongoActionOperation
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import java.nio.charset.Charset
import com.mongodb.util.JSONParser
import com.fasterxml.jackson.databind.ObjectMapper


@RestController
@EnableAutoConfiguration
@EnableMongoRepositories
@Configuration
@RequestMapping(Array("/api/v1/*"))
class DigitalWallet {

	val seq = new Sequence()
	val idcard_seq = new IdCardSequence()
	val webLogin_seq = new WebLoginIdSequence()	 
	val baid_seq = new BankAccountIdSequence()

	val ctx = new AnnotationConfigApplicationContext(classOf[MongoDBConfiguration])
	val mongoOperation = ctx.getBean("mongoTemplate").asInstanceOf[MongoOperations]

			@Bean
			def shallowEtagHeaderFilter() : FilterRegistrationBean = {

					var etagHeaderFilter =  new ShallowEtagHeaderFilter()
					var etagBean = new FilterRegistrationBean()
					etagBean.setFilter(etagHeaderFilter)
					var urls = new ArrayList [String] ()
					urls.add("/api/v1/users/*")
					etagBean.setUrlPatterns(urls)

					return etagBean

			}




			@ResponseStatus(HttpStatus.CREATED)
			@RequestMapping(value = Array("/users"), method = Array(RequestMethod.POST))
			@ResponseBody def createUser(@RequestBody @Valid user:User): User = {

					val currentDateTime = DateTime.now()
							val userId = seq.getNextInt()
							seq.setPreviousInt()
							user.setUser_id(userId)	
							user.setCreated_at(currentDateTime)
							user.setUpdated_at(currentDateTime)

							mongoOperation.save(user,"user")

							val query = new Query()
					query.addCriteria(Criteria.where("user_id").is(userId))

					return mongoOperation.findOne(query,classOf[User])

			}


			@RequestMapping(value = Array("/map"), method = Array(RequestMethod.GET))
			@ResponseBody def getAllUsers(): DBCollection = {

					val mongoClient = new MongoClient("localhost", 27017)

					val db = mongoClient.getDB("Assignment2DB")

					return db.getCollection("user")

			}

			@ResponseStatus(HttpStatus.OK)
			@RequestMapping(value = Array("/users/{user_id}"), method = Array(RequestMethod.GET))        
			@ResponseBody def getUser(@PathVariable user_id:String): User = {  	

					return getUserUsingUserID(user_id)
			}

			@ResponseStatus(HttpStatus.CREATED)
			@RequestMapping(value = Array("/users/{user_id}"), method = Array(RequestMethod.PUT))        
			@ResponseBody def updateUser(@PathVariable user_id:String, @RequestBody @Valid user:User): User= {  	

					val query = new Query()
					query.addCriteria(Criteria.where("user_id").is(new Integer(user_id.substring(2))))
					//query.fields().include("name")

					val userTest3 = mongoOperation.findOne(query,classOf[User])

					val update = new Update()
					update.set("email", user.getEmail)
					update.set("password", user.getPassword)
					update.set("updated_at", DateTime.now())

					mongoOperation.updateFirst(query, update, classOf[User])
					return getUserUsingUserID(user_id)

			}	

			@ResponseStatus(HttpStatus.CREATED)
			@RequestMapping(value = Array("/users/{user_id}/idcards"), method = Array(RequestMethod.POST))
			@ResponseBody def addIdCard(@PathVariable user_id:String,@RequestBody @Valid idcard:IdCard): IdCard = {


					val cardId = idcard_seq.getNextIdCardNumber()
							idcard_seq.setPreviousIdCardNumber()
							idcard.setCard_id(cardId)			
							mongoOperation.save(idcard,"idcard")
							val update = new Update()

					update.set("user_id", user_id.substring(2).toInt)
					val query_AddIdCardForUser = new Query()
					query_AddIdCardForUser.addCriteria(Criteria.where("card_id").is(cardId))
					mongoOperation.updateFirst(query_AddIdCardForUser, update, classOf[IdCard])

					return idcard

			}


			@ResponseStatus(HttpStatus.OK)
			@RequestMapping(value = Array("/users/{user_id}/idcards"), method = Array(RequestMethod.GET))	        
			@ResponseBody def getIdCards(@PathVariable user_id:String): List[IdCard] = {

					val query = new Query()
					query.addCriteria(Criteria.where("user_id").is(user_id.substring(2).toInt))
					return mongoOperation.find(query, classOf[IdCard])

			}


			@ResponseStatus(HttpStatus.NO_CONTENT)
			@RequestMapping(value = Array("/users/{user_id}/idcards/{id_card}"), method = Array(RequestMethod.DELETE))
			@ResponseBody def deleteIdCard(@PathVariable user_id:String,@PathVariable id_card:String): List[IdCard]= {


					val query = new Query()
					query.addCriteria(Criteria.where("card_id").is(id_card.substring(2).toInt))

					mongoOperation.remove(query, classOf[IdCard])	

					return getAllIdCards(user_id)

			}


			@ResponseStatus(HttpStatus.CREATED)
			@RequestMapping(value = Array("/users/{user_id}/weblogins"), method = Array(RequestMethod.POST))
			@ResponseBody def addWebLogin(@PathVariable user_id:String,@RequestBody @Valid weblogin:WebLogin): WebLogin = {

					val login_id = webLogin_seq.getNextWebLoginId()
							webLogin_seq.setPreviousWebLoginId()
							weblogin.setLogin_id(login_id)			
							mongoOperation.save(weblogin,"weblogin")
							val update = new Update()

					update.set("user_id", user_id.substring(2).toInt)
					val query_AddIdCardForUser = new Query()
					query_AddIdCardForUser.addCriteria(Criteria.where("login_id").is(login_id))
					mongoOperation.updateFirst(query_AddIdCardForUser, update, classOf[WebLogin])

					return weblogin


			}


			@ResponseStatus(HttpStatus.OK)
			@RequestMapping(value = Array("/users/{user_id}/weblogins"), method = Array(RequestMethod.GET))
			@ResponseBody def getWebLogins(@PathVariable user_id:String): List[WebLogin] = {

					return getAllWebLogins(user_id) 
			}




			@ResponseStatus(HttpStatus.NO_CONTENT)
			@RequestMapping(value = Array("/users/{user_id}/weblogins/{login_id}"), method = Array(RequestMethod.DELETE))
			@ResponseBody def deleteWebLogin(@PathVariable user_id:String,@PathVariable login_id:String): List[WebLogin]= {


					val query = new Query()
					query.addCriteria(Criteria.where("login_id").is(login_id.substring(2).toInt))

					mongoOperation.remove(query, classOf[WebLogin])	

					return getAllWebLogins(user_id)

			}


			@ResponseStatus(HttpStatus.CREATED)
			@RequestMapping(value = Array("/users/{user_id}/bankaccounts"), method = Array(RequestMethod.POST))
			@ResponseBody def addBankAccount(@PathVariable user_id:String,@RequestBody @Valid bankaccount:BankAccount): BankAccount = {


					val ba_id = baid_seq.getNextBankAccountId()
							baid_seq.setPreviousBankAccountId()
							bankaccount.setBa_id(ba_id)			
							mongoOperation.save(bankaccount,"bankaccount")
							val update = new Update()

					update.set("user_id", user_id.substring(2).toInt)

					val restTemplate = new RestTemplate()

					restTemplate.getMessageConverters().add(new StringHttpMessageConverter())
					restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter())

					var url = "http://www.routingnumbers.info/api/data.json?rn="+bankaccount.getRouting_number

					val bankDetails = restTemplate.getForObject(url, classOf[String], "customer_name")

					val mapper = new ObjectMapper()
					val map = mapper.readValue(bankDetails, classOf[BankDetails])
					if(map.getCustomer_name!=null){
						bankaccount.setAccount_name(map.getCustomer_name)
						update.set("account_name", bankaccount.getAccount_name)	
					}


					val query_AddIdCardForUser = new Query()
					query_AddIdCardForUser.addCriteria(Criteria.where("ba_id").is(ba_id))
					mongoOperation.updateFirst(query_AddIdCardForUser, update, classOf[BankAccount])

					return bankaccount	  
			}


			@ResponseStatus(HttpStatus.OK)
			@RequestMapping(value = Array("/users/{user_id}/bankaccounts"), method = Array(RequestMethod.GET))
			@ResponseBody def getBankAccount(@PathVariable user_id:String): List[BankAccount] = {

					return getAllBankAccounts(user_id)  
			}


			@ResponseStatus(HttpStatus.NO_CONTENT)
			@RequestMapping(value = Array("/users/{user_id}/bankaccounts/{ba_id}"), method = Array(RequestMethod.DELETE))
			@ResponseBody def deleteBankAccountId(@PathVariable user_id:String,@PathVariable ba_id:String): List[BankAccount]= {

					val query = new Query()
					query.addCriteria(Criteria.where("ba_id").is(ba_id.substring(2).toInt))

					mongoOperation.remove(query, classOf[BankAccount])	

					return getAllBankAccounts(user_id) 	

			}

			def getUserUsingUserID(user_id: String): User ={
					val query = new Query()
					query.addCriteria(Criteria.where("user_id").is(new Integer(user_id.substring(2))))
					return mongoOperation.findOne(query,classOf[User])
			}

			def getAllIdCards(user_id: String): List[IdCard]={
					val query = new Query()
					query.addCriteria(Criteria.where("user_id").is(new Integer(user_id.substring(2))))
					return mongoOperation.find(query,classOf[IdCard])
			}

			def getAllWebLogins(user_id: String): List[WebLogin]={
					val query = new Query()
					query.addCriteria(Criteria.where("user_id").is(new Integer(user_id.substring(2))))
					return mongoOperation.find(query,classOf[WebLogin])
			}

			def getAllBankAccounts(user_id: String): List[BankAccount]={
					val query = new Query()
					query.addCriteria(Criteria.where("user_id").is(new Integer(user_id.substring(2))))
					return mongoOperation.find(query,classOf[BankAccount])
			}

}
