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
//import org.springframework.format.annotation.DateTimeFormat.style
import scala.beans.{BeanProperty, BooleanBeanProperty}
//remove if not needed
import scala.collection.JavaConversions._

/*class User(@BeanProperty var userId: Int, 
    @BeanProperty var email: String, 
    @BeanProperty var password: String, 
    @BeanProperty var created_at: Date, 
    @BeanProperty var updated_at: Date) {

  @BeanProperty
  var name: String = _
}*/



/*class User {

  @BeanProperty
  var userId: Int = _

  @BeanProperty
  var email: String = _

  @BeanProperty
  var password: String = _

  @BeanProperty
  var name: String = _

  @BeanProperty
  var created_at: Date = _

  @BeanProperty
  var updated_at: Date = _

  def this(email: String, password: String) {
    this()
    this.email = email
    this.password = password
  }
}*/

/*object User {
import javax.validation.Valid;
  val seq = 0import javax.validation.Valid;
}*/

class User {
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
}

