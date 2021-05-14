package ai.dragonfly.graphics

import ai.dragonfly.math.vector._

import scala.collection.mutable

object Voxel {

  def fromTriangle(a:Vector3, b:Vector3, c:Vector3, voxelWidth:Double = 1.0):Seq[UnitVoxel] = {
    val sortedVoxelCount:mutable.TreeMap[Int, mutable.TreeMap[Int, mutable.TreeMap[Int, Int]]] = mutable.TreeMap[Int, mutable.TreeMap[Int, mutable.TreeMap[Int, Int]]]()
    val aScaled = a.copy().scale(1.0 / voxelWidth)
    val AB = b.copy().scale(1.0 / voxelWidth).subtract(aScaled)
    val lenAB = AB.magnitude()
    val AC = c.copy().scale(1.0 / voxelWidth).subtract(aScaled)
    val lenAC = AC.magnitude()

    for (u <- 0 until (3 * lenAB).toInt) {
      val oAB = 1.0 / (3.0 * lenAB / u)
      for (v <- 0 until (3 * lenAC).toInt) {
        val oAC = 1.0 / (3.0 * lenAC / v)

        if (oAB + oAC < 1) {
          val U = AB.copy().scale(oAB)
          val V = AC.copy().scale(oAC)
          val voxel = a.copy().add(U).add(V)
          val hist = sortedVoxelCount.getOrElseUpdate(voxel.x.toInt,
            mutable.TreeMap[Int, mutable.TreeMap[Int, Int]]()
          ).getOrElseUpdate(voxel.y.toInt,
            mutable.TreeMap[Int, Int]()
          )
          hist.put(voxel.z.toInt, 1 + hist.getOrElseUpdate(voxel.z.toInt, 0))
        }
      }
    }

    var voxels:Seq[UnitVoxel] = Seq[UnitVoxel]()
    for ((x, ys) <- sortedVoxelCount) {
      for ((y, zs) <- ys) {
        for ((z, count) <- zs) {
          voxels = voxels :+ UnitVoxel(x, y, z)
        }
      }
    }
    voxels
  }

  def meshToVoxels(m:Mesh, voxelWidth:Double = 1.0):Seq[UnitVoxel] = {
    var voxels:Seq[UnitVoxel] = Seq[UnitVoxel]()
    for (polygon <- m.polygons) {
      polygon match {
        case t:Triangle =>
          voxels = voxels :++ fromTriangle(m.points(t.v1), m.points(t.v2), m.points(t.v3), voxelWidth)
        case q:Quad =>
          val center:Vector3 = Vector.average(m.points(q.v1), m.points(q.v2), m.points(q.v3), m.points(q.v4)).asInstanceOf[Vector3]
          voxels = voxels :++ fromTriangle(m.points(q.v1), m.points(q.v2), center, voxelWidth)
          voxels = voxels :++ fromTriangle(m.points(q.v2), m.points(q.v3), center, voxelWidth)
          voxels = voxels :++ fromTriangle(m.points(q.v3), m.points(q.v4), center, voxelWidth)
          voxels = voxels :++ fromTriangle(m.points(q.v4), m.points(q.v1), center, voxelWidth)
      }
    }
    voxels
  }
}

case class UnitVoxel(x:Int, y:Int, z:Int) {
  override def toString: String = s"[$x,$y,$z]"
}
