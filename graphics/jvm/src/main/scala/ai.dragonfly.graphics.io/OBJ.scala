package ai.dragonfly.graphics.io

import java.io.{File, PrintWriter}

object OBJ {

  def writeFile(obj: String, name:String): Unit = writeFile(obj, name, new File(s"./obj/$name.obj"))

  def writeFile(obj: String, name:String, file: File): File = {
    val pw = new PrintWriter(file)
    pw.write(obj)
    pw.close()
    file
  }
}
