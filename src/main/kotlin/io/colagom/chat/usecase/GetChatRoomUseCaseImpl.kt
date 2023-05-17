package io.colagom.chat.usecase

import io.colagom.chat.model.ChatRoom
import io.colagom.chat.repository.ChatRoomRepository
import io.colagom.chat.repository.Repositories

class GetChatRoomUseCaseImpl(
    private val repo: ChatRoomRepository = Repositories.room,
) : GetChatRoomUseCase {
    override suspend fun execute(input: Long): ChatRoom? = repo.getById(input)
}