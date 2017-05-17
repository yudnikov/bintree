package ru.yudnikov.bintree

import ru.yudnikov.bintree

/**
  * Created by igor.yudnikov on 17-May-17.
  */
class NonEmpty(val value: Int, val left: Node = Empty, val right: Node = Empty) extends Node {

  override def toString: String = s"{$left $value $right}"

  override def include(x: Int): Node = {
    if (x < value) withLeft(left.include(x))
    else if (x > value) withRight(right.include(x))
    else this
  }

  override def contains(x: Int): Boolean = {
    if (x < value) left.contains(x)
    else if (x > value) right.contains(x)
    else true
  }

  def withLeft(left: Node): NonEmpty = new NonEmpty(value, left, right)

  def withRight(right: Node): NonEmpty = new NonEmpty(value, left, right)

  override def union(other: NonEmpty): NonEmpty = {
    if (other.value < value) withLeft(left.union(other))
    else if (other.value > value) withRight(right.union(other))
    else this
  }
}
