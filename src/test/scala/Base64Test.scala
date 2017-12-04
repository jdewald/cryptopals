import jdewald.cryptopals.{Base64, Hex}
import org.scalatest.{Matchers, WordSpecLike}
import org.scalatest.Matchers._

/**
  * Created by jdewald on 12/2/17.
  */
class Base64Test extends WordSpecLike with Matchers {
  "A Base64 Encoder" should {
    "Encode bytes" in {
      Base64.encode(Seq(77,97,110)) shouldBe "TWFu"
    }
    "Add padding" in {
      Base64.encode(Seq(77)) shouldBe "TQ=="
    }

    "Encode Cryptopals Challenge 1" in {
      val hb = Hex.bytes("49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d")
      val b64 = Base64.encode(hb)
      b64 shouldBe "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t"
    }
  }
  "A Base64 Decoder" should {
    "Decode base64" in {
      Base64.decode("TWFu").toSeq should equal (Seq(77,97,110))
    }
  }
}
