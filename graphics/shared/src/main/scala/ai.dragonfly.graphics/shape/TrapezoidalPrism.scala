package ai.dragonfly.graphics.shape

import ai.dragonfly.graphics._
import ai.dragonfly.math.vector.Vector3

object TrapezoidalPrism {

  def apply(name: String, maxLength: Double, minLength: Double, maxWidth: Double, minWidth: Double, maxHeight: Double, minHeight: Double, material: Material, orientation: Orientation = Orientation.FRONT, position: Vector3 = new Vector3(0.0, 0.0, 0.0), smooth: Boolean = false): Mesh = {
    val maxL = maxLength / 2.0; val minL = minLength / 2.0
    val maxW = maxWidth / 2.0; val minW = minWidth / 2.0
    val maxH = maxHeight / 2.0; val minH = minHeight / 2.0

    val points: Array[Vector3] = Array[Vector3](
      Orientation.orient(-maxL, -maxW, maxH, orientation, position),  // 0
      Orientation.orient( maxL, -maxW, maxH, orientation, position),  // 1
      Orientation.orient( minL,  minW, minH, orientation, position),  // 2
      Orientation.orient(-minL,  minW, minH, orientation, position),  // 3
      Orientation.orient(-maxL, -maxW, 0, orientation, position),  // 4
      Orientation.orient( maxL, -maxW, 0, orientation, position),  // 5
      Orientation.orient( minL,  minW, 0, orientation, position),  // 6
      Orientation.orient(-minL,  minW, 0, orientation, position)  // 7
    )

    val polygons: Array[Polygon] = Array[Polygon]( Quad(0, 1, 2, 3), Quad(0, 3, 7, 4), Quad(2, 1, 5, 6), Quad(3, 2, 6, 7), Quad(0, 4, 5, 1), Quad(4, 7, 6, 5) )

    Mesh(name, material, points, polygons, smooth)
  }
}
