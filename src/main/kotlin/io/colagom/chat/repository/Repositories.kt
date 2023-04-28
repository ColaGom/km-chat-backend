package io.colagom.chat.repository

/**
 * Service locator for repositories.
 *
 */
object Repositories {
    val chat: ChatRepository by lazy { ChatRepositoryImpl() }
    val room: ChatRoomRepository by lazy { ChatRoomRepositoryImpl() }
    val user: ChatUserRepository by lazy { ChatUserRepositoryImpl() }
}