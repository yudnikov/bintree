package ru.yudnikov.bintree

import scala.util.Random

/**
  * Created by igor.yudnikov on 17-May-17.
  */
class NonEmpty[T](val compare: (T, T) => Boolean)
                 (val value: T, val left: Node[T] = new Empty[T](compare), val right: Node[T] = new Empty[T](compare))
  extends Node[T] {
  
  override def collect: List[T] = List(left.collect, List(value), right.collect).flatten
  
  override def contains(x: T): Boolean = {
    if (!compare(x, value))
      left.contains(x)
    else if (compare(x, value))
      right.contains(x)
    else
      true
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
  
  override def include(x: T): NonEmpty[T] = {
    if (!compare(x, value))
      withLeft(left.include(x))
    else if (compare(x, value))
      withRight(right.include(x))
    else
      this
  }
  
  override def include(x: T*): NonEmpty[T] = x.tail match {
    case Nil => include(x.head)
    case tail: List[T] => include(x.head).include(tail: _*)
  }
  
  override def merge(other: Node[T]): Node[T] = left.merge(right).merge(other).include(value)
  
  override def remove(x: T): Node[T] = {
    if (!compare(x, value))
      withLeft(left.remove(x))
    else if (compare(x, value))
      withRight(right.remove(x))
    else
      left.merge(right)
  }
  
  override def sortBy(f: (T, T) => Boolean): Node[T] = {
    val elements = Random.shuffle(collect)
    new NonEmpty[T](f)(elements.head).include(elements.tail: _*)
  }
  
  override def toString: String = s"{$left $value $right}"
  
  override def withOrderFunction(f: (T, T) => Boolean): Node[T] = {
    new NonEmpty[T](f)(value, left.withOrderFunction(f), right.withOrderFunction(f))
  }
  
  def withLeft(left: Node[T]): NonEmpty[T] = new NonEmpty(compare)(value, left, right)
  
  def withRight(right: Node[T]): NonEmpty[T] = new NonEmpty(compare)(value, left, right)
}
