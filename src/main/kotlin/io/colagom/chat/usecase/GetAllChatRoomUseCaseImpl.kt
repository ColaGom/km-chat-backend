package io.colagom.chat.usecase

import io.colagom.chat.model.ChatRoom
import io.colagom.chat.repository.ChatRoomRepository
import io.colagom.chat.repository.Repositories

class GetAllChatRoomUseCaseImpl(
    private val repo: ChatRoomRepository = Repositories.room,
) : GetAllChatRoomUseCase {
    override suspend fun execute(input: Unit): List<ChatRoom> =
        repo.getAll()
}
