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
            0,
            1,
            2,
            "Пост для теста",
            null,
            false,
            Likes(0),
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
            0,
            1,
            2,
            "Первый пост",
            Comments(0),
            false,
            Likes(0),
            attachment = arrayAttachment
        )

        val secondPost = Post(
            1,
            1,
            2,
            "Второй пост",
            Comments(0),
            false,
            null,
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
            0,
            1,
            2,
            "Первый пост",
            Comments(0),
            false,
            Likes(0),
            attachment = arrayAttachment
        )

        val secondPost = Post(
            2,
            1,
            2,
            "Второй пост",
            Comments(0),
            false,
            Likes(0),
            attachment = arrayAttachment
        )

        WallService.add(firstPost)

        assertFalse(WallService.update(secondPost))
    }
}