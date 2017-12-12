package jdewald.cryptopals

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

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


  def main(args: Array[String]): Unit = {
    val key = args(0)
    val encrypted = Base64.decode(io.Source.stdin.mkString).toArray

    println(AES.decryptecb(encrypted, key.map(_.toByte)).map(_.toChar).mkString)
  }
}
