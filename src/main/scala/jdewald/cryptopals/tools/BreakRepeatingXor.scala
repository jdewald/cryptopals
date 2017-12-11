package jdewald.cryptopals.tools

import jdewald.cryptopals._

/**
  * Created by jdewald on 12/3/17.
  */
object BreakRepeatingXor {

  def findKey(encrypted: Iterable[Byte], keyLen: Int): Iterable[Byte] = {


    println(s"Phase 2 - Break into blocks and transpose")


    val blocks = encrypted.grouped(keyLen).filter(_.size == keyLen)

    // This now should give us blocks where each block is the nth byte of each
    val transposed = blocks.toStream.transpose

    // This may not work as it's based on smaller word sizes
    val xorSolved = transposed.map(FindXor.xorAndEnglishScore(_).find(_ => true).get._3)

    //xorSolved.foreach(println)
    //val estimatedKeySize = findKeySize(encrypted, startKeySize, endKeySize)
    xorSolved
  }
  def findDistances(enc: Iterable[Byte], start: Int, end: Int): Iterable[(Int, Int)] = {

    (start to end).map( { size =>
      // TODO: this seems comically inefficient
      val iter = enc.grouped(size)
//      val block1 = iter.take(size).toTraversable
//      val block2 = iter.take(size).toTraversable
      val block1 = iter.next()
      val block2 = iter.next()

      val block3 = iter.next()
      val block4 = iter.next()

      val block5 = iter.next()
      val block6 = iter.next()

      val h1 = Hamming.bitDistance(block1, block2)
      val h2 = Hamming.bitDistance(block3, block4)
      val h3 = Hamming.bitDistance(block5, block6)

      (size, (100 * (((h1 + h2 + h3).toDouble / 3.0) / size.toDouble)).round.toInt)
    }).sortBy(_._2)
  }

  def main(args: Array[String]): Unit = {
    val startKeySize = args(0).toInt
    val endKeySize = args(1).toInt
    println(s"Phase 1 - Guessing key size from $startKeySize to $endKeySize bits")

    val encrypted = Base64.decode(io.Source.stdin.mkString).toArray
    //val encrypted = io.Source.stdin.toArray.map(_.toByte)
    val distances = findDistances(encrypted, startKeySize, endKeySize)
    val block = distances.find( _ => true ).get

    println(s"\tKey size appears to be: ${block._1}")

    val key = findKey(encrypted, block._1)
    println(s"Key seems to be: -[${key.map(_.toChar).mkString}]-")

    println("==== Plaintext =====")
    println(Xor.xor(encrypted, key).map(_.toChar).mkString)
    println("====================")
  }
}
