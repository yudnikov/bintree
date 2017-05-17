package ru.yudnikov.bintree

/**
  * Created by igor.yudnikov on 17-May-17.
  */
object BinTreeApp extends App {
  
  //implicit def greater[T]: (T, T) => Boolean = (x, y) => x.asInstanceOf[Int] > y.asInstanceOf[Int]
  //def greaterOrEqual[T]: (T, T) => Boolean = (x, y) => x.asInstanceOf[Int] >= y.asInstanceOf[Int]
  
  implicit def greater[T]: (T, T) => Boolean = (x, y) => x.asInstanceOf[String] > y.asInstanceOf[String]
  def greaterOrEqual[T]: (T, T) => Boolean = (x, y) => greater(x, y) | x == y
  
  val x = new NonEmpty("12") include "2" include "45" include "3"
  val y = new NonEmpty("mama") include "13" include "zero" include "5"
  val z = x.merge(y)
  println(z)
  println(z.filter(greaterOrEqual("13", _)).include("zzz"))
  z.foreach(x => println(s"value is: $x"))
}
