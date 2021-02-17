package ai.dragonfly.graphics.shape

import ai.dragonfly.graphics._
import ai.dragonfly.math.vector.Vector3

object Drum {

  def apply(name: String, topRadiusCM: Double, bottomRadiusCM: Double, depthCM: Double, material: Material, position: Vector3 = new Vector3(0.0, 0.0, 0.0), smooth: Boolean = true): Mesh = {

    // 1.5 CM radius on base, 2mm height
    val points: Array[Vector3] = new Array[Vector3](65)

    var dT = 0.0
    var i = 0
    while (dT < 2.0 * Math.PI) {
      points(i) = Vector3(topRadiusCM * Math.sin(dT), topRadiusCM * Math.cos(dT), depthCM)
      dT = dT + (Math.PI / 16.0)
      i = i + 1
    }

    dT = 0.0
    while (dT < 2.0 * Math.PI) {
      points(i) = Vector3(bottomRadiusCM * Math.sin(dT), bottomRadiusCM * Math.cos(dT), 0)
      dT = dT + (Math.PI / 16.0)
      i = i + 1
    }

    points(64) = Vector3(0, 0, depthCM)

    translatePointsInPlace(points, position)

    val polygons: Array[Polygon] = new Array[Polygon](64)

    i = 0
    while (i < 31) {
      val j = i * 2
      polygons(j) = Triangle(i, 64, i + 1) // cap
      polygons(j + 1) = Quad(i, i + 1, i + 33, i + 32) // side
      i = i + 1
    }

    // last one.
    polygons(62) = Triangle(31,64,0) // cap
    polygons(63) = Quad(31, 0, 32, 63)

    Mesh(name: String, material, points, polygons, smooth)
  }

}
