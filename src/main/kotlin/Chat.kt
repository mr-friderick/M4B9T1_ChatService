data class Chat(
    val id: Int,
    val idFirstPerson: Int,
    val idSecondPerson: Int,
    val messages: MutableList<ChatMessage> = mutableListOf()
)