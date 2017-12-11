package jdewald.cryptopals

import scala.collection.{Map, mutable}

/**
  * Created by jdewald on 12/3/17.
  */
object TextScoring {

  val LETTER_ORDER = "etao"
  //https://www.math.cornell.edu/~mec/2003-2004/cryptography/subs/frequencies.html
  val ENGLISH_LETTER_FREQS = Map(
    'e' -> .1202,
    't' -> .0910,
    'a' -> .0812,
    'o' -> .0868,
    'i' -> .0731,
    'n' -> .0695,
    's' -> .0628,
    'r' -> .0602,
    'h' -> .0592,
    'd' -> .0432,
    'l' -> .0398,
    'u' -> .0288,
    'c' -> .0271,
    'm' -> .0261,
    'f' -> .0230,
    'y' -> .0211,
    'w' -> .0209,
    'g' -> .0203,
    'p' -> .0182,
    'b' -> .0149,
    'v' -> .0111,
    'k' -> .0069,
    'x' -> .0017,
    'q' -> .0011,
    'j' -> .0010,
    'z' -> .0007
  )
  val AVG_WORD_LEN = 5

  /**
    * Calculate average word length of some text
    * @param a
    * @return
    */
  def wordLen(a: String): Int = {
    println(a)
    if (! a.contains(" ")) a.length
    else a.length() / a.replaceAll("[^ ]", "").length
  }

  def letterFrequencies(a: String): Map[Char, Double] = {
    a.toLowerCase.replaceAll("[0-9\\s'\",.()]", "").foldLeft(new mutable.HashMap[Char, Int])((m, c) => {
      m(c) = m.getOrElseUpdate(c, 0) + 1
      m
    }).mapValues( _.toDouble / a.length().toDouble)
  }
  /**
    * Calculate the likelihood using a Chi-squared test
    * that the letter frequencies match what we expect
    * Naturally larger texts are better. For example you need
    * around 15 letters before you would expect to see a single z
    * @param freqs - table of actual letter frequencies
    * @return
    */
  def letterFreqProb(freqs: Map[Char, Double]): Double = {
    freqs.foldLeft(0.0)( (s, ci) => {
      val exp = ENGLISH_LETTER_FREQS.getOrElse(ci._1, 0.0001d)
      val chis = (Math.pow(ci._2 - exp, 2) / exp)
      //println(s"${ci._1}=>${ci._2} ($exp)->$chis")
      s + chis
    }
    )
  }

  def testEnglish(a: String): Double = letterFreqProb(letterFrequencies(a))

}
