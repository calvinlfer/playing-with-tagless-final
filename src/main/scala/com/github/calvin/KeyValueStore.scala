package com.github.calvin

import cats.Monad

import scala.language.higherKinds

// algebra
// We abstract over the effect of the return type
trait KeyValueStore[Key, Value, Effect[_]] {
  implicit def monad: Monad[Effect]
  def get(key: Key): Effect[Option[Value]]
  def put(key: Key, value: Value): Effect[Either[Error, Key]]
}