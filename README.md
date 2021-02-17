# graphics
A Scala/Scala.js library that provides accessible functionality to procedurally generate 3D meshes, materials, and export them as OBJ and MTL files from web servers and from within browsers.

To use this library with SBT:
<pre>
resolvers += "dragonfly.ai" at "https://code.dragonfly.ai/"
libraryDependencies += "ai.dragonfly.code" %% "graphics" % "0.1"
</pre>

Example Use:
```scala

import ai.dragonfly.color.Color
import ai.dragonfly.graphics._
import shape._

/** Let's make a 3D Model of a Threaded Bolt and export it to the OBJ file format. */

object Test extends App {
  // create a material:
  val metalMtl = Material("metal", Color.GRAY)

  // create a mesh:
  val tBolt = ThreadedBolt("A_Threaded_Bolt", 4.0, 10, metalMtl, metalMtl)

  // write obj file text to standard output:
  println(tBolt.toObj())
}

```

For more info, see the: <a href="https://dragonfly.ai/doc/graphics/2.13/0.1/ai/dragonfly/index.html">Scala Documentation</a>

