package io.colagom.chat.service

import io.colagom.chat.repository.Repositories

/**
 * Service locator for services.
 */
object Services {
    val room: RoomService by lazy { RoomService(Repositories.room) }
    val chat: ChatService by lazy { ChatService(Repositories.chat, Repositories.room, Repositories.user) }
}