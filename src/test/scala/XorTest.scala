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
    "Perform repeated XOR" in {
      Hex.chars(
        Xor.xor(
          "Burning 'em, if you ain't quick and nimble\nI go crazy when I hear a cymbal".getBytes,
          "ICE".getBytes
        )
      ).mkString should equal ("0b3637272a2b2e63622c2e69692a23693a2a3c6324202d623d63343c2a26226324272765272a282b2f20430a652e2c652a3124333a653e2b2027630c692b20283165286326302e27282f")
    }
  }
}
