package io.colagom.chat.usecase

import io.colagom.chat.ext.now
import io.colagom.chat.model.ChatRoom
import io.colagom.chat.model.request.CreateChatRoom
import io.colagom.chat.repository.ChatRoomRepository
import io.colagom.chat.repository.Repositories

class CreateChatRoomUseCaseImpl(
    private val repo: ChatRoomRepository = Repositories.room,
) : CreateChatRoomUseCase {
    override suspend fun execute(input: CreateChatRoom): ChatRoom {
        val room = ChatRoom(repo.incrementAndGetId(), input.name, input.limit, mutableListOf(), now())
        repo.save(room)
        return room
    }
}