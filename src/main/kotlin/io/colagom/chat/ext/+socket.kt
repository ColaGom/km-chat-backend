package io.colagom.chat.ext

import io.colagom.chat.model.request.SendChat
import io.ktor.websocket.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


fun Frame.readMessage(): SendChat? = (this as? Frame.Text)?.readText()?.let {
    Json.decodeFromString<SendChat>(it)
}