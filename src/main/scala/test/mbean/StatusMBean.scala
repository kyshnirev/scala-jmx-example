package test.mbean

// define an "interface" (name ends with "MBean"!)
trait StatusMBean {
  def getAllChars : String
  def getCharsCount : Array[String]
}

class Status extends StatusMBean {

  private val cmap = new scala.collection.mutable.HashMap[Char, Int].withDefaultValue(0)

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

}
