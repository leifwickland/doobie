package doobie.util

/** Module for source positions. */
object pos {

  /** A source position. */ 
  case class Pos(path: String, line: Int) {

    def file: String =
      path.lastIndexOf(java.io.File.separatorChar) match {
        case -1 => path
        case n  => path.substring(n + 1)
      }

    override def toString =
      s"$file:$line"

  }

  object Pos {

    /** A `Pos` can be forged on demand. */
    implicit def sourcePos(implicit ef: sourcecode.File, el: sourcecode.Line): Pos =
      Pos(ef.value, el.value)

  }

}
