package io.colagom.chat.service

import io.colagom.chat.ChatMessage
import io.colagom.chat.dto.Chat
import io.colagom.chat.dto.ChatUser
import io.colagom.chat.ext.now
import io.colagom.chat.repository.ChatRepository
import io.colagom.chat.repository.ChatRoomRepository
import io.colagom.chat.repository.ChatUserRepository
import io.ktor.server.plugins.*
import java.util.concurrent.atomic.AtomicLong

class ChatService(
    private val chatRepository: ChatRepository,
    private val roomRepository: ChatRoomRepository,
    private val userRepository: ChatUserRepository,
) {
    private val idCounter = AtomicLong()
    fun onJoined(roomId: Long, userName: String): ChatUser {
        val room = roomRepository.getById(roomId) ?: throw BadRequestException("invalid room id")
        return userRepository.save(room, userName)
    }

    fun onMessage(roomId: Long, userId: Long, chat: ChatMessage) {
        val room = getRoom(roomId) ?: throw BadRequestException("invalid room id")
        val user = getUser(userId) ?: throw BadRequestException("invalid user id")

        chatRepository.save(Chat(idCounter.incrementAndGet(), room, user, chat.message, now()))
    }

    fun onLeave(userId: Long) {
        getUser(userId)?.let(userRepository::delete)
    }

    fun getUser(id: Long) = userRepository.getById(id)
    fun getRoom(id: Long) = roomRepository.getById(id)
}