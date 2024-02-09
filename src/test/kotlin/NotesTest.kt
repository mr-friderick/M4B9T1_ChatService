import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class NotesTest {

    @Before
    fun clean() {
        Notes.clear()
    }

    @Test
    fun add() {
        assertEquals(
            0, Notes.add(
                title = "title1", text = "text1"
            )
        )
    }

    @Test
    fun createComment() {
        Notes.add(
            title = "title1", text = "text1"
        )

        assertEquals(
            0, Notes.createComment(
                noteId = 0, message = "message"
            )
        )
    }

    @Test(expected = NoteNotFoundException::class)
    fun shouldThrowCreateComment() {
        Notes.createComment(
            noteId = 0, message = "message"
        )
    }

    @Test
    fun delete() {
        Notes.add(
            title = "title1", text = "text1"
        )
        assertEquals(1, Notes.delete(0))
    }

    @Test(expected = NoteNotFoundException::class)
    fun shouldThrowDelete() {
        Notes.delete(0)
    }

    @Test
    fun deleteComment() {
        Notes.add(
            title = "title1", text = "text1"
        )
        Notes.createComment(
            noteId = 0, message = "message"
        )
        assertEquals(1, Notes.deleteComment(0))
    }

    @Test(expected = NoteCommentNotFoundException::class)
    fun shouldThrowDeleteComment() {
        Notes.deleteComment(0)
    }

    @Test
    fun edit() {
        Notes.add(
            title = "title1", text = "text1"
        )
        assertEquals(
            1, Notes.edit(
                noteId = 0,
                title = "title_new",
                text = "text_new"
            )
        )
    }

    @Test(expected = NoteNotFoundException::class)
    fun shouldThrowEdit() {
        Notes.edit(
            noteId = 0,
            title = "title_new",
            text = "text_new"
        )
    }

    @Test
    fun editComment() {
        Notes.add(
            title = "title1", text = "text1"
        )
        Notes.createComment(
            noteId = 0, message = "message"
        )
        assertEquals(
            1, Notes.editComment(
                commentId = 0,
                message = "message_new"
            )
        )
    }

    @Test(expected = NoteCommentNotFoundException::class)
    fun shouldThrowEditComment() {
        Notes.editComment(
            commentId = 0,
            message = "message_new"
        )
    }

    @Test
    fun checkIdGet() {
        Notes.add(
            title = "title1", text = "text1"
        )
        assertEquals(0, Notes.get("0")[0].id)
    }

    @Test
    fun checkCountGet() {
        Notes.add(
            title = "title1", text = "text1"
        )
        Notes.add(
            title = "title2", text = "text2"
        )
        assertEquals(2, Notes.get("0,1").count())
    }

    @Test
    fun getById() {
        Notes.add(
            title = "title1", text = "text1"
        )
        assertEquals(0, Notes.getById(0).id)
    }

    @Test(expected = NoteNotFoundException::class)
    fun shouldThrowGetById() {
        Notes.getById(0)
    }

    @Test
    fun checkIdGetComments() {
        Notes.add(
            title = "title1", text = "text1"
        )
        Notes.createComment(
            noteId = 0, message = "message"
        )
        assertEquals(0, Notes.getComments(0)[0].id)
    }

    @Test
    fun checkCountGetComments() {
        Notes.add(
            title = "title1", text = "text1"
        )
        Notes.createComment(
            noteId = 0, message = "message"
        )
        Notes.createComment(
            noteId = 0, message = "message"
        )
        assertEquals(2, Notes.getComments(0).count())
    }

    @Test(expected = NoteNotFoundException::class)
    fun shouldThrowGetComments() {
        Notes.getComments(0)
    }

    @Test
    fun restoreComment() {
        Notes.add(
            title = "title1", text = "text1"
        )
        Notes.createComment(
            noteId = 0, message = "message"
        )
        Notes.deleteComment(0)
        assertEquals(1, Notes.restoreComment(0))
    }

    @Test(expected = NoteCommentNotFoundException::class)
    fun shouldThrowNoteCommentRestoreComment() {
        Notes.restoreComment(0)
    }
}