import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.test.BeforeTest
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class WallServiceTest {

    @BeforeTest
    fun clean() {
        WallService.clear()
    }

    @Test
    fun add() {
        val post = Post(
            0,
            1,
            2,
            "Пост для теста",
            Comments(0),
            false,
            Likes(0)
        )

        assertEquals(1, WallService.add(post).id)
    }

    @Test
    fun updateTrue() {
        val firstPost = Post(
            0,
            1,
            2,
            "Первый пост",
            Comments(0),
            false,
            Likes(0)
        )

        val secondPost = Post(
            1,
            1,
            2,
            "Второй пост",
            Comments(0),
            false,
            Likes(0)
        )

        WallService.add(firstPost)

        /*
        Корректно ли, что для вызова этих методов необходимо импортировать
        классы из библиотеки Kotlin? Они нормально дружат с библиотекой JUnit?
        */
        assertTrue(WallService.update(secondPost))
    }

    @Test
    fun updateFalse() {
        val firstPost = Post(
            0,
            1,
            2,
            "Первый пост",
            Comments(0),
            false,
            Likes(0)
        )

        val secondPost = Post(
            2,
            1,
            2,
            "Второй пост",
            Comments(0),
            false,
            Likes(0)
        )

        WallService.add(firstPost)

        assertFalse(WallService.update(secondPost))
    }
}