package ru.yudnikov.bintree

/**
  * Created by igor.yudnikov on 17-May-17.
  */
object BinTreeApp extends App {
  
  def greater[T]: (String, (T, T) => Boolean) =
    ("(x, y) => x > y", (x, y) => x.asInstanceOf[String] > y.asInstanceOf[String])
  def longer[T]: (String, (T, T) => Boolean) =
    ("(x, y) => x.length > y.length", (x, y) => x.asInstanceOf[String].length > y.asInstanceOf[String].length)
  
  val x = new NonEmpty(greater)("12") include "2" include "45" include "3" include "zzz" include "1" include "100000"
  println(s"x: $x")
  val y = x.sortBy(longer)
  println(s"y = x.sortBy(longer): $y")
  val z = y.sortBy(greater)
  println(s"z = y.sortBy(greater): $z")
  
}
