import jdewald.cryptopals.Xor
import jdewald.cryptopals.tools.BreakRepeatingXor
import org.scalatest.{Matchers, WordSpecLike}

/**
  * Created by jdewald on 12/9/17.
  */
class BreakRepeatingXorTest extends WordSpecLike with Matchers {

  "A Repeated Xor Breaker" should {
    val plaintext =
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id"
        .toCharArray.map(_.toByte)

    /*"Detect 3 byte key" in {
      val key = "hel"
      val encrypted = Xor.xor(plaintext, key.map(_.toByte)).toIterable

      val distances = BreakRepeatingXor
        .findDistances(encrypted, 1, 30)

      distances.foreach(println)

      distances.find(_ => true).map(_._1) should contain (3)


    }
    */
    "Detect 5 byte key" in {
      // It turns out that the Hamming-based key length detection sucks quite
      // a bit when you try to use something where the bit distances between
      // the keys characters are low, e.g. 'abcdef'
      val key = "hello"
      val encrypted = Xor.xor(plaintext, key.map(_.toByte)).toIterable

      val distances = BreakRepeatingXor
        .findDistances(encrypted, 1, 30)

      distances.find(_ => true).map(_._1) should contain (5)


    }
    "Detect and break 5 byte key" in {

      val key = "hello"
      val encrypted = Xor.xor(plaintext, key.map(_.toByte)).toIterable

      val distances = BreakRepeatingXor
        .findDistances(encrypted, 1, 30)
      val keyLen = distances.find(_ => true).map(_._1).get
      val foundKey = BreakRepeatingXor.findKey(encrypted, keyLen)

      foundKey.map(_.toChar).mkString shouldBe key
    }

    "Break 2 byte key" in {
      val key = "ab"
      val encrypted = Xor.xor(plaintext, key.map(_.toByte)).toIterable

      BreakRepeatingXor.findKey(encrypted, key.length)
        .toVector.map(_.toChar).mkString should be (key)
    }
    "Break 3 byte key" in {
      val key = "abc"
      val encrypted = Xor.xor(plaintext, key.map(_.toByte)).toIterable

      BreakRepeatingXor.findKey(encrypted, key.length)
        .toVector.map(_.toChar).mkString should be (key)
    }
    "Break 4 byte key" in {
      val key = "abcd"
      val encrypted = Xor.xor(plaintext, key.map(_.toByte)).toIterable

      BreakRepeatingXor.findKey(encrypted, key.length)
        .toVector.map(_.toChar).mkString should be (key)
    }
  }

}
