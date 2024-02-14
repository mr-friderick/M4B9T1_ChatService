data class Comment(
    val id: Int,
    val canPost: Boolean = true,
    val text: String
)

data class NoteComment(
    val id: Int,
    var message: String
)

data class Report(
    val id: Int,
    val reason: Int,
    val comment: Comment
)

data class ChatMessage(
    val id: Int,
    val text: String,
    var read: Boolean = false
)
