package jdewald.cryptopals

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

import scala.collection.mutable

/**
  * Created by jdewald on 12/11/17.
  */
object AES {

  def decryptecb(ciphertext: Iterable[Byte], key: Iterable[Byte]): Iterable[Byte] = {

    val cipher = Cipher.getInstance("AES/ECB/NoPadding")
    val secret = new SecretKeySpec(key.toArray, "AES")
    cipher.init(Cipher.DECRYPT_MODE, secret)

    cipher.doFinal(ciphertext.toArray)
  }

  def encryptecb(plaintext: Iterable[Byte], key: Iterable[Byte]): Iterable[Byte] = {

    val cipher = Cipher.getInstance("AES/ECB/NoPadding")
    val secret = new SecretKeySpec(key.toArray, "AES")
    cipher.init(Cipher.ENCRYPT_MODE, secret)

    cipher.doFinal(plaintext.toArray)
  }

  def decryptCBC: (Iterable[Byte], Iterable[Byte], Iterable[Byte]) => Iterable[Byte] = CBC(decryptecb)
  def encryptCBC: (Iterable[Byte], Iterable[Byte], Iterable[Byte]) => Iterable[Byte] = CBC(encryptecb)


  def CBC(fn: (Iterable[Byte], Iterable[Byte]) => Iterable[Byte])(cipherOrPlain: Iterable[Byte], key:Iterable[Byte], iv:Iterable[Byte]): Iterable[Byte] = {
    var prev = iv
    cipherOrPlain.grouped(key.size).map( block => {
      val text = Xor.xor(fn(block, key), prev)
      prev = block // the chain
      text
    }).flatten.toIterable
  }

  /*
    * Challen 1-8 Idea here is that as a common plaintext block
    * will produce the same ciphertext block, we just need to find
    * duplicate blocks. If found, likely is ECB
   */
  def isECB(ciphertext: Iterable[Byte]): Boolean = {
    ciphertext.grouped(16).foldLeft(new mutable.HashMap[String, Int])( (m,b) => {
      m.put(b.toString, m.getOrElseUpdate(b.toString, 0) + 1)
      m
    }).exists( _._2 > 1)
  }

  def main(args: Array[String]): Unit = {
    // 1-7
    //val key = args(0)
    //val encrypted = Base64.decode(io.Source.stdin.mkString).toArray

    //println(AES.decryptecb(encrypted, key.map(_.toByte)).map(_.toChar).mkString)

    // 1-8
    io.Source.stdin.getLines()
      .filter(line => isECB(Hex.bytes(line).toIterable))
        .foreach(println)

  }
}
