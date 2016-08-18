package test.mbean

import scala.collection.mutable

// define an "interface" (name ends with "MBean"!)
trait CharCounterMBean {
  protected var filter : Set[Char] = Set.empty
  def getFilter : String = filter.mkString(", ")
  def setFilter(commaSplitChars : String) = filter = commaSplitChars.toCharArray.filter { c => c != ' ' && c != ',' }.toSet
  def getAllChars : String
  def getCharsCount : Array[String]
  def clear : Unit
}

class CharCounter extends CharCounterMBean {

  private val cmap = new mutable.HashMap[Char, Int].withDefaultValue(0)

  def putChar(c : Char) = {
    if (filter.isEmpty || filter.contains(c))
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
