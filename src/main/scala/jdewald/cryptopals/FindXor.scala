package jdewald.cryptopals

/**
  * Created by jdewald on 12/3/17.
  * Attempt to decrypt the passed in bytes assumig that there has been a single-byte xor
  */
object FindXor {

  def findXorDecrypt(a: Traversable[Byte]): String = {
    ('A' to 'z').map( c => Xor.xor(a, c.toByte))
      .map(_.map(_.toChar).mkString) // byte->char strings
      .map( s=>(TextScoring.wordLen(s), s))
      .sorted // word len, smallest likely right, but really closest to 5
      .apply(0)._2
  }

  def main(args: Array[String]): Unit = {

    // argument is assumed to be a hex-encoded byte array
    val found = findXorDecrypt(Hex.bytes(args(0)))
    print("$found\n")
  }
}


