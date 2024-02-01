import org.junit.Test
import org.junit.Before
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue

class WallServiceTest {

    @Before
    fun clean() {
        WallService.clear()
    }

    @Test
    fun add() {
        var arrayAttachment = arrayOf<Attachment>()
        arrayAttachment += PhotoAttachment(Photo(0, "text"))
        arrayAttachment += VideoAttachment(Video(0, "title", "description"))

        val post = Post(
            id = 0,
            ownerId = 1,
            text = "text",
            likes = Likes(0),
            attachment = arrayAttachment
        )

        assertEquals(1, WallService.add(post).id)
    }

    @Test
    fun updateTrue() {
        var arrayAttachment = arrayOf<Attachment>()
        arrayAttachment += PhotoAttachment(Photo(0, "text"))
        arrayAttachment += VideoAttachment(Video(0, "title", "description"))

        val firstPost = Post(
            id = 0,
            ownerId = 1,
            text = "text1",
            likes = Likes(0),
            attachment = arrayAttachment
        )

        val secondPost = Post(
            id = 1,
            ownerId = 1,
            text = "text2",
            likes = Likes(0),
            attachment = arrayAttachment
        )

        WallService.add(firstPost)

        assertTrue(WallService.update(secondPost))
    }

    @Test
    fun updateFalse() {
        var arrayAttachment = arrayOf<Attachment>()
        arrayAttachment += PhotoAttachment(Photo(0, "text"))
        arrayAttachment += VideoAttachment(Video(0, "title", "description"))

        val firstPost = Post(
            id = 0,
            ownerId = 1,
            text = "text1",
            likes = Likes(0),
            attachment = arrayAttachment
        )

        val secondPost = Post(
            id = 0,
            ownerId = 1,
            text = "text2",
            likes = Likes(0),
            attachment = arrayAttachment
        )

        WallService.add(firstPost)

        assertFalse(WallService.update(secondPost))
    }

    @Test
    fun createComment() {
        val newPost = Post(
            id = 0,
            ownerId = 1,
            text = "text",
            likes = Likes(0)
        )

        val comment = Comment(
            id = 0,
            text = "text"
        )

        WallService.add(newPost)

        assertEquals(
            comment,
            WallService.createComment(
                postId = 1,
                comment = comment
            )
        )
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrowCreateComment() {
        val comment = Comment(
            id = 0,
            text = "text"
        )

        WallService.createComment(
            postId = 1,
            comment = comment
        )
    }

    @Test
    fun reportComment() {
        val newPost = Post(
            id = 0,
            ownerId = 1,
            text = "text",
            likes = Likes(0)
        )

        val comment = Comment(
            id = 0,
            text = "text"
        )

        WallService.add(newPost)
        WallService.createComment(
                postId = 1,
                comment = comment
        )
        assertEquals(
            1,
            WallService.reportComment(
                commentId = 0,
                reason = 6
            )
        )
    }

    @Test(expected = ReasonNotFoundException::class)
    fun shouldThrowReasonReportComment() {
        WallService.reportComment(
            commentId = 0,
            reason = 10
        )
    }

    @Test(expected = CommentNotFoundException::class)
    fun shouldThrowCommentReportComment() {
        val newPost = Post(
            id = 0,
            ownerId = 1,
            text = "text",
            likes = Likes(0)
        )

        val comment = Comment(
            id = 0,
            text = "text"
        )

        WallService.add(newPost)
        WallService.createComment(
            postId = 1,
            comment = comment
        )
        assertEquals(
            1,
            WallService.reportComment(
                commentId = 1,
                reason = 6
            )
        )
    }
}