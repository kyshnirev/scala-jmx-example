package test.mbean

import scala.collection.mutable

// define an "interface" (name ends with "MBean"!)
trait CharCounterMBean {
  def getAllChars : String
  def getCharsCount : Array[String]
  def clear : Unit
}

class CharCounter extends CharCounterMBean {

  private val cmap = new mutable.HashMap[Char, Int].withDefaultValue(0)

  def putChar(c : Char) = {
      cmap(c) += 1
  }

  override def getAllChars : String =
      cmap.keys.toSeq.sorted.mkString(", ")

  override def getCharsCount : Array[String] =
      cmap.toSeq
          .sortBy(_._1)
          .map { case (k, v) => s"$k = $v" }
          .toArray

   override def clear = cmap.clear()

}
