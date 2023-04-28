package io.colagom.chat.service

import io.colagom.chat.dto.ChatRoom
import io.colagom.chat.ext.now
import io.colagom.chat.repository.ChatRoomRepository
import io.colagom.chat.route.CreateRoomRequest
import java.util.concurrent.atomic.AtomicLong

class RoomService(
    private val roomRepository: ChatRoomRepository,
) {
    private val id = AtomicLong()

    fun create(request: CreateRoomRequest): ChatRoom {
        val newRoom = ChatRoom(id.incrementAndGet(), request.name, request.limit, emptyList(), now())
        roomRepository.save(newRoom)
        return newRoom
    }

    fun all() = roomRepository.getAll()
}