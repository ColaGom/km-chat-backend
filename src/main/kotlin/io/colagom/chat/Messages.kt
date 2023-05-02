package io.colagom.chat


object Messages {

    fun join(userName: String) = "$userName has joined"
    fun chat(userName: String, chat: ChatMessage) = "$userName: ${chat.message}"
}