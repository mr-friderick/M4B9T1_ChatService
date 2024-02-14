data class Chat(
    val id: Int,
    val ownerId: Int,
    val companionId: Int,
    val messages: MutableList<ChatMessage> = mutableListOf()
)