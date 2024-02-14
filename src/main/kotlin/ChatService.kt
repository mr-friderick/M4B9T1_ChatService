/*
По общей логики приложения сделал так, что если метод выполняется успешно - возвращается 1
*/
object ChatService {
    private var chatId = 0
    private var messageId = 0

    private var listChat = mutableListOf<Chat>()

    private fun MutableList<Chat>.findChatById(id: Int) : Chat =
        this.find {  it.id == id }
            ?: throw ChatNotFoundException("Не найден чат с id $chatId")

    fun clear() {
        chatId = 0
        messageId = 0
        listChat = mutableListOf()
    }

    fun sendMessage(ownerId: Int, companionId: Int, messageText: String) : Int {
        var chat = listChat.find { it.ownerId == ownerId && it.companionId == companionId }
        if (chat == null) {
            chat = Chat(id = chatId++, ownerId = ownerId, companionId = companionId)
            listChat.add(chat)
        }
        chat.messages.add(
            ChatMessage(id = messageId++, text = messageText)
        )
        return 1
    }

    fun deleteMessage(chatId: Int, messageId: Int) : Int {
        val chat = listChat.findChatById(chatId)
        val message = chat.messages.find { it.id == messageId }
            ?: throw ChatMessageNotFoundException("Не найдено сообщение с id $messageId")
        chat.messages.remove(message)
        return 1
    }

    fun deleteChat(chatId: Int) : Int {
        val chat = listChat.findChatById(chatId)
        listChat.remove(chat)
        return 1
    }

    fun getUnreadChatsCount() : Int = listChat.count { it.messages.any { !it.read } }

    fun getChats() : List<Chat> = listChat.toList()

    fun getLastMessages(chatId: Int, count: Int) : List<ChatMessage> {
        val chat = listChat.find { it.id == chatId }
        return if (chat == null) {
            println("Нет сообщений")
            listOf()
        } else {
            chat.messages.takeLast(count)
        }
    }

    fun getMessagesByCompanion(companionId: Int, count: Int) : List<ChatMessage> {
        val chat = listChat.find { it.companionId == companionId }
            ?: throw ChatNotFoundException("Не найден чат с собеседником $companionId")
        val listMessage = chat.messages.takeLast(count)
        for (message in listMessage) {
            message.read = true
        }
        return listMessage
    }
}