object WallService {
    var arrayPosts = emptyArray<Post>()
    private var postId = 0

    fun add(post: Post): Post {
        arrayPosts += post.copy(id = postId)
        postId += 1

        return arrayPosts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, value) in arrayPosts.withIndex()) {
            if (value.id == post.id)  {
                arrayPosts[index] = post
                return true
            }
        }
        return false
    }
}