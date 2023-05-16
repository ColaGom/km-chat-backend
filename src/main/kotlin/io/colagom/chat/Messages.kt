package io.colagom.chat

import io.colagom.chat.dto.ChatMessage
import io.colagom.chat.dto.ChatRequest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


object Messages {
    fun join(userName: String) = Json.encodeToString(
        ChatMessage(false, "$userName has joined")
    )
    fun chat(isMine: Boolean, userName: String, chat: ChatRequest) =
        Json.encodeToString(
            ChatMessage(isMine, "$userName: ${chat.message}")
        )
}