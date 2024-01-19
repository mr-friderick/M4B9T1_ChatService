object WallService {
    var arrayPosts = emptyArray<Post>()
    private var postId = 1

    fun clear() {
        arrayPosts = emptyArray()
        postId = 1
    }

    fun add(post: Post): Post {
        arrayPosts += post.copy(id = postId)
        postId += 1

        return arrayPosts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, value) in arrayPosts.withIndex()) {
            if (value.id == post.id)  {
                arrayPosts[index] = post.copy()
                return true
            }
        }
        return false
    }
}