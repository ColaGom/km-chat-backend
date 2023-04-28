package io.colagom.chat.plugins

import io.colagom.chat.route.routeRoom
import io.ktor.server.application.*
import io.ktor.server.routing.*


fun Application.configureRouting() {
    routing {
        routeRoom()
    }
}
