package ru.yudnikov.bintree

/**
  * Created by igor.yudnikov on 17-May-17.
  */
abstract class Node {

  def include(x: Int): NonEmpty

  def contains(x: Int): Boolean

  def merge(other: Node): Node
  
  def remove(x: Int): Node
}
