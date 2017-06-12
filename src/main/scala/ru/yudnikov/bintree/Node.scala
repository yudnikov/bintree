package ru.yudnikov.bintree

/**
  * Created by igor.yudnikov on 17-May-17.
  */
abstract class Node[T] {
  
  type CompareFunction = (T, T) => Boolean
  
  val compare: CompareFunction
  
  def collect: List[T]
  
  def contains(x: T):  Boolean

  def filter(p: (T) => Boolean): Node[T]
  
  def foreach(f: (T) => Unit): Unit

  def include(x: T): NonEmpty[T]

  def include(x: T*): NonEmpty[T]
  
  def merge(other: Node[T]): Node[T]
  
  def remove(x: T): Node[T]
  
  def sortBy(f: CompareFunction): Node[T]
  
  def withOrderFunction(f: CompareFunction): Node[T]
}
