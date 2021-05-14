package ai.dragonfly.graphics

import ai.dragonfly.color.Color


case class Specular(color: Color = Color.GRAY, exponent: Float = 10f) {
  def mtl: String = s"Ks ${color.red / 255f} ${color.green / 255f} ${color.blue / 255f}\nNs $exponent\n"
}

object Material {
  val default:Material = Material("metal", Color.GRAY)
}

case class Material(name: String, diffuse: Color, ambient: Color = Color.GRAY, specular: Specular = Specular(), dissolve: Float = 1f) {
  def mtl: String = {
    val sb: StringBuilder = new StringBuilder()
    sb.append(s"newmtl $name\n")
    sb.append(s"Ka ${ambient.red / 255f} ${ambient.green / 255f} ${ambient.blue / 255f}\n")
    sb.append(s"Kd ${diffuse.red / 255f} ${diffuse.green / 255f} ${diffuse.blue / 255f}\n")
    sb.append(specular.mtl)
    sb.append(s"d $dissolve\n")
    sb.append("illum 1\n")
    sb.toString()
  }
}