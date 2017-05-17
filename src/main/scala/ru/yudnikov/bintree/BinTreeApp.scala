package ru.yudnikov.bintree

/**
  * Created by igor.yudnikov on 17-May-17.
  */
object BinTreeApp extends App {
  
  val x = new NonEmpty(12) include 2 include 45 include 3
  val y = new NonEmpty(6) include 13 include 0 include 5
  val z = x.merge(y).remove(13).remove(12).remove(0)

  println(z)
}
