package com.study.conversordemoeda.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json

object KtorHttpClient {

  private const val BASE_URL = "http://10.0.2.2:8080"

  val client = HttpClient(Android) {
    install(Logging)
    install(ContentNegotiation) {
      json()
    }
  }




}
