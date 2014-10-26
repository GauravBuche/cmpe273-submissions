package dbconfiguration


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate
import com.mongodb.MongoClient
import scala.collection.JavaConversions._
import com.mongodb.MongoClientURI

@Configuration
class MongoDBConfiguration{

  @Bean
  def mongoTemplate(): MongoTemplate = {
    val mongoURI = new MongoClientURI("mongodb://GauravBuche:22july2011!@ds047950.mongolab.com:47950/cmpe273assgn2")
    val mongoTemplate = new MongoTemplate(new MongoClient(mongoURI), "cmpe273assgn2")
    return mongoTemplate
  }
}
