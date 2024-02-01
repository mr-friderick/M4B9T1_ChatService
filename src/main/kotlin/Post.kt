data class Post(
    val id: Int,
    val ownerId: Int,
    val fromId: Int = 0,
    val text: String,
    val friendsOnly: Boolean = false,
    val likes: Likes?,
    val postType: String = "post",
    val canPin: Boolean = true,
    val canEdit: Boolean = true,
    var comments: Array<Comment> = emptyArray(),
    val attachment: Array<Attachment> = emptyArray()
)