package ru.yudnikov.bintree

/**
  * Created by igor.yudnikov on 17-May-17.
  */
class NonEmpty[T](val greater: (String, (T, T) => Boolean))
                 (val value: T, val left: Node[T] = new Empty[T](greater), val right: Node[T] = new Empty[T](greater))
  extends Node[T] {
  
  override def toString: String = s"{$left $value $right}"
  
  def withLeft(left: Node[T]): NonEmpty[T] = new NonEmpty(greater)(value, left, right)
  
  def withRight(right: Node[T]): NonEmpty[T] = new NonEmpty(greater)(value, left, right)
  
  override def include(x: T): NonEmpty[T] = {
    if (!greater._2(x, value) && x != value)
      withLeft(left.include(x))
    else if (greater._2(x, value) && x != value)
      withRight(right.include(x))
    else
      this
  }
  
  override def contains(x: T): Boolean = {
    if (!greater._2(x, value) && x != value)
      left.contains(x)
    else if (greater._2(x, value) && x != value)
      right.contains(x)
    else
      true
  }
  
  override def merge(other: Node[T]): Node[T] =
    left.merge(right).merge(other).include(value)
  
  override def remove(x: T): Node[T] = {
    if (!greater._2(x, value) && x != value)
      withLeft(left.remove(x))
    else if (greater._2(x, value) && x != value)
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
  
  override def foreach(f: (T) => Unit): Unit = {
    left.foreach(f)
    f(value)
    right.foreach(f)
  }
  
  override def withGreaterFunction(f: (String, (T, T) => Boolean)): Node[T] =
    new NonEmpty[T](f)(value, left.withGreaterFunction(f), right.withGreaterFunction(f))
  
  override def sortBy(f: (String, (T, T) => Boolean)): Node[T] =
    left.sortBy(f).merge(right.sortBy(f)).withGreaterFunction(f).include(value)
  
}
