package jdewald.cryptopals

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by jdewald on 12/2/17.
  */
object Hex {

  val HEX_MAP: List[Char] = "0123456789ABCDEF".toList
  /**
    * Takes pairs of hex chars and outputs a stream of bytes
    * @param hexChars
    * @return
    */
  def bytes(hexChars: Traversable[Char]): Traversable[Byte] = {
    val out = mutable.MutableList.empty[Byte]
    var first = true
    var nibble = 0
    hexChars.foreach { c =>
      val d = HEX_MAP.indexOf(c.toUpper)
      if (first) nibble = d * 16
      else {
        nibble += d
        out += nibble.toByte
      }
      first = !first
    }
    out
  }

  def chars(hexBytes: Traversable[Byte]): Traversable[Char] = {
    val out = mutable.MutableList.empty[Char]

    hexBytes.foreach { b =>
      val n1 = b & 0x0F
      val n2 = (b & 0xF0) >> 4
      out += HEX_MAP(n2)
      out += HEX_MAP(n1)
    }
    out
  }


}
