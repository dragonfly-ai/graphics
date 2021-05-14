package ai.dragonfly.graphics

import ai.dragonfly.math.vector.Vector3

import scala.collection.immutable.HashSet

/**
 * Abstract type for polygonal faces.
 */
trait Polygon {
  def objFace(offset: Int = 0): String
  def offset(delta: Int): Polygon
}

case class Triangle(v1: Int, v2: Int, v3: Int) extends Polygon {
  override def objFace(offset: Int = 0): String = s"f ${offset + v1 + 1} ${offset + v2 + 1} ${offset + v3 + 1}"
  override def offset(delta: Int): Polygon = Triangle(v1 + delta, v2 + delta, v3 + delta)
}

case class Quad(v1: Int, v2: Int, v3: Int, v4: Int) extends Polygon {
  def objFace(offset: Int = 0): String = s"f ${offset + v1 + 1} ${offset + v2 + 1} ${offset + v3 + 1} ${offset + v4 + 1}"
  override def offset(delta: Int): Polygon = Quad(v1 + delta, v2 + delta, v3 + delta, v4 + delta)
}

object Mesh {

  def combine(name: String, material: Material, meshes: Mesh*): Mesh = {
    var pointCount = 0
    var polygonCount = 0

    for (m <- meshes) {
      pointCount = pointCount + m.points.length
      polygonCount = polygonCount + m.polygons.length
    }

    val points: Array[Vector3] = new Array[Vector3](pointCount)
    val polygons: Array[Polygon] = new Array[Polygon](polygonCount)

    var pi: Int = 0
    var tj = 0

    for (m <- meshes) {
      var pj = pi

      for (p <- m.points) {
        points(pj) = p
        pj = pj + 1
      }

      for (polygon <- m.polygons) {
        polygons(tj) = polygon.offset(pi)
        tj = tj + 1
      }

      pi = pi + m.points.length
    }

    Mesh(name, material, points, polygons)
  }
}

case class Mesh (name: String, material: Material, points: Array[Vector3], polygons: Array[Polygon], smooth: Boolean = true) {

  def scale(scalar: Double): Unit = {
    for (p <- points) p.scale(scalar)
  }
}

trait Meshable {
  def meshGroup: MeshGroup
}

object MeshGroup  {
  def empty(name: String): MeshGroup = MeshGroup(name, HashSet[Mesh]())
  def apply(name: String, meshGroups: MeshGroup*):MeshGroup = {
    MeshGroup(
      name,
      {
        var hs: HashSet[Mesh] = new HashSet[Mesh]()
        for (mg <- meshGroups) hs = hs ++ mg.meshes
        hs
      }
    )
  }
}

case class MeshGroup(name: String, meshes: Set[Mesh]) {

  def scale(scalar: Double): MeshGroup = {
    for (m <- meshes) {
      m.scale(scalar)
    }
    this
  }

}
