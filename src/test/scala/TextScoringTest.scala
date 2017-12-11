import jdewald.cryptopals.TextScoring
import org.scalatest.{Matchers, WordSpecLike}

/**
  * Created by jdewald on 12/10/17.
  */
class TextScoringTest extends WordSpecLike with Matchers{

  "An English scorer" should {
    "Give a good score to English text" in {
      TextScoring.testEnglish(
        """
          |It is a long established fact that a reader will be distracted by the
          |readable content of a page when looking at its layout. The point of
          |using Lorem Ipsum is that it has a more-or-less normal distribution of
          |letters, as opposed to using 'Content here, content here', making it
          |look like readable English. Many desktop publishing packages and web
          |page editors now use Lorem Ipsum as their default model text, and a
          |search for 'lorem ipsum' will uncover many web sites still in their
          |infancy. Various versions have evolved over the years, sometimes by
          |accident, sometimes on purpose (injected humour and the like).
        """.stripMargin) shouldBe <= (1.0d)
    }
    "Give a low score to random characters" in {
      TextScoring.testEnglish(
        """
          |lfkjdlfkaj fkfa f 48hsaflasjf fkaflk4 fkl4kskfa fbabjfsf
          |lk34j  8f8rnfa vkgyvsjvanglajb aklfanfnawlvkano  dsfkaljflakjr lfajflka
          |flkn vnvmnlajeoajfakf
        """.stripMargin
      ) shouldBe >= (5.0d)
    }
  }
}
