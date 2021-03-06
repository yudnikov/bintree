package ru.yudnikov.bintree

/**
  * Created by igor.yudnikov on 17-May-17.
  */
class Empty[T](val compare: (T, T) => Boolean) extends Node[T] {

  override def collect: List[T] = List()

  override def contains(x: T): Boolean = false

  override def filter(f: (T) => Boolean): Node[T] = this

  override def foreach(f: (T) => Unit): Unit = {}
  
  override def include(x: T): NonEmpty[T] = new NonEmpty(compare)(x)
  
  override def include(x: T*): NonEmpty[T] = new NonEmpty[T](compare)(x.head).include(x.tail: _*)
  
  override def merge(other: Node[T]): Node[T] = other
  
  override def remove(x: T): Node[T] = this
  
  override def sortBy(f: (T, T) => Boolean): Node[T] = this
  
  override def toString: String = "."
  
  override def withOrderFunction(f: (T, T) => Boolean): Node[T] = new Empty(compare)
}
