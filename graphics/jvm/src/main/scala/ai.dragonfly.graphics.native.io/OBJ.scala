package ai.dragonfly.graphics.native.io

import java.io.{File, FileInputStream, FileOutputStream, PrintWriter}

import ai.dragonfly.graphics.{Mesh, MeshGroup, Voxel}

object OBJ {

  private val defaultComment = s"OBJ file generated by the graphics.dragonfly.ai scala.js library.  Visit http://dragonfly.ai for more information."
  private val defaultMaterialFileName:String = "default.mtl"

  def writeMeshFile(mesh:Mesh, file: File, comment:String = defaultComment, materialFileName:String = defaultMaterialFileName): File = {
    ai.dragonfly.graphics.io.OBJ.writeMesh(mesh, new FileOutputStream(file), comment, materialFileName)
    file
  }

  def writeMeshGroupFile(meshGroup:MeshGroup, file: File, comment:String = defaultComment, materialFileName:String = defaultMaterialFileName): File = {
    ai.dragonfly.graphics.io.OBJ.writeMeshGroup(meshGroup, new FileOutputStream(file), comment, materialFileName)
    file
  }

  def main(args:Array[String]):Unit = {
    val m:Mesh = ai.dragonfly.graphics.io.OBJ.read("Tank", new FileInputStream(new File("/home/c/BlenderScenes/minecraft/tankSimple.obj")))
    writeMeshFile(m, new File("/home/c/BlenderScenes/minecraft/tankSimple.obj"))

    // minecraft voxels
    val voxels = Voxel.meshToVoxels(m)

    for (v <- voxels) {
      println(s"mc.setBlock(spot + Vec3(${v.x}, ${v.y}, ${v.z}), 35, ${if (Math.random() > 0.5) 12 else 13})")
    }
/*
    val ocTable: Array[Array[Array[Int]]] = Array[Array[Array[Int]]](
      Array[Array[Int]]( // x = 0
        Array[Int]( // y = 0
          0x1, // z = 0
          0x4      // z = 1
        ),
        Array[Int]( // y = 1
          0xA, // z = 0
          0x40      // z = 1
        )
      ),
      Array[Array[Int]]( // x = 1
        Array[Int]( // y = 0
          0x2, // z = 0
          0x8      // z = 1
        ),
        Array[Int]( // y = 1
          0x20, // z = 0
          0x80  // z = 1
        )
      )
    )

    val blocks:mutable.HashMap[Int, (Int, Int)] = mutable.HashMap[Int, (Int, Int)](
      0xFF -> (43, 2), // Whole Block
      0x0F -> (44, 10), // Upper Slab
      0xF0 -> (44, 2), // Lower Slab
      0xAF -> (53, 0), // X |\
      0x5F -> (53, 1), // X /|
      0x3F -> (53, 2), // Z |\
      0xCF -> (53, 3), // Z /|
      0xF5 -> (53, 4), // X \|
      0xFA -> (53, 5), // X |/
      0xF3 -> (53, 6), // Z \|
      0xFC -> (53, 7), // Z |/
    )

    val voxelMap:mutable.HashMap[UnitVoxel, Int] = mutable.HashMap[UnitVoxel, Int]()
    for (v <- voxels) {
      val voxel:Vector3 = Vector3(v.x, v.y, v.z)
      val parentVoxel = UnitVoxel(voxel.x.toInt, voxel.y.toInt, voxel.z.toInt)

      val voxelByte:Int = voxelMap.getOrElseUpdate( parentVoxel, 0 )

      val dX: Int = Math.round(voxel.x - parentVoxel.x).toInt
      val dY: Int = Math.round(voxel.y - parentVoxel.y).toInt
      val dZ: Int = Math.round(voxel.z - parentVoxel.z).toInt

      voxelMap.put( parentVoxel, voxelByte | ocTable(dX)(dY)(dZ) )
      //println(s"ocTable($dX)($dY)($dZ) -> ${ocTable(dX)(dY)(dZ)} | $voxelByte = ${voxelByte | ocTable(dX)(dY)(dZ)}")
    }

    for ((v, bytes) <- voxelMap) {
      val (b:Int, bd:Int) = blocks.getOrElse(bytes, (43, 2))
      if (b != 0) println(s"mc.setBlock(spot + Vec3(${v.x}, ${v.y}, ${v.z}), $b, $bd) # $bytes")
    }
    */
  }
}

/*{
        val perfect = Integer.bitCount(bytes)
        var minBlock = blocks.keySet.head
        var minDist:Int = perfect - Integer.bitCount(minBlock & bytes)
        for (block <- blocks.keySet.tail) {
          val tempDist = perfect - Integer.bitCount(minBlock & bytes)
          if (tempDist < minDist) {
            minDist = tempDist
            minBlock = block
          }
        }
        blocks(minBlock)
      }
 */