package com.github.calvin
import com.github.calvin.interpreters.MutableInMemoryKeyValueStore

object Example extends App {
  val kvStore = MutableInMemoryKeyValueStore[String, Int]
  println(kvStore.put("1", 1))
  println(kvStore.get("2"))
  println(kvStore.get("1"))
}
