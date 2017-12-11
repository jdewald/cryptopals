package jdewald.cryptopals

/**
  * Created by jdewald on 12/3/17.
  * Attempt to decrypt the passed in bytes assumig that there has been a single-byte xor
  */
object FindXor {

  // TODO: This is really a 'zip' that I'm weirdly manually doing
  def xorAndEnglishScore(a: Traversable[Byte]): Traversable[(Double, String, Byte)] = {
    (' ' to '~').map( c => (c, Xor.xor(a, c.toByte)))
      .map( x => (x._1, x._2.map(_.toChar).mkString)) // byte->char strings
      .map( s=>(TextScoring.testEnglish(s._2), s._2, s._1.toByte))
      .sorted
  }

  def findXorDecrypt(a: Traversable[Byte]): Option[String] = {
    xorAndEnglishScore(a)
      .find(_ => true)
      .map( _._2)
  }

  def main(args: Array[String]): Unit = {

    // argument is assumed to be a hex-encoded byte array
    println("\tDecrypting " + args(0))
    val found = findXorDecrypt(Hex.bytes(args(0)))
    found.foreach(println)
  }
}


