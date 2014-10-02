package resources

import WebLoginIdSequence._
//remove if not needed
import scala.collection.JavaConversions._

object WebLoginIdSequence {

  private var constant: Int = 3001
}

class WebLoginIdSequence {

  def getNextWebLoginId(): Int = constant

  def setPreviousWebLoginId() {
    constant += 1	
  }
}
