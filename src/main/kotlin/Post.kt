data class Post(
    val id: Int,
    val ownerId: Int,
    val fromId: Int,
    val text: String,
    val friendsOnly: Boolean,
    val likes: Likes?,
    val postType: String = "post",
    val canPin: Boolean = true,
    val canEdit: Boolean = true,
    var comments: Array<Comment> = emptyArray(),
    val attachment: Array<Attachment>
)