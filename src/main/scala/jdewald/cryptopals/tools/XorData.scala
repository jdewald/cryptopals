package jdewald.cryptopals.tools

import jdewald.cryptopals.{Hex, Xor}

/**
  * Created by jdewald on 12/3/17.
  */
object XorData {
  def main(args: Array[String]): Unit = {
    // xor stdin using password parameter
    val encrypted = Xor.xor(io.Source.stdin.toIterable.map(_.toByte), args(0).toTraversable.map(_.toByte))
    println(Hex.chars(encrypted).mkString)
    println(encrypted.map(_.toChar).mkString)
  }
}
