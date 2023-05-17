package io.colagom.chat.route

import io.colagom.chat.model.request.CreateChatRoom
import io.colagom.chat.service.Services
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.routeRoom() {
    val service = Services.room

    route("room") {
        get { call.respond(service.all()) }

        get("{id}") {
            val roomId = call.parameters["id"]?.toLongOrNull() ?: throw BadRequestException("Invalid parameter")
            val room = service.getById(roomId) ?: throw BadRequestException("Invalid room id")
            call.respond(room)
        }

        post {
            val request = call.receive<CreateChatRoom>()
            call.respond(service.create(request))
        }
        delete { } //TODO
    }
}