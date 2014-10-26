package resources

import scala.beans.{BeanProperty, BooleanBeanProperty}
import serializers.CustomBankAccIdSerializer
import org.hibernate.validator.constraints.NotEmpty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import scala.collection.JavaConversions._
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "bankaccount")
class BankAccount {

  //@BeanProperty
  var ba_id: Int = _

  def getBa_id = ba_id
  
  @JsonSerialize(using = classOf[CustomBankAccIdSerializer])
  def setBa_id(newBa_id: Int) = ba_id = newBa_id

  @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
  @BeanProperty
  var account_name: String = _

  @NotEmpty(message = "Please enter your 'routing number'.")
  @BeanProperty
  var routing_number: String = _

  @NotEmpty(message = "Please enter your 'account number'.")
  @BeanProperty
  var account_number: String = _
}
