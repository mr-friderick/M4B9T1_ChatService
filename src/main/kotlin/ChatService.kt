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

    fun deleteMessage(chatId: Int, messageId: Int) : Boolean =
        listChat
            .findChatById(chatId)
            .messages.removeIf { it.id == messageId }

    fun deleteChat(chatId: Int) : Boolean = listChat.removeIf { it.id == chatId }

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

    fun getMessagesByCompanion(companionId: Int, count: Int) : List<ChatMessage> =
        listChat.asSequence()
            .first { it.companionId == companionId }
            .let { it.messages.toList() }
            .takeLast(count)
            .onEach { it.read = true }
}