package ai.dragonfly.graphics

import ai.dragonfly.math.vector.Vector3

import scala.collection.immutable.HashSet
import scala.collection.mutable

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

object GeneratesOBJ {
  val defaultComment = s"OBJ file generated by the raphics.dragonfly.ai scala.js library.  Visit http://dragonfly.ai for more information."
  val defaultMaterialFileName = "default.mtl"
}

trait GeneratesOBJ {
  def name: String

  def toObj(comment:String, materialLibraryFileName:String): String
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

case class Mesh (override val name: String, material: Material, points: Array[Vector3], polygons: Array[Polygon], smooth: Boolean = true) extends GeneratesOBJ {

  def scale(scalar: Double): Unit = {
    for (p <- points) p.scale(scalar)
  }

  override def toObj(comment:String = GeneratesOBJ.defaultComment, materialLibraryFile:String = GeneratesOBJ.defaultMaterialFileName): String = {
    val sb = new mutable.StringBuilder()
    sb.append(s"#  $comment\n")
    sb.append(s"mtllib $materialLibraryFile")
    sb.append(s"o $name\n")
    for (p <- points) { sb.append(s"v ${p.x} ${p.z} ${p.y}\n") }

    if (smooth) sb.append(s"s 1\n")

    sb.append(s"usemtl ${material.name}\n")

    for (t <- polygons)  sb.append(s"${t.objFace()}\n")

    sb.toString()
  }

}

trait Meshable {
  def meshGroup: MeshGroup
}

object MeshGroup  {
  def empty(name: String): MeshGroup = new MeshGroup(name, HashSet[Mesh]())
}

class MeshGroup(override val name: String, val meshes: Set[Mesh]) extends GeneratesOBJ {

  def this(name: String, meshGroups: MeshGroup*) {
    this(
      name,
      {
        var hs: HashSet[Mesh] = new HashSet[Mesh]()
        for (mg <- meshGroups) hs = hs ++ mg.meshes
        hs
      }
    )
  }

  def scale(scalar: Double): MeshGroup = {
    for (m <- meshes) {
      m.scale(scalar)
    }
    this
  }

  /* How to add materials to meshes?  How to combine them without losing materials? */
  def toObj(comment:String = GeneratesOBJ.defaultComment, materialLibraryFile:String = GeneratesOBJ.defaultMaterialFileName): String = {
    var pointCount = 0
    var triangleCount = 0

    for (m <- meshes) {
      pointCount = pointCount + m.points.length
      triangleCount = triangleCount + m.polygons.length
    }

    val pointSB = new mutable.StringBuilder()
    val triangleSB = new mutable.StringBuilder()

    pointSB.append(s"# © ${java.time.Year.now.getValue} batteriesandcarkeys.com obj file\n\n")
    pointSB.append(s"mtllib autoBattery.mtl\n\n")

    var pi: Int = 0

    for (m <- meshes) {
      var pj = 0

      for (p <- m.points) {
        pointSB.append(s"v ${-p.x} ${p.z} ${p.y}\n")
        pj = pj + 1
      }

      var tj = 0

      triangleSB.append(s"g ${m.name}\n")
      //if (m.smooth) triangleSB.append(s"s 1\n") else
      triangleSB.append(s"s 1\n")
      triangleSB.append(s"usemtl ${m.material.name}\n")

      for (t <- m.polygons) {
        triangleSB.append(s"${t.objFace(pi)}\n")
        tj = tj + 1
      }

      pi = pi + m.points.length
    }
    pointSB.append(triangleSB).toString()
  }

}