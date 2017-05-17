package ru.yudnikov.bintree

/**
  * Created by igor.yudnikov on 17-May-17.
  */
object Empty extends Node {

  override def toString: String = "."

  override def include(x: Int): NonEmpty = new NonEmpty(x)

  override def contains(x: Int): Boolean = false

  override def merge(other: Node): Node = other
  
  override def remove(x: Int): Node = this
}
