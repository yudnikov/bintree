package ru.yudnikov.bintree

/**
  * Created by igor.yudnikov on 17-May-17.
  */
object BinTreeApp extends App {
  
  implicit def greater[T]: (T, T) => Boolean = (x, y) => x.asInstanceOf[Int] > y.asInstanceOf[Int]
  def greaterOrEqual[T]: (T, T) => Boolean = (x, y) => x.asInstanceOf[Int] >= y.asInstanceOf[Int]
  
  val x = new NonEmpty(12) include 2 include 45 include 3
  val y = new NonEmpty(6) include 13 include 0 include 5
  val z = x.merge(y).filter(greaterOrEqual(12, _))
  println(z)
}
