package com.kbudunov.lesson.core.generics

abstract class GenericsMethodExample {
  def remove[K](key: K): K
}

trait Cache[K, V] {
  def get(key: K): V
  def put(key: K, value: V): V
  def delete(key: K): V
}
