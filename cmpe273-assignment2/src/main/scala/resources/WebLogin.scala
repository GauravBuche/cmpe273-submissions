package resources

import scala.beans.{BeanProperty, BooleanBeanProperty}
import serializers.CustomWebLoginIdSerializer
import org.hibernate.validator.constraints.NotEmpty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import scala.collection.JavaConversions._
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "weblogin")
class WebLogin {

  
  //@BeanProperty
  var login_id: Int = _
  
  def getLogin_id = login_id
  
  @JsonSerialize(using = classOf[CustomWebLoginIdSerializer])
  def setLogin_id(newLogin_id: Int) = login_id = newLogin_id

  @NotEmpty(message = "Please enter your 'url'.")
  @BeanProperty
  var url: String = _

  @NotEmpty(message = "Please enter your 'login'.")
  @BeanProperty
  var login: String = _

  @NotEmpty(message = "Please enter your 'password'.")
  @BeanProperty
  var password: String = _
}

