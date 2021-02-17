package ai.dragonfly.graphics

import ai.dragonfly.color.Color
import ai.dragonfly.graphics.shape._

object Test extends App {
  val metalMtl = Material("metal", Color.GRAY)
  val tBolt = ThreadedBolt("A_Threaded_Bolt", 4.0, 10, metalMtl, metalMtl)
  println(tBolt)
  println(tBolt.toObj())
}
