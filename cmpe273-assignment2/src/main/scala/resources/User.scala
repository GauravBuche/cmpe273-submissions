package resources

import java.util.Date
import java.util.Calendar
import org.hibernate.validator.constraints.NotEmpty
import javax.validation.constraints.NotNull
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat._
import org.springframework.format.annotation.DateTimeFormat.ISO
import serializers.CustomUserDateSerializer
import serializers.CustomUserIdSerializer
import serializers.CustomUserIdPutSerializer
import org.joda.time.DateTime
import scala.beans.{BeanProperty, BooleanBeanProperty}
import scala.collection.JavaConversions._
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/*class User {
  val seq = 0	
  
  		
  //@BeanProperty
  var user_id: Int = _

  //@JsonSerialize(using = classOf[CustomUserIdPutSerializer]) 
  def getUser_id = user_id
	
  @JsonSerialize(using = classOf[CustomUserIdSerializer])
  def setUser_id(newUser_id: Int) = user_id = newUser_id


  @NotEmpty(message = "Please enter your 'email'.")
  @BeanProperty
  var email: String = _


  @NotEmpty(message = "Please enter your 'password'.") 	
  @BeanProperty
  var password: String = _

  @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)	
  @BeanProperty
  var name: String = _
  
  //@DateTimeFormat(iso=ISO.DATE)
  //@DateTimeFormat(pattern="MM-dd-yyyy") 
  //@BeanProperty
  var created_at: DateTime = _
  
  
  def getCreated_at = created_at	

  @JsonSerialize(using = classOf[CustomUserDateSerializer])
  def setCreated_at(newCreated_at: DateTime) = created_at = newCreated_at

  //@DateTimeFormat(iso=ISO.DATE)
  //@DateTimeFormat(pattern="MM-dd-yyyy") 
  //@BeanProperty
  var updated_at: DateTime = _

  
  def getUpdated_at = updated_at	


  @JsonSerialize(using = classOf[CustomUserDateSerializer])
  def setUpdated_at(newUpdated_at: DateTime) = updated_at = newUpdated_at	

  def this(email: String, password: String) {
    this()
    this.email = email
    this.password = password
  }
  
}*/
/*
class User {

  @Id
  private var user_id: String = _

  private var email: String = _

  private var password: String = _

  def this(email: String, password: String) {
    this()
    this.email = email
    this.password = password
    
  }
}*/

@Document(collection = "user")
class User {

/*
  @BeanProperty
  var user_id: String = _
*/
  
  /*@Id
  private var id: String = _*/
  
  var user_id: Int = _
  
  def getUser_id = user_id
	
  @JsonSerialize(using = classOf[CustomUserIdSerializer])
  def setUser_id(newUser_id: Int) = user_id = newUser_id
  
  
  @NotEmpty(message = "Please enter your 'email'.")	
  @BeanProperty
  var email: String = _

  //@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
  @NotEmpty(message = "Please enter your 'password'.")
  @BeanProperty
  var password: String = _

  @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
  @BeanProperty
  var name: String = _
  
  var created_at: DateTime = _
  
  def getCreated_at = created_at	

  @JsonSerialize(using = classOf[CustomUserDateSerializer])
  def setCreated_at(newCreated_at: DateTime) = created_at = newCreated_at

  var updated_at: DateTime = _
  
  def getUpdated_at = updated_at	

  @JsonSerialize(using = classOf[CustomUserDateSerializer])
  def setUpdated_at(newUpdated_at: DateTime) = updated_at = newUpdated_at	
}

