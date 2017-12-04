package jdewald.cryptopals

import scala.collection.mutable

/**
  * Created by jdewald on 12/2/17.
  */
object Base64 {
  val B64_MAP = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toList

  def encode(bytes: Traversable[Byte]): String = {
    val out = new StringBuilder()

    var idx = 0
    var prev = 0
    bytes foreach { b =>
      idx match {
        case 0 => out += B64_MAP((b & 0xFC) >> 2) // 6 bits from MSB
        case 1 => out += B64_MAP(((prev & 0x3) << 4) + ((b & 0xF0) >> 4)) // 2 from prev, 4 from MSB
        case 2 =>
          out += B64_MAP(((prev & 0xF) << 2) + ((b & 0xC0) >> 6)) // 4 from prev LSB, 2 from MSB
          out += B64_MAP(b & 0x3F) // 6 from LSB
      }
      idx = (idx + 1) % 3
      prev = b
    }
    if (idx > 0) { // we still  have bytes left
     idx match {
       case 1 => out += B64_MAP((prev & 0x3) << 4)
       case _ => throw new RuntimeException("Foo")
     }
    }
    while (idx > 0) {
      out += '='
      idx = (idx + 1) % 3
    }
    out.mkString
  }

  def decode(encoded: String): Traversable[Byte] = {
    val out = mutable.MutableList.empty[Byte]

    var idx = 0
    var prev = 0
    // each character represents 6 bytes from the original
    encoded foreach { c =>
      var byteVal = B64_MAP.indexOf(c)
      idx match {
        case 0 =>
          byteVal <<= 2
          prev = byteVal
        case 1 =>
          out += (prev + ((byteVal & 0x30) >> 4)).toByte
          prev = (byteVal & 0xF) << 4
        case 2 =>
          out += (prev + ((byteVal & 0x3C) >> 2)).toByte
          prev = (byteVal & 0x3) << 6
        case 3 =>
          out += (prev + byteVal).toByte
          prev = 0
      }
      idx = (idx + 1) % 4
    }
    out
  }
}
