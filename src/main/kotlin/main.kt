fun main() {
    var arrayAttachment = arrayOf<Attachment>()
    arrayAttachment += PhotoAttachment(Photo(0, "text"))
    arrayAttachment += VideoAttachment(Video(0, "title", "description"))

    val newPost = Post(
        0,
        0,
        0,
        "text",
        Comments(0),
        true,
        null,
        attachment = arrayAttachment)

    WallService.add(newPost)
    println(WallService.arrayPosts[0].attachment[0].type)
}