package io.colagom.chat.service

import io.colagom.chat.repository.Repositories

/**
 * Service locator for services.
 */
object Services {
    val chat: ChatService by lazy { ChatService(Repositories.chat, Repositories.room, Repositories.user) }
}