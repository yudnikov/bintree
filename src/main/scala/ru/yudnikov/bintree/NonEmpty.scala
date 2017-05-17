package ru.yudnikov.bintree

/**
  * Created by igor.yudnikov on 17-May-17.
  */
class NonEmpty[T](val value: T, val left: Node[T] = new Empty[T], val right: Node[T] = new Empty[T])
                 (implicit val greater: (T, T) => Boolean)
  extends Node[T] {
  
  override def toString: String = s"{$left $value $right}"
  
  def withLeft(left: Node[T]): NonEmpty[T] = new NonEmpty(value, left, right)
  
  def withRight(right: Node[T]): NonEmpty[T] = new NonEmpty(value, left, right)
  
  override def include(x: T): NonEmpty[T] = {
    if (!greater(x, value) && x != value)
      withLeft(left.include(x))
    else if (greater(x, value) && x != value)
      withRight(right.include(x))
    else
      this
  }
  
  override def contains(x: T): Boolean = {
    if (!greater(x, value) && x != value)
      left.contains(x)
    else if (greater(x, value) && x != value)
      right.contains(x)
    else
      true
  }
  
  override def merge(other: Node[T]): Node[T] =
    left.merge(right).merge(other).include(value)
  
  override def remove(x: T): Node[T] = {
    if (!greater(x, value) && x != value)
      withLeft(left.remove(x))
    else if (greater(x, value) && x != value)
      withRight(right.remove(x))
    else
      left.merge(right)
  }
  
  override def filter(p: (T) => Boolean): Node[T] = {
    if (p(value))
      withLeft(left.filter(p)).withRight(right.filter(p))
    else
      left.filter(p).merge(right.filter(p))
  }
}
