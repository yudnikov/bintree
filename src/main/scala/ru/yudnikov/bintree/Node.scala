package ru.yudnikov.bintree

/**
  * Created by igor.yudnikov on 17-May-17.
  */
abstract class Node[T] {

  def include(x: T): NonEmpty[T]

  def contains(x: T): Boolean

  def merge(other: Node[T]): Node[T]
  
  def remove(x: T): Node[T]
  
  def filter(p: (T) => Boolean): Node[T]
  
  def foreach(f: (T) => Unit): Unit
}
