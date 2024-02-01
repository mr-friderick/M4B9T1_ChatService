import java.lang.Exception
import javax.print.attribute.standard.JobStateReason

object WallService {
    var arrayPosts = emptyArray<Post>()
    var arrayReports = emptyArray<Report>()

    private var postId   = 1
    private var reportId = 1

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

    fun reportComment(commentId: Int, reason: Int) : Int {
        if (reason !in 0..8) throw ReasonNotFoundException(
            "Допустимо указать значение от 0 до 8 включительно")

        for (post in arrayPosts) {
            for (comment in post.comments) {
                if (comment.id == commentId) {
                    arrayReports += Report(
                        id = reportId,
                        reason = reason,
                        comment = comment
                    )
                    reportId++
                    return 1
                }
            }
        }
        throw CommentNotFoundException("Не найден комментарий по id $commentId")
    }
}