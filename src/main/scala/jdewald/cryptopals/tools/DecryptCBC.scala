package jdewald.cryptopals.tools

import jdewald.cryptopals.{AES, Base64}

/**
  * Created by jdewald on 5/1/18.
  */
object DecryptCBC {
  def main(args: Array[String]): Unit = {

    val passphrase = args(0)
    val encrypted = Base64.decode(io.Source.stdin.mkString).toArray

    println(AES.decryptCBC(encrypted, passphrase.getBytes, List.fill(passphrase.size)(0.toByte))
        .map(_.toChar).mkString
    )

  }

}
