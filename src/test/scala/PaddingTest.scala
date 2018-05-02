import jdewald.cryptopals.Padding
import org.scalatest.{Matchers, WordSpecLike}

/**
  * Created by jdewald on 5/1/18.
  */
class PaddingTest extends WordSpecLike with Matchers {

  "PKCS#7 Padding" should {

    "Add 4 bytes to pad 16 to 20" in {
      Padding.pkcs7(20, "YELLOW SUBMARINE".map(_.toByte))
        .toSeq should equal (
        new StringBuilder("YELLOW SUBMARINE")
          .append(4.toChar)
          .append(4.toChar)
          .append(4.toChar)
          .append(4.toChar)
          .toString.map(_.toByte))
    }

    "Return original when size matches" in {
      Padding.pkcs7(16, "YELLOW SUBMARINE".map(_.toByte)) should equal ("YELLOW SUBMARINE".map(_.toByte))
    }
  }
}
