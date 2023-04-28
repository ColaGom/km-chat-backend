package io.colagom.chat

import kotlinx.serialization.Serializable

@Serializable
data class ChatMessage(
    val type: ChatType,
    val message: String
)

enum class ChatType {
    JOIN,
    LEAVE,
    MESSAGE
}