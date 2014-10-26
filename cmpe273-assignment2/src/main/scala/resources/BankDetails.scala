package resources

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import scala.beans.{BeanProperty, BooleanBeanProperty}
//remove if not needed
import scala.collection.JavaConversions._

@JsonIgnoreProperties(ignoreUnknown = true)
class BankDetails {

  @BeanProperty
  var customer_name: String = _

  @BeanProperty
  var routing_number: String = _

  @BeanProperty
  var address: String = _

  @BeanProperty
  var city: String = _
}
