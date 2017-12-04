package jdewald.cryptopals

/**
  * Created by jdewald on 12/3/17.
  */
object TextScoring {

  val LETTER_ORDER = "etao"
  val AVG_WORD_LEN = 5

  /**
    * Calculate average word length of some text
    * @param a
    * @return
    */
  def wordLen(a: String): Int = {
    if (! a.contains(" ")) a.length
    else a.length() / a.replaceAll("[^ ]", "").length
  }

}
