fun main() {
    val newPost = Post(
        0,
        1,
        2,
        "Шаблон поста",
        Comments(0),
        false,
        Likes(0)
    )

    WallService.add(newPost)
    WallService.add(newPost)
    WallService.add(newPost)
    println(WallService.arrayPosts.last().text)

    val nextPost = Post(
        2,
        1,
        2,
        "Еще один пост",
        Comments(0),
        false,
        Likes(0)
    )

    WallService.update(nextPost)
    println(WallService.arrayPosts.last().text)
}