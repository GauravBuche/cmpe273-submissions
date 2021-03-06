package resources

import java.util.Date
import scala.beans.{BeanProperty, BooleanBeanProperty}
import org.hibernate.validator.constraints.NotEmpty
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat._
import org.springframework.format.annotation.DateTimeFormat.ISO
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import serializers.CustomIdCardDateDeserializer
import serializers.CustomIdCardDateSerializer
import serializers.CustomIdCardIdSerializer
//remove if not needed
import scala.collection.JavaConversions._

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
class IdCard {

  //@BeanProperty
  
  var card_id: Int = _

  
  def getCard_id = card_id
  
  @JsonSerialize(using = classOf[CustomIdCardIdSerializer])
  def setCard_id(newCard_id: Int) = card_id = newCard_id

  @NotEmpty(message = "Please enter your 'card_name'.")	
  @BeanProperty
  var card_name: String = _

  @NotEmpty(message = "Please enter your 'card_number'.")
  @BeanProperty
  var card_number: String = _

  //@DateTimeFormat(iso=ISO.DATE)
  //@DateTimeFormat(pattern="MM-dd-yyyy") 
  

  //@BeanProperty
  //var expiration_date: Date = _

 //@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
 //@BeanProperty	
 var expiration_date : Date = _
  
 // @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
  @JsonSerialize(using = classOf[CustomIdCardDateSerializer])
  def getExpiration_date = expiration_date
  
  @JsonDeserialize(using = classOf[CustomIdCardDateDeserializer])
  def setExpiration_date(newExpiration_date: Date) = expiration_date = newExpiration_date		
}

