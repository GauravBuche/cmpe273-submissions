package resources

import IdCardSequence._
//remove if not needed
import scala.collection.JavaConversions._

object IdCardSequence {

  private var constant: Int = 2001
}

class IdCardSequence {

  def getNextIdCardNumber(): Int = constant

  def setPreviousIdCardNumber() {
    constant += 1
  }
}
