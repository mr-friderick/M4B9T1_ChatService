object WallService {
    var arrayPosts = emptyArray<Post>()

    fun addPost(post: Post) {
        arrayPosts += post
    }

}