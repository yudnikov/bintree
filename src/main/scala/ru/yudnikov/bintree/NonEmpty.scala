package ru.yudnikov.bintree

/**
  * Created by igor.yudnikov on 17-May-17.
  */
class NonEmpty(val value: Int, val left: Node = Empty, val right: Node = Empty) extends Node {
  
  override def toString: String = s"{$left $value $right}"
  
  def withLeft(left: Node): NonEmpty = new NonEmpty(value, left, right)
  
  def withRight(right: Node): NonEmpty = new NonEmpty(value, left, right)
  
  override def include(x: Int): NonEmpty = {
    if (x < value)
      withLeft(left.include(x))
    else if (x > value)
      withRight(right.include(x))
    else
      this
  }
  
  override def contains(x: Int): Boolean = {
    if (x < value)
      left.contains(x)
    else if (x > value)
      right.contains(x)
    else
      true
  }
  
  override def merge(other: Node): Node =
    left.merge(right).merge(other).include(value)
  
  override def remove(x: Int): Node = {
    if (x < value)
      withLeft(left.remove(x))
    else if (x > value)
      withRight(right.remove(x))
    else
      left.merge(right)
  }
  
  override def filter(p: (Int) => Boolean): Node = {
    if (p(value))
      withLeft(left.filter(p)).withRight(right.filter(p))
    else
      left.filter(p).merge(right.filter(p))
  }
}
