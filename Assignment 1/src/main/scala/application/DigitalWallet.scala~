package application

import org.springframework.context.annotation.Configuration
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
import org.joda.time._;
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
	
@RestController
@EnableAutoConfiguration
@RequestMapping(Array("/api/v1/*"))
class DigitalWallet {

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
	
        val seq = new Sequence()
        val idcard_seq = new IdCardSequence()
	val webLogin_seq = new WebLoginIdSequence()	
        val baid_seq = new BankAccountIdSequence()
    
	

	@ResponseStatus(HttpStatus.CREATED)
  	@RequestMapping(value = Array("/users"), method = Array(RequestMethod.POST))
        @ResponseBody def createUser(@RequestBody @Valid user:User): User = {
          	
            
          //val dateFormat = new SimpleDateFormat()
          
	  val currentDateTime = DateTime.now()
	  val userId = seq.getNextInt()
	  user.setUser_id(userId)	
	  user.setCreated_at(currentDateTime)
	  user.setUpdated_at(currentDateTime)	
          DigitalWalletApplication.userHashMap.put(userId,user)	
	  seq.setPreviousInt()
          
          return user
          
        }
	
	
        @RequestMapping(value = Array("/map"), method = Array(RequestMethod.GET))
        @ResponseBody def getAllUsers(): HashMap[Int,User] = {
        
          return DigitalWalletApplication.userHashMap
          
        }

	@ResponseStatus(HttpStatus.OK)
  	@RequestMapping(value = Array("/users/{user_id}"), method = Array(RequestMethod.GET))        
        @ResponseBody def getUser(@PathVariable user_id:String): User = {  	
	  	
          val temp = user_id.substring(2) 	 
          val int_userId = temp.toInt
          val user = DigitalWalletApplication.userHashMap.get(int_userId)	
	  //seq.setPreviousInt()
          
          return user
          
        }
	
	@ResponseStatus(HttpStatus.CREATED)
  	@RequestMapping(value = Array("/users/{user_id}"), method = Array(RequestMethod.PUT))        
        @ResponseBody def updateUser(@PathVariable user_id:String, @RequestBody @Valid user:User): User = {  	
	  	
          //val user = DigitalWalletApplication.userHashMap.get(user_id)	
	  //user.setEmail(updated_user.getEmail())
	  //user.setPassword(updated_user.getPassword())	
	  //user.setName(updated_user.getName())
	  //user.setUpdated(updated_user.getCreated_at())
	  //seq.setPreviousInt()
                  
	  val currentDateTime = DateTime.now()

	  val temp = user_id.substring(2) 	 
          val int_userId = temp.toInt
	  user.setUser_id(int_userId)	  	
	  user.setCreated_at(currentDateTime)
	  user.setUpdated_at(currentDateTime)
          DigitalWalletApplication.userHashMap.put(int_userId,user)

          return user
          
        }	

	@ResponseStatus(HttpStatus.CREATED)
  	@RequestMapping(value = Array("/users/{user_id}/idcards"), method = Array(RequestMethod.POST))
        @ResponseBody def addIdCard(@PathVariable user_id:Int,@RequestBody @Valid idcard:IdCard): IdCard = {
          	
            
          //Generate and Set IdCardNumber
          val cardId = idcard_seq.getNextIdCardNumber()
	  idcard_seq.setPreviousIdCardNumber()
          idcard.setCard_id(cardId)
	  
	  val existingHashMap = DigitalWalletApplication.idcardHashMap.get(user_id)
	  
	  //Inner HashMap	
	  if(existingHashMap != null){
		existingHashMap.put(cardId,idcard)	
	  }	
          else{
		val newIdCardHashMap = new HashMap[Int,IdCard]
          	newIdCardHashMap.put(cardId,idcard)
		 //Insert IDcard of user in IDCardHashmap
		DigitalWalletApplication.idcardHashMap.put(user_id,newIdCardHashMap)
	  }	
          
          return idcard
          
        }


	@ResponseStatus(HttpStatus.OK)
  	@RequestMapping(value = Array("/users/{user_id}/idcards"), method = Array(RequestMethod.GET))	        
	//@ResponseBody def getIdCards(@PathVariable user_id:Int): HashMap[Int,IdCard] = {
	@ResponseBody def getIdCards(@PathVariable user_id:Int): ArrayList[IdCard] = {

	
		//return DigitalWalletApplication.idcardHashMap.get(user_id)
  
/*		val userIdCardHashMap = DigitalWalletApplication.idcardHashMap.get(user_id)
		val keyList = new ArrayList[IdCard]()
                Iterator itr = userIdCardHashMap.entryset().iterator()
                while(itr.hasNext){
			keyList.add(itr.next())	
		}
	  	return keyList  */

  /*		val map = DigitalWalletApplication.idcardHashMap.get(user_id)
		val keyList = new ArrayList[IdCard]()
		val iterator = map.entrySet().iterator()
   		while (iterator.hasNext) {
			val entry = iterator.next()
      			keyList.add(entry.getValue)
		}

		return keyList    */

		val allIdCards = getAllIdCards(user_id)
		return allIdCards
  	}

	
	@ResponseStatus(HttpStatus.NO_CONTENT)
  	@RequestMapping(value = Array("/users/{user_id}/idcards/{id_card}"), method = Array(RequestMethod.DELETE))
        //@ResponseBody def deleteIdCard(@PathVariable user_id:Int,@PathVariable id_card:Int): HashMap[Int,HashMap[Int,IdCard]]= {
	@ResponseBody def deleteIdCard(@PathVariable user_id:Int,@PathVariable id_card:Int): ArrayList[IdCard]= {
	
		val existingHashMap = DigitalWalletApplication.idcardHashMap.get(user_id)
		existingHashMap.remove(id_card)	
		
		//return DigitalWalletApplication.idcardHashMap	
                return getAllIdCards(user_id)
	  
  	}
	

	@ResponseStatus(HttpStatus.CREATED)
  	@RequestMapping(value = Array("/users/{user_id}/weblogins"), method = Array(RequestMethod.POST))
        @ResponseBody def addWebLogin(@PathVariable user_id:Int,@RequestBody @Valid weblogin:WebLogin): WebLogin = {
          	
            
          //Generate and Set IdCardNumber
          val login_id = webLogin_seq.getNextWebLoginId()
	  webLogin_seq.setPreviousWebLoginId()
          weblogin.setLogin_id(login_id)
	  
	  val existingHashMap = DigitalWalletApplication.webloginHashMap.get(user_id)
	  
	  //Inner HashMap	
	  if(existingHashMap != null){
		existingHashMap.put(login_id,weblogin)	
	  }	
          else{
		val newIdCardHashMap = new HashMap[Int,WebLogin]
          	newIdCardHashMap.put(login_id,weblogin)
		//Insert IDcard of user in IDCardHashmap
		DigitalWalletApplication.webloginHashMap.put(user_id,newIdCardHashMap)
	  }	
          
          return weblogin
          
        }
	

	@ResponseStatus(HttpStatus.OK)
  	@RequestMapping(value = Array("/users/{user_id}/weblogins"), method = Array(RequestMethod.GET))
       // @ResponseBody def getWebLogins(@PathVariable user_id:Int): HashMap[Int,WebLogin] = {
	@ResponseBody def getWebLogins(@PathVariable user_id:Int): ArrayList[WebLogin] = {
	
	
		return getAllWebLogins(user_id)	  
  	}
	

	
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
  	@RequestMapping(value = Array("/users/{user_id}/weblogins/{login_id}"), method = Array(RequestMethod.DELETE))
        @ResponseBody def deleteWebLogin(@PathVariable user_id:Int,@PathVariable login_id:Int): HashMap[Int,HashMap[Int,IdCard]]= {
	
	
		val existingHashMap = DigitalWalletApplication.webloginHashMap.get(user_id)
		existingHashMap.remove(login_id)	
		
		return DigitalWalletApplication.idcardHashMap	
	  
  	}
	

	@ResponseStatus(HttpStatus.CREATED)
  	@RequestMapping(value = Array("/users/{user_id}/bankaccounts"), method = Array(RequestMethod.POST))
        @ResponseBody def addBankAccount(@PathVariable user_id:Int,@RequestBody @Valid bankaccount:BankAccount): BankAccount = {
          	
            
          //Generate and Set IdCardNumber
          val ba_id = baid_seq.getNextBankAccountId()
	  baid_seq.setPreviousBankAccountId()
          bankaccount.setBa_id(ba_id)
	  
	  val existingHashMap = DigitalWalletApplication.baHashMap.get(user_id)
	  
	  //Inner HashMap	
	  if(existingHashMap != null){
		existingHashMap.put(ba_id,bankaccount)	
	  }	
          else{
		val newIdCardHashMap = new HashMap[Int,BankAccount]
          	newIdCardHashMap.put(ba_id,bankaccount)
		//Insert IDcard of user in IDCardHashmap
		DigitalWalletApplication.baHashMap.put(user_id,newIdCardHashMap)
	  }	
          
          return bankaccount
          
        }


	@ResponseStatus(HttpStatus.OK)
  	@RequestMapping(value = Array("/users/{user_id}/bankaccounts"), method = Array(RequestMethod.GET))
        //@ResponseBody def getBankAccount(@PathVariable user_id:Int): HashMap[Int,BankAccount] = {
	@ResponseBody def getBankAccount(@PathVariable user_id:Int): ArrayList[BankAccount] = {
		
		//return DigitalWalletApplication.baHashMap.get(user_id)	
		return getAllBankAccounts(user_id)  
  	}


	@ResponseStatus(HttpStatus.NO_CONTENT)
  	@RequestMapping(value = Array("/users/{user_id}/bankaccounts/{ba_id}"), method = Array(RequestMethod.DELETE))
        @ResponseBody def deleteBankAccountId(@PathVariable user_id:Int,@PathVariable ba_id:Int): HashMap[Int,HashMap[Int,BankAccount]]= {
	
	
		val existingHashMap = DigitalWalletApplication.baHashMap.get(user_id)
		existingHashMap.remove(ba_id)	
		
		return DigitalWalletApplication.baHashMap	
	  
  	}


	def getAllIdCards(user_id: Int): ArrayList[IdCard] = {
		
		val map = DigitalWalletApplication.idcardHashMap.get(user_id)
		val keyList = new ArrayList[IdCard]()
		val iterator = map.entrySet().iterator()
   		while (iterator.hasNext) {
			val entry = iterator.next()
      			keyList.add(entry.getValue)
		}

		return keyList
	}	
	
	def getAllWebLogins(user_id: Int): ArrayList[WebLogin] = {
		
		val map = DigitalWalletApplication.webloginHashMap.get(user_id)
		val keyList = new ArrayList[WebLogin]()
		val iterator = map.entrySet().iterator()
   		while (iterator.hasNext) {
			val entry = iterator.next()
      			keyList.add(entry.getValue)
		}

		return keyList
	}

	def getAllBankAccounts(user_id: Int): ArrayList[BankAccount] = {
		
		val map = DigitalWalletApplication.baHashMap.get(user_id)
		val keyList = new ArrayList[BankAccount]()
		val iterator = map.entrySet().iterator()
   		while (iterator.hasNext) {
			val entry = iterator.next()
      			keyList.add(entry.getValue)
		}

		return keyList
	}

	
	
	



}
