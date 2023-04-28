package io.colagom.chat.ext

import io.colagom.chat.ChatMessage
import io.ktor.websocket.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


fun Frame.readMessage(): ChatMessage? = (this as? Frame.Text)?.readText()?.let {
    Json.decodeFromString<ChatMessage>(it)
}