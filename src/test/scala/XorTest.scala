import jdewald.cryptopals.{Hex, Xor}
import org.scalatest.{Matchers, WordSpecLike}

/**
  * Created by jdewald on 12/3/17.
  */
class XorTest extends WordSpecLike with Matchers {

  "A XOR Function" should {
    "XOR 2 sequences of bytes" in {
      Hex.chars(
        Xor.xor(
          Hex.bytes("1c0111001f010100061a024b53535009181c"),
          Hex.bytes("686974207468652062756c6c277320657965")
        )
      ).mkString should equal ("746865206b696420646f6e277420706c6179")

    }
  }
}
