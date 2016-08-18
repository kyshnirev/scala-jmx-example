package test.main

import java.lang.management.ManagementFactory
import javax.management.ObjectName
import test.mbean.CharCounter
import scala.io.StdIn

object MBeanMain extends App {

  val mbean = new CharCounter
  val c = mbean.getClass
  ManagementFactory.getPlatformMBeanServer.registerMBean(
      mbean,
      new ObjectName(s"${c.getPackage.getName}:type=${c.getSimpleName}")
  )

  println("type '\\q' to exit")
  var line :String = null
  do {
      line = StdIn.readLine("> ")
      line.toCharArray().foreach { c => mbean.putChar(c) }
  } while (line != "\\q")
}
