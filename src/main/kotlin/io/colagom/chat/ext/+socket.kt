package io.colagom.chat.ext

import io.colagom.chat.dto.ChatRequest
import io.ktor.websocket.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


fun Frame.readMessage(): ChatRequest? = (this as? Frame.Text)?.readText()?.let {
    Json.decodeFromString<ChatRequest>(it)
}