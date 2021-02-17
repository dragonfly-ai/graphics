package ai.dragonfly.graphics.shape

import ai.dragonfly.graphics._
import ai.dragonfly.math.vector.Vector3

object Cube {

  def apply(name: String, l: Double, w: Double, h: Double, material: Material, position: Vector3 = new Vector3(0.0, 0.0, 0.0), smooth: Boolean = false): Mesh = {

    val points: Array[Vector3] = translatePointsInPlace(
      Array[Vector3](
        new Vector3(-l, -w, h), // 0
        new Vector3(l, -w, h), // 1
        new Vector3(l, w, h), // 2
        new Vector3(-l, w, h), // 3

        new Vector3(-l, -w, 0), // 4
        new Vector3(l, -w, 0),  // 5
        new Vector3(l, w, 0),  // 6
        new Vector3(-l, w, 0) // 7
      ),
      position
    )

    val polygons: Array[Polygon] = Array[Polygon](
      Quad(0,1,2,3), Quad(0, 3, 7, 4), Quad(2, 1, 5, 6), Quad(3, 2, 6, 7), Quad(0, 4, 5, 1), Quad(4, 7, 6, 5)
    )

    Mesh(name: String, material, points, polygons, smooth)
  }
}
