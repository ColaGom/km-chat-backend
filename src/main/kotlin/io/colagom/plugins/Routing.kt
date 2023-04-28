package io.colagom.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureRouting() {
    routing {
        get("room") {
            call.respondText("Hello World!")
        }
        post("room") {

        }
    }
}
