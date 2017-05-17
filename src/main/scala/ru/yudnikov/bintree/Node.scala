package ru.yudnikov.bintree

/**
  * Created by igor.yudnikov on 17-May-17.
  */
abstract class Node {

  def include(x: Int): Node

  def contains(x: Int): Boolean

  def union(other: NonEmpty): NonEmpty
}
