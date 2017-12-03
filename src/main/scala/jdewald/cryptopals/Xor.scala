package jdewald.cryptopals

/**
  * Created by jdewald on 12/3/17.
  */
object Xor {

  def xor(a:(Byte, Byte)): Byte = (a._1 ^ a._2).toByte

  def xor(a: Traversable[Byte], b: Traversable[Byte]): Traversable[Byte] = {
    val zipped = a.toList zip b.toList
    zipped.map(xor)
  }

  def xor(a: Traversable[Byte], b: Byte): Traversable[Byte] = {
    a.map( c => xor((c, b)))
  }
}
