import org.scalatest.{Matchers, WordSpecLike}
import org.scalatest.Matchers._
import jdewald.cryptopals.Hex
/**
  * Created by jdewald on 12/2/17.
  */
class HexTest extends WordSpecLike with Matchers {

  "A Hex Converter" should {
    "Convert byte to string" in {
      Hex.chars(Vector(0xAB.toByte)).toSeq should equal (Seq('a','b'))
    }


    "Convert hex chars to bytes" in {
      Hex.bytes("4d".toCharArray).toSeq should equal (Seq(0x4D.toByte))
    }


  }

}
