import jdewald.cryptopals.Hamming
import org.scalatest.{Matchers, WordSpecLike}

/**
  * Created by jdewald on 12/3/17.
  */
class HammingTest extends WordSpecLike with Matchers {

  "A Hamming class" should {
    "Calculate same value correctly" in {
      Hamming.bitDistance('a'.toByte, 'a'.toByte) shouldBe 0
    }
    "Calculate 1 bit distance" in {
      Hamming.bitDistance('b'.toByte, 'c'.toByte) shouldBe 1
    }
    "Calculate 2 bit distance" in {
      Hamming.bitDistance('a'.toByte, 'b'.toByte) shouldBe 2
    }
    "Calculate bit distance correctly" in {
      Hamming.bitDistance(
        "this is a test".getBytes,
        "wokka wokka!!!".getBytes
      ) shouldBe 37
    }
    "Calculate bit distance correctly on single byte array" in {
      Hamming.bitDistance(
        "a".getBytes,
        "b".getBytes
      ) shouldBe 2
    }
  }
}
