import jdewald.cryptopals.AES
import org.scalatest.{Matchers, WordSpecLike}

/**
  * Created by jdewald on 12/11/17.
  */
class AESTest extends WordSpecLike with Matchers{

  "An AES crypto library" should {
    "Encrypt and decrypt via ECB" in {

      val pass="foobarbazagainfo".getBytes
      AES.decryptecb(
        AES.encryptecb(
          "YELLOW SUBMARINE".getBytes,
          pass
        ),
        pass
      ) should be ("YELLOW SUBMARINE".getBytes)
    }

    "Encrypt and decrypt using Cipher Block Chaining (ECB)" in {
      val pass = "0123456789ABCDEF".getBytes

      val iv = List.fill(pass.size)(0.toByte)

      AES.decryptCBC(
        AES.encryptCBC(
          "YELLOW SUBMARINE IS AWESOME".getBytes,
          pass,
          iv
        ),
        pass,
        iv

      )
    }

  }



}
