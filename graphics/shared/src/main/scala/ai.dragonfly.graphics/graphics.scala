package ai.dragonfly

import ai.dragonfly.math.vector.Vector3

import scala.collection.immutable.HashSet

package object graphics {
  import scala.language.implicitConversions

  type Orientation = Int

  implicit def meshToMeshGroup(mesh: Mesh): MeshGroup = new MeshGroup(mesh.name, HashSet[Mesh](mesh))

  def translatePointsInPlace(points: Array[Vector3], position: Vector3): Array[Vector3] = {
    for (p <- points) p.add(position)
    points
  }

}
