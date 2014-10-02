package application

import Sequence._
//remove if not needed
import scala.collection.JavaConversions._

object Sequence {

  private var constant: Int = 1001
}

class Sequence {

  def getNextInt(): Int = constant

  def setPreviousInt() {
    constant += 1
  }
}
