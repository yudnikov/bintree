package ru.yudnikov.bintree

/**
  * Created by igor.yudnikov on 17-May-17.
  */
class Empty[T](val greater: (String, (T, T) => Boolean)) extends Node[T] {

  override def toString: String = "."

  override def include(x: T): NonEmpty[T] = new NonEmpty(greater)(x)

  override def contains(x: T): Boolean = false

  override def merge(other: Node[T]): Node[T] = other
  
  override def remove(x: T): Node[T] = this
  
  override def filter(f: (T) => Boolean): Node[T] = this
  
  override def foreach(f: (T) => Unit): Unit = {}
  
  override def sortBy(f: (String, (T, T) => Boolean)): Node[T] = this
  
  override def withGreaterFunction(f: (String, (T, T) => Boolean)): Node[T] = new Empty(greater)
}
