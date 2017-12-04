package jdewald.cryptopals

/**
  * Created by jdewald on 12/3/17.
  */
object Hamming {

  /**
    * Calculates how many bits differ
    * @param a
    * @param b
    * @return
    */
  def bitDistance(a: Byte, b:Byte): Int = {
    (0 to 7).map( i => ((a ^ b) >> i ) & 0x1).sum
  }

  def bitDistance(a: Traversable[Byte], b: Traversable[Byte]): Int = {
    (a.toIterable zip b.toIterable)
      .map( x => bitDistance(x._1, x._2))
      .sum
  }
}
