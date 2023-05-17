package io.colagom.chat.route

import io.colagom.chat.model.request.CreateChatRoom
import io.colagom.chat.usecase.CreateChatRoomUseCaseImpl
import io.colagom.chat.usecase.GetAllChatRoomUseCaseImpl
import io.colagom.chat.usecase.GetChatRoomUseCaseImpl
import io.colagom.chat.usecase.execute
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.routeRoom() {
    route("room") {
        get { call.respond(GetAllChatRoomUseCaseImpl().execute()) }

        get("{id}") {
            val roomId = call.parameters["id"]?.toLongOrNull() ?: throw BadRequestException("Invalid parameter")
            val room = GetChatRoomUseCaseImpl().execute(roomId) ?: throw BadRequestException("Invalid room id")
            call.respond(room)
        }

        post {
            val request = call.receive<CreateChatRoom>()
            call.respond(CreateChatRoomUseCaseImpl().execute(request))
        }
        delete { } //TODO
    }
}