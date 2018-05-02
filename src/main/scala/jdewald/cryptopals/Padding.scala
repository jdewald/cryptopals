package jdewald.cryptopals

/**
  * Created by jdewald on 5/1/18.
  */
object Padding {

  def pkcs7(blockSize: Int, data: Traversable[Byte]): Traversable[Byte] = {
    Stream.concat(data, List.fill(blockSize - data.size)((blockSize - data.size).asInstanceOf[Byte]))
  }
}
