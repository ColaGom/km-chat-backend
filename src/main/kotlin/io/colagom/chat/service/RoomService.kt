package io.colagom.chat.service

import io.colagom.chat.dto.ChatRoom
import io.colagom.chat.dto.request.CreateChatRoom
import io.colagom.chat.ext.now
import io.colagom.chat.repository.ChatRoomRepository
import java.util.concurrent.atomic.AtomicLong

class RoomService(
    private val roomRepository: ChatRoomRepository,
) {
    private val id = AtomicLong()

    fun getById(id: Long) = roomRepository.getById(id)

    fun create(request: CreateChatRoom): ChatRoom {
        val newRoom = ChatRoom(id.incrementAndGet(), request.name, request.limit, mutableListOf(), now())
        roomRepository.save(newRoom)
        return newRoom
    }

    fun all() = roomRepository.getAll()
}