object ChatService {
    private var chatId = 0
    private var messageId = 0

    private var listChat = mutableListOf<Chat>()

    private fun findChatById(id: Int) : Chat =
         listChat.find { it.id == chatId }
            ?: throw ChatNotFoundException("Не найден чат с id $chatId")

    fun sendMessage(idFirst: Int, idSecond: Int, messageText: String) {
        var chat = listChat.find { it.idFirstPerson == idFirst || it.idSecondPerson == idSecond }
        if (chat == null) {
            chat = Chat(id = chatId++, idFirstPerson = idFirst, idSecondPerson = idSecond)
            listChat.add(chat)
        }
        chat.messages.add(
            ChatMessage(id = messageId++, text = messageText)
        )
    }

    fun deleteMessage(chatId: Int, messageId: Int) {
        val chat = findChatById(chatId)
        val message = chat.messages.find { it.id == messageId }
            ?: throw ChatMessageNotFoundException("Не найдено сообщение с id $messageId")
        chat.messages.remove(message)
    }

    fun deleteChat(chatId: Int) {
        val chat = findChatById(chatId)
        listChat.remove(chat)
    }

    fun getUnreadChatsCount() : Int = listChat.count { it.messages.equals(it.messages.find { !it.read }) }

    fun getChats() : List<Chat> = listChat.toList()

    fun getLastMessage(chatId: Int, count: Int) : List<ChatMessage> {
        val chat = listChat.find { it.id == chatId }
        return if (chat == null) {
            println("Нет сообщений")
            listOf()
        } else {
            chat.messages.takeLast(count)
        }
    }


}