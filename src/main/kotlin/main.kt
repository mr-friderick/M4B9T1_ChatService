fun main() {
    val newPost = Post(
        1,
        1,
        2,
        "Мой первый пост",
        Comments(0),
        false,
        Likes(0)
    )

    WallService.addPost(newPost)
}