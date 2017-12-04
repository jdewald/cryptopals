package jdewald.cryptopals

/**
  * Created by jdewald on 12/3/17.
  */
object Xor {

  def xor(a:(Byte, Byte)): Byte = (a._1 ^ a._2).toByte

  /**
    * XORs each element of a with corrsponding element of b
    * Smaller of them should be passed as second value
    * @param a - contains "plaintext"
    * @param b - contains "password" to be hashed against
    * @return
    */
  def xor(a: Traversable[Byte], b: Traversable[Byte]): Traversable[Byte] = {
    (a.toIterable zip Stream.continually(b).flatten).map(xor)
  }

  def xor(a: Traversable[Byte], b: Byte): Traversable[Byte] = {
    a.map( c => xor((c, b)))
  }

}
