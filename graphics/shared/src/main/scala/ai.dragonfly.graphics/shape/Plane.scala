package ai.dragonfly.graphics.shape

import ai.dragonfly.graphics._
import ai.dragonfly.math.vector.Vector3

object Plane {

  def apply(name: String, l: Double, h: Double, material: Material, position: Vector3 = new Vector3(0.0, 0.0, 0.0)): Mesh = {

    val points: Array[Vector3] = translatePointsInPlace(
      Array[Vector3](
        new Vector3(-l, 0, h),
        new Vector3(l, 0, h),
        new Vector3(l, 0, -h),
        new Vector3(-l, 0, -h)
      ),
      position
    )

    val polygons: Array[Polygon] = Array[Polygon](Quad(0,1,2,3))

    Mesh(name: String, material, points, polygons, false)
  }

}
