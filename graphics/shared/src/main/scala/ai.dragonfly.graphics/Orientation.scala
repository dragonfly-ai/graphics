package ai.dragonfly.graphics

import ai.dragonfly.math.vector.Vector3

object Orientation {

  val FRONT: Orientation = 0
  val BACK: Orientation = 1
  val LEFT: Orientation = 2
  val RIGHT: Orientation = 3

  def orient(v: Vector3, orientation: Orientation, position: Vector3): Vector3 = {
    (orientation match {
      case Orientation.FRONT => new Vector3(-v.x, -v.y, v.z)
      case Orientation.BACK => new Vector3(v.x, v.y, v.z)
      case Orientation.LEFT => new Vector3(-v.y, -v.x, v.z)
      case Orientation.RIGHT => new Vector3(v.y, v.x, v.z)
    }).add(position)
  }

  def orient(x: Double, y: Double, z: Double, orientation: Orientation, position: Vector3 = new Vector3(0.0, 0.0, 0.0)): Vector3 = orient(new Vector3(x, y, z), orientation, position)

  def rotateAboutZAxis(theta: Double, v: Vector3): Vector3 = {
    val cosTheta = Math.cos(theta)
    val sinTheta = Math.sin(theta)
    Vector3(cosTheta * v.x + -sinTheta * v.y, sinTheta * v.x + cosTheta * v.y, v.z)
  }

  def orientations: Array[Orientation] = Array[Orientation](FRONT, BACK, LEFT, RIGHT)

}