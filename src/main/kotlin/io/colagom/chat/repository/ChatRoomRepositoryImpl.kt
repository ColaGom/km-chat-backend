package io.colagom.chat.repository

import io.colagom.chat.dto.ChatRoom

class ChatRoomRepositoryImpl : ChatRoomRepository {
    private val rooms = hashMapOf<Long, ChatRoom>()
    override fun getById(id: Long): ChatRoom? = rooms[id]
    override fun getAll(): List<ChatRoom> = rooms.values.toList()
    override fun save(room: ChatRoom) {
        rooms[room.id] = room
    }
}