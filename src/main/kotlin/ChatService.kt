object ChatService {
    private var chatId = 0
    private var messageId = 0

    private var listChat = mutableListOf<Chat>()

    private fun MutableList<Chat>.findChatById(id: Int): Chat =
        this.find { it.id == id }
            ?: throw ChatNotFoundException("Не найден чат с id $id")

    fun clear() {
        chatId = 0
        messageId = 0
        listChat = mutableListOf()
    }

    fun sendMessage(ownerId: Int, companionId: Int, messageText: String): Int {
        val chat = listChat.find { it.ownerId == ownerId && it.companionId == companionId }
            ?: Chat(id = chatId++, ownerId = ownerId, companionId = companionId)
        listChat.add(chat)

        chat.messages.add(
            ChatMessage(id = messageId++, text = messageText)
        )
        return 1
    }

    fun deleteMessage(chatId: Int, messageId: Int): Boolean =
        listChat
            .findChatById(chatId)
            .messages.removeIf { it.id == messageId }

    fun deleteChat(chatId: Int): Boolean =
        listChat
            .removeIf { it.id == chatId }

    fun getUnreadChatsCount(): Int =
        listChat
            .count { it.messages.any { !it.read } }

    fun getChats(): List<Chat> =
        listChat
            .toList()

    /*
    Все равно не уверен, что правильно сделал. Не могу до конца понять целесообразность таких преобразований.
    Я вызываю .take() первым действием после нахождение чата. Т.е. у меня уже только нужно кол-во.
    Далее я выполняю доп. действия. Не вижу никакого выйгрыша по скорости, т.к. кол-во обрабатываемых элементов
    всегда определено параметром.
    Я понимаю пример из лекции, но видимо для моего решения он избыточный.
     */
    fun getLastMessages(chatId: Int, count: Int): List<String> =
        listChat
            .findChatById(chatId)
            .messages.asReversed().asSequence()
            .take(count)
            .map { it.text }
            .toList()

    /*
    Все равно не уверен, что правильно сделал. Не могу до конца понять целесообразность таких преобразований.
    Я вызываю .take() первым действием после нахождение чата. Т.е. у меня уже только нужно кол-во.
    Далее я выполняю доп. действия. Не вижу никакого выйгрыша по скорости, т.к. кол-во обрабатываемых элементов
    всегда определено параметром.
    Я понимаю пример из лекции, но видимо для моего решения он избыточный.
     */
    fun getMessagesByCompanion(companionId: Int, count: Int): List<ChatMessage> =
        listChat
            .first { it.companionId == companionId }
            .messages.asReversed().asSequence()
            .take(count)
            .onEach { it.read = true }
            .toList()
}