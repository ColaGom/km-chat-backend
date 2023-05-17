package io.colagom.chat.repository

import io.colagom.chat.model.Chat

interface ChatRepository {
    fun getAllByRoomId(roomId: Long): List<Chat>
    fun save(chat: Chat)
}