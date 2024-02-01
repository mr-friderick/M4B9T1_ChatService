data class Comment(
    val id: Int,
    val canPost: Boolean = true,
    val text: String
)

data class Report(
    val id: Int,
    val reason: Int,
    val comment: Comment
)