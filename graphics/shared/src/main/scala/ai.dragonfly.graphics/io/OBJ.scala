package ai.dragonfly.graphics.io

import java.io.{BufferedReader, InputStream, InputStreamReader, OutputStream}
import java.util

import ai.dragonfly.graphics.{Material, Mesh, MeshGroup, Polygon, Quad, Triangle}
import ai.dragonfly.math.vector.Vector3

import scala.collection.mutable

object OBJ {

  private val defaultComment = s"OBJ file generated by the graphics.dragonfly.ai scala.js library.  Visit http://dragonfly.ai for more information."
  private val defaultMaterialFileName:String = "default.mtl"

  val vertexLine: StringContext = StringContext("v ", " ", " ", "")

  def fromMesh(mesh:Mesh, comment:String = defaultComment, materialLibraryFile:String = defaultMaterialFileName): String = {
    val sb = new mutable.StringBuilder()
    sb.append(s"#  $comment\n")
    sb.append(s"mtllib $materialLibraryFile")
    sb.append(s"o ${mesh.name}\n")
    for (p <- mesh.points) { sb.append(s"v ${p.x} ${p.z} ${p.y}\n") }

    if (mesh.smooth) sb.append(s"s 1\n")

    sb.append(s"usemtl ${mesh.material.name}\n")

    for (t <- mesh.polygons)  sb.append(s"${t.objFace()}\n")

    sb.toString()
  }

  /* How to add materials to meshes?  How to combine them without losing materials? */
  def fromMeshGroup(meshGroup:MeshGroup, comment:String = defaultComment, materialLibraryFile:String = defaultMaterialFileName): String = {
    var pointCount = 0
    var triangleCount = 0

    for (m <- meshGroup.meshes) {
      pointCount = pointCount + m.points.length
      triangleCount = triangleCount + m.polygons.length
    }

    val pointSB = new mutable.StringBuilder()
    val triangleSB = new mutable.StringBuilder()

    pointSB.append(s"# $comment\n\n")
    pointSB.append(s"mtllib $materialLibraryFile\n\n")

    var pi: Int = 0

    for (m <- meshGroup.meshes) {
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

  // IO
  def writeMesh(mesh:Mesh, out:OutputStream, comment:String = defaultComment, materialLibraryFileName:String = defaultMaterialFileName): Unit = {
    out.write(OBJ.fromMesh(mesh, comment, materialLibraryFileName).getBytes)
  }

  def writeMeshGroup(meshGroup:MeshGroup, out:OutputStream, comment:String = defaultComment, materialLibraryFileName:String = defaultMaterialFileName): Unit = {
    out.write(OBJ.fromMeshGroup(meshGroup, comment, materialLibraryFileName).getBytes)
  }

  def parseVertex(line:String):Option[Vector3] = {
    vertexLine.s.unapplySeq(line) match {
      case Some(Seq(xS:String, yS:String, zS:String)) =>
        try {
          Some(
            Vector3(
              java.lang.Double.parseDouble(xS),
              java.lang.Double.parseDouble(yS),
              java.lang.Double.parseDouble(zS)
            )
          )
        } catch {
          case _:Throwable => None
        }
      case _ => None
    }
  }

  /**
   * Only parses triangles and quads.
   * @param line a line of text depicting an OBJ face.
   * @return
   */
  def parseFace(line:String):Option[Polygon] = {
    val tokens:Array[String] = line.split("\\s")
    if (tokens.head.equals("f")) {
      val vertexReferences:util.ArrayList[Int] = new util.ArrayList[Int]()
      for (vid <- tokens.tail) try { vertexReferences.add(Integer.parseInt(vid)) } catch { case t: Throwable => }
      vertexReferences.size() match {
        case 3 => Some(Triangle(vertexReferences.get(0)-1, vertexReferences.get(1)-1, vertexReferences.get(2)-1))
        case 4 => Some(Quad(vertexReferences.get(0)-1, vertexReferences.get(1)-1, vertexReferences.get(2)-1, vertexReferences.get(3)-1))
        case _ => None
      }
    } else None
  }

  def read(name:String, is:InputStream):Mesh = {
    val br: java.io.BufferedReader = new BufferedReader(new InputStreamReader(is, "UTF-8"))

    var line = br.readLine()
    val point:util.ArrayList[Vector3] = new util.ArrayList[Vector3]()
    val polygon:util.ArrayList[Polygon] = new util.ArrayList[Polygon]()

    while (line != null) {
      parseVertex(line) match {
        case Some(p: Vector3) =>
          point.add(p)
        case None =>
          parseFace(line) match {
            case Some(p: Polygon) =>
              polygon.add(p)
            case _ => // ignore
          }
      }
      line = br.readLine()
    }
    br.close()
    val pointsArr:Array[Vector3] = new Array[Vector3](point.size())
    val polygonArr:Array[Polygon] = new Array[Polygon](polygon.size())
    Mesh(name, Material.default, point.toArray(pointsArr), polygon.toArray(polygonArr))
  }
}
