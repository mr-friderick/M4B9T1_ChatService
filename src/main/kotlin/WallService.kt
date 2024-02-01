import java.lang.Exception

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

    fun createComment(postId: Int, comment: Comment): Comment {
        for (post in arrayPosts) {
            if (post.id == postId) {
                post.comments += comment
                return comment
            }
        }
        throw PostNotFoundException("Не найдено ни одного поста с id $postId")
    }
}