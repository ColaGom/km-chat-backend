package io.colagom.chat.route

import io.colagom.chat.service.Services
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

fun Route.routeRoom() {
    val service = Services.room

    route("room") {
        get { call.respond(service.all()) }
        post {
            val request = call.receive<CreateRoomRequest>()
            call.respond(service.create(request))
        }
        delete { } //TODO
    }
}


@Serializable
data class CreateRoomRequest(
    val name: String,
    val limit: Int
)