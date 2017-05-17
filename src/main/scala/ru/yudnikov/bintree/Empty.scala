package ru.yudnikov.bintree

import BinTreeApp._

/**
  * Created by igor.yudnikov on 17-May-17.
  */
class Empty[T] extends Node[T] {

  override def toString: String = "."

  override def include(x: T): NonEmpty[T] = new NonEmpty(x)

  override def contains(x: T): Boolean = false

  override def merge(other: Node[T]): Node[T] = other
  
  override def remove(x: T): Node[T] = this
  
  override def filter(f: (T) => Boolean): Node[T] = this
  
  override def foreach(f: (T) => Unit): Unit = {}
}
