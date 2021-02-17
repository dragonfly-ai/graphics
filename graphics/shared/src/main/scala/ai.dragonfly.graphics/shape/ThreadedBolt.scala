package ai.dragonfly.graphics.shape

import ai.dragonfly.graphics.{Material, Mesh, Orientation, Polygon, Triangle}
import ai.dragonfly.math.vector.Vector3

object ThreadedBolt {

  val referencePoints: Array[Vector3] = Array[Vector3](
    Vector3(0.19753361123614124, 0.29378178639254915, 0.044444444461940645),
    Vector3(0.2962395976527229, 0.19507579997596758, 0.044444444461940645),
    Vector3(-0.0012271168300430407, 0.35407508066868726, 0.027777777788712908),
    Vector3(0.06856828327873153, 0.3472025109004287, 0.03333333334645549),
    Vector3(0.13568048332181434, 0.3268423888019595, 0.027777777788712908),
    Vector3(0.19753361123614124, 0.29378178639254915, 0.03333333334645549),
    Vector3(0.06856828327873153, 0.3472025109004287, 0.022222222230970323),
    Vector3(-0.07102251693881764, 0.3472025109004287, 0.022222222230970323),
    Vector3(-0.0012271168300430407, 0.35407508066868726, 0.016666666673227745),
    Vector3(-0.1381347169819004, 0.3268423888019595, 0.016666666673227745),
    Vector3(0.13568048332181434, 0.3268423888019595, 0.03888888890419807),
    Vector3(-0.19998784489622734, 0.29378178639254915, 0.0),
    Vector3(0.25174856296346554, 0.2492907517032919, 0.03888888890419807),
    Vector3(-0.07102251693881764, 0.3472025109004287, 0.011111111115485161),
    Vector3(-0.1381347169819004, 0.3268423888019595, 0.005555555557742581),
    Vector3(-0.19998784489622734, 0.29378178639254915, 0.011111111115485161),
    Vector3(-0.25420279662355166, 0.2492907517032919, 0.005555555557742581),
    Vector3(-0.2601738111697377, 0.38385502379777847, 0.0),
    Vector3(-0.1797672492011741, 0.42683631125007315, 0.005555555557742581),
    Vector3(-0.3306525328952418, 0.32601596318172643, 0.005555555557742581),
    Vector3(-0.001785222443706931, 0.46223666411676684, 0.016666666673227745),
    Vector3(0.0889498709777263, 0.45329981909796935, 0.022222222230970323),
    Vector3(0.2566033662823239, 0.38385502379777847, 0.03333333334645549),
    Vector3(0.17619680431376022, 0.42683631125007315, 0.027777777788712908),
    Vector3(-0.38849159351129386, 0.25553724145622236, 0.0),
    Vector3(-0.09251673826505256, 0.45329981909796935, 0.011111111115485161),
    Vector3(0.2566033662823239, 0.38385502379777847, 0.044444444461940645),
    Vector3(0.17619680431376022, 0.42683631125007315, 0.03888888890419807),
    Vector3(0.0889498709777263, 0.45329981909796935, 0.03333333334645549),
    Vector3(-0.001785222443706931, 0.46223666411676684, 0.027777777788712908),
    Vector3(-0.09251673826505256, 0.45329981909796935, 0.022222222230970323),
    Vector3(-0.1797672492011741, 0.42683631125007315, 0.016666666673227745),
    Vector3(-0.2601738111697377, 0.38385502379777847, 0.011111111115485161),
    Vector3(0.327082088007828, 0.32601596318172643, 0.03888888890419807),
    Vector3(-0.3306525328952418, 0.32601596318172643, 0.005555555557742581),
    Vector3(0.38492114862388, 0.25553724145622236, 0.044444444461940645),
    Vector3(-0.29869383131280897, 0.19507579997596758, 0.0),
    Vector3(-0.3306525328952418, 0.32601596318172643, -0.0),
    Vector3(0.327082088007828, 0.32601596318172643, 0.044444444461940645)
  )

  def fillTriangles(vi: Int, tOffset: Int, polygons: Array[Polygon]): Unit = {
    var ti = tOffset
    polygons(ti) = Triangle( vi + 7, vi + 29, vi + 30 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 2, vi + 28, vi + 29 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 5, vi + 33, vi + 12 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 16, vi + 24, vi + 36 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 15, vi + 34, vi + 16 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 3, vi + 27, vi + 28 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 10, vi + 26, vi + 27 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 12, vi + 35, vi + 1 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 9, vi + 30, vi + 31 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 13, vi + 20, vi + 8 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 14, vi + 25, vi + 13 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 11, vi + 18, vi + 14 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 6, vi + 23, vi + 4 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 26, vi + 33, vi + 22 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 27, vi + 22, vi + 23 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 23, vi + 28, vi + 27 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 21, vi + 29, vi + 28 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 30, vi + 20, vi + 25 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 31, vi + 25, vi + 18 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 32, vi + 18, vi + 17 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 37, vi + 34, vi + 17 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 37, vi + 24, vi + 34 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 12, vi + 33, vi + 35 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 32, vi + 31, vi + 18 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 8, vi + 20, vi + 21 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 15, vi + 9, vi + 31 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 9, vi + 7, vi + 30 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 34, vi + 32, vi + 17 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 11, vi + 17, vi + 18 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 6, vi + 21, vi + 23 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 14, vi + 18, vi + 25 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 13, vi + 25, vi + 20 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 3, vi + 10, vi + 27 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 15, vi + 32, vi + 34 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 10, vi + 0, vi + 26 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 4, vi + 23, vi + 22 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 23, vi + 21, vi + 28 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 16, vi + 34, vi + 24 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 2, vi + 3, vi + 28 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 7, vi + 2, vi + 29 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 5, vi + 22, vi + 33 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 30, vi + 29, vi + 20 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 31, vi + 30, vi + 25 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 21, vi + 20, vi + 29 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 15, vi + 31, vi + 32 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 8, vi + 21, vi + 6 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 4, vi + 22, vi + 5 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 27, vi + 26, vi + 22 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 26, vi + 38, vi + 33 )
    ti = ti +1
    polygons(ti) = Triangle( vi + 33, vi + 38, vi + 35 )
  }

  def apply(name: String, radiusCM: Double, heightCM: Double, boltMaterial: Material, threadMaterial: Material, position: Vector3 = Vector3(0.0,0.0,0.0), smooth: Boolean = true): Mesh = {

    val naturalHeight = 1.6
    val naturalSegmentHeight = 0.044444444
    val naturalThreadRadius = 4.65
    val naturalDrumRadius = 0.41

    val xyScalar = radiusCM / naturalThreadRadius

    val zScalar = heightCM / naturalHeight

    val drumRadius = xyScalar * naturalDrumRadius
    val segmentHeight = zScalar * naturalSegmentHeight

    val sections = 4 * 9
    val segments = 1
    val vectorsPerSegment = referencePoints.length
    val polygonsPerSegment = 50

    val pointSection: Array[Vector3] = new Array[Vector3](sections * segments * vectorsPerSegment)
    val polygonSection: Array[Polygon] = new Array[Polygon](sections * segments * polygonsPerSegment)

    var vi = 0
    var vj = 0
    var tOffset = 0

    val angles = Array[Double](0, Math.PI * 3.0 / 2.0, Math.PI, Math.PI / 2.0)
    var t = 0

    for (s <- 0 until sections) {
      val sectionOffset: Vector3 = Vector3( 0.0, 0.0, s * segmentHeight )

      val theta: Double = angles(t)

      for (v <- referencePoints) {
        val v1 = Orientation.rotateAboutZAxis(theta, v)
        val v2 = if (s == sections - 1) {
          val t = v1.z / segmentHeight
          val m = v1.copy.scale(xyScalar).magnitude()
          v1.copy.normalize().scale(drumRadius + (m - drumRadius) * (1 - t))
        } else {
          v1.copy.scale(xyScalar)
        }
        v1.scale(zScalar)
        pointSection(vj) = Vector3(v2.x, v2.y, v1.z).add(sectionOffset).add(position)
        vj = vj + 1
      }

      fillTriangles(vi, tOffset, polygonSection)

      vi = vi + referencePoints.length
      tOffset = tOffset + 50
      t = (t + 1) % 4
    }
    Mesh.combine(
      name,
      boltMaterial,
      new Mesh(name, threadMaterial, pointSection, polygonSection, false),
      Drum(name, drumRadius, drumRadius, heightCM, boltMaterial, position)
    )
  }

}