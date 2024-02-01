fun main() {
    var arrayAttachment = arrayOf<Attachment>()
    arrayAttachment += PhotoAttachment(Photo(0, "text"))
    arrayAttachment += VideoAttachment(Video(0, "title", "description"))

    val newPost = Post(
        0,
        0,
        0,
        "text",
        true,
        null,
        attachment = arrayAttachment)

    WallService.add(newPost)
    println(WallService.createComment(1, Comment(0, true, "text")))
}