package io.colagom.chat.repository

import io.colagom.chat.dto.Chat

class ChatRepositoryImpl : ChatRepository {
    /**
     * key is room id
     */
    private val chats = sortedMapOf<Long, MutableList<Chat>>()
    override fun getAllByRoomId(roomId: Long): List<Chat> = chats[roomId] ?: emptyList()

    override fun save(chat: Chat) {
        val roomId = chat.room.id
        chats[roomId]?.add(chat) ?: chats.put(roomId, mutableListOf(chat))
    }
}