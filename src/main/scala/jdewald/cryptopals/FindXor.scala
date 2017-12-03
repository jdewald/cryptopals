package jdewald.cryptopals

/**
  * Created by jdewald on 12/3/17.
  * Attempt to decrypt the passed in bytes assumig that there has been a single-byte xor
  */
object FindXor {

  def findXorDecrypt(a: Traversable[Byte]): Option[String] = {
    ('!' to '~').map( c => Xor.xor(a, c.toByte))
      .map(_.map(_.toChar).mkString) // byte->char strings
      .map( s=>(TextScoring.wordLen(s), s))
      .sorted // word len, smallest likely right, but really closest to 5
      .collectFirst( { case c if c._1 >=3 && c._1 <= 6 => c._2 })
  }

  def main(args: Array[String]): Unit = {

    // argument is assumed to be a hex-encoded byte array
    println("\tDecrypting " + args(0))
    val found = findXorDecrypt(Hex.bytes(args(0)))
    found.foreach(println)
  }
}


