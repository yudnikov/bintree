package ru.yudnikov.bintree

import ru.yudnikov.bintree.tweet.Tweet

/**
  * Created by igor.yudnikov on 17-May-17.
  */
object BinTreeApp extends App {
  
  def greater[T]: (T, T) => Boolean = (x, y) => x.asInstanceOf[String] > y.asInstanceOf[String]
  def longer[T]: (T, T) => Boolean = (x, y) => x.asInstanceOf[String].length > y.asInstanceOf[String].length
  
  val x = new NonEmpty[String](greater)("12") include "2" include "45" include "3" include "zzz" include "1" include "100000"
  println(s"x: $x")
  val y = x.sortBy(longer)
  println(s"y: $y")
  val z = y.sortBy(greater)
  println(s"z: $z")
  
  println(x.collect)
  
  
  def compareByText[T]: (T, T) => Boolean = _.asInstanceOf[Tweet].text > _.asInstanceOf[Tweet].text
  def compareByText2[T]: (T, T) => Boolean = _.asInstanceOf[Tweet].text.toLowerCase > _.asInstanceOf[Tweet].text.toLowerCase
  def compareByRetweets[T]: (T, T) => Boolean = _.asInstanceOf[Tweet].retweets < _.asInstanceOf[Tweet].retweets
  
  val tweets = List(
    Tweet("Hello, world!", 100),
    Tweet("ABC is the alphabet", 13),
    Tweet("getthereveryfastindeed", 1),
    Tweet("ZZ Top", 2),
    Tweet("AAA! Help", 999)
  )
  
  //val t = new NonEmpty[Tweet](compareByText)(tweets.head) include tweets(1) include tweets(2) include tweets(3) include tweets(4)
  val t = new NonEmpty(compareByRetweets)(tweets.head).include(tweets.tail: _*)
  println(t)
  println(t.sortBy(compareByText2))
  println(t.sortBy(compareByRetweets))
  println(t.sortBy(compareByText2).sortBy(compareByRetweets))
  
}
