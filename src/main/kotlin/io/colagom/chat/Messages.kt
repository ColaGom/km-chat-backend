package io.colagom.chat

import io.colagom.chat.model.ChatMessage
import io.colagom.chat.model.request.SendChat
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


object Messages {
    fun join(userName: String) = Json.encodeToString(
        ChatMessage(false, "$userName has joined")
    )
    fun chat(isMine: Boolean, userName: String, chat: SendChat) =
        Json.encodeToString(
            ChatMessage(isMine, "$userName: ${chat.message}")
        )
}