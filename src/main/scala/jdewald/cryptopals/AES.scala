package jdewald.cryptopals

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

import scala.collection.mutable

/**
  * Created by jdewald on 12/11/17.
  */
object AES {

  def decryptecb(plaintext: Iterable[Byte], key: Iterable[Byte]): Iterable[Byte] = {

    val cipher = Cipher.getInstance("AES/ECB/NoPadding")
    val secret = new SecretKeySpec(key.toArray, "AES")
    cipher.init(Cipher.DECRYPT_MODE, secret)

    cipher.doFinal(plaintext.toArray)
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
