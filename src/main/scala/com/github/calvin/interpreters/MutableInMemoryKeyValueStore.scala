package com.github.calvin.interpreters

import cats.{Id, Monad, catsInstancesForId}
import com.github.calvin.KeyValueStore

// interpreter
trait MutableInMemoryKeyValueStore[Key, Value] extends KeyValueStore[Key, Value, Id] {
  private var map = Map.empty[Key, Value]

  override implicit def monad: Monad[Id] = catsInstancesForId

  override def get(key: Key): Id[Option[Value]] = map.get(key)

  override def put(key: Key, value: Value): Id[Either[Error, Key]] = map.get(key) match {
    case Some(_) => Left(sys.error("key-value pair already exists"))
    case None =>
      map = map + (key -> value)
      Right(key)
  }
}

object MutableInMemoryKeyValueStore {
  def apply[K, V]: MutableInMemoryKeyValueStore[K, V] = new MutableInMemoryKeyValueStore[K, V] {}
}
