package io.colagom.chat.repository

import io.colagom.chat.dto.ChatRoom

class ChatRoomRepositoryImpl : ChatRoomRepository {
    private val rooms = mutableListOf<ChatRoom>()
    override fun getById(id: Long): ChatRoom? {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<ChatRoom> = rooms

    override fun save(room: ChatRoom) {
        rooms.add(room)
    }
}