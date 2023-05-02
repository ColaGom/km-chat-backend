package io.colagom.chat.plugins

import io.colagom.chat.ChatType
import io.colagom.chat.Messages
import io.colagom.chat.dto.ChatRoom
import io.colagom.chat.dto.ChatUser
import io.colagom.chat.ext.readMessage
import io.colagom.chat.service.Services
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import java.time.Duration
import java.util.*

fun Application.configureSockets() {
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    routing {
        val chatService = Services.chat
        val sessions = Collections.synchronizedSet<UserSession>(LinkedHashSet())
        webSocket("/chat/{roomId}") {
            val roomId = call.parameters["roomId"]?.toLong()
            val room: ChatRoom = roomId?.let(chatService::getRoom)
                ?: return@webSocket close(CloseReason(CloseReason.Codes.VIOLATED_POLICY, "Invalid room id"))

            val session = UserSession(this, room)

            sessions += session
            for (frame in incoming) {
                frame.readMessage()?.let { chat ->
                    when (chat.type) {
                        ChatType.JOIN -> {
                            session.user = chatService.onJoined(room.id, chat.message)
                            sessions.filter { it.room.id == room.id }.forEach {
                                it.socket.send(Messages.join(chat.message))
                            }
                        }

                        ChatType.LEAVE -> {
                            session.user?.id?.let(chatService::onLeave)
                        }

                        ChatType.MESSAGE -> {
                            val user = session.user ?: return@let close(
                                CloseReason(
                                    CloseReason.Codes.VIOLATED_POLICY,
                                    "Required user name"
                                )
                            )
                            chatService.onMessage(room.id, user.id, chat)

                            sessions.filter { it.room.id == room.id }.forEach {
                                it.socket.send(Messages.chat(it.name, chat))
                            }
                        }
                    }
                }
            }
        }
    }
}

data class UserSession(val socket: WebSocketSession, var room: ChatRoom, var user: ChatUser? = null) {
    val name get() = user?.name ?: "Unknown"
}