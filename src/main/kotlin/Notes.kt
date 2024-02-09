object Notes {
    private var noteId = 0
    private var noteCommentId = 0

    var listNotes = mutableListOf<Note>()
    var mapDeleteComments = mutableMapOf<Int, Map<Int, NoteComment>>()

    fun clear() {
        listNotes         = mutableListOf()
        mapDeleteComments = mutableMapOf()
        noteId            = 0
        noteCommentId     = 0
    }

    fun add(title: String, text: String, privacy: Int = 0, commentPrivacy: Int = 0): Int {
        listNotes.add(
            Note(
                id = noteId++, title = title, text = text, privacy = privacy, commentPrivacy = commentPrivacy
            )
        )
        return noteId - 1
    }

    fun createComment(noteId: Int, message: String): Int {
        val note = listNotes.find { it.id == noteId } ?: throw NoteNotFoundException("Не найдена Заметка с id $noteId")
        note.comments.add(
            NoteComment(
                id = noteCommentId++, message = message
            )
        )
        return noteCommentId - 1
    }

    fun delete(noteId: Int): Int {
        val note = listNotes.find { it.id == noteId } ?: throw NoteNotFoundException("Не найдена Заметка с id $noteId")
        listNotes.remove(note)
        return 1
        /*
        По документации сказано, что возвращаем 180, если не найдена заметка,
        но для единообразия сделал вызов исключения
        */
    }

    fun deleteComment(commentId: Int): Int {
        for (note in listNotes) {
            for (noteComment in note.comments) {
                if (noteComment.id == commentId) {
                    mapDeleteComments.put(commentId, mapOf(note.id to noteComment))
                    note.comments.remove(noteComment)
                    return 1
                }
            }
        }
        throw NoteCommentNotFoundException("Не найден Комментарий заметки с id $commentId")
    }

    fun edit(noteId: Int, title: String, text: String, privacy: Int? = null, commentPrivacy: Int? = null): Int {
        val note = listNotes.find { it.id == noteId } ?: throw NoteNotFoundException("Не найдена Заметка с id $noteId")
        note.title = title
        note.text = text
        note.privacy = privacy ?: note.privacy
        note.commentPrivacy = commentPrivacy ?: note.commentPrivacy
        return 1
    }

    fun editComment(commentId: Int, message: String): Int {
        for (note in listNotes) {
            for (noteComment in note.comments) {
                if (noteComment.id == commentId) {
                    noteComment.message = message
                    return 1
                }
            }
        }
        throw NoteCommentNotFoundException("Не найден Комментарий заметки с id $commentId")
    }

    /*
    В документации сказано, что список id это просто строка, что странно.
    Предполагаю, что это просто id через запятую
    Про параметр count тоже не понял: кол-во в плане какое: 2 сверху? 2 с конца? Не стал добавлять.
    */
    fun get(noteIds: String): List<Note> {
        val listN = mutableListOf<Note>()

        val arrIds = noteIds.split(",")
        for (id in arrIds) {
            for (note in listNotes) {
                if (note.id == id.toInt()) {
                    listN.add(note)
                    break
                }
            }
        }
        return listN.toList()
    }

    /*
    Не понял по документации в итоге, что нужно возвращать. В описание сказано возвращает Заметку.
    Но в Результате описано, что возвращает список. Сделал как кажется логичным.
    */
    fun getById(noteId: Int): Note {
        return listNotes.find { it.id == noteId } ?: throw NoteNotFoundException("Не найдена Заметка с id $noteId")
    }

    fun getComments(noteId: Int): List<NoteComment> {
        val note = listNotes.find { it.id == noteId } ?: throw NoteNotFoundException("Не найдена Заметка с id $noteId")
        return note.comments.toList()
    }

    fun restoreComment(commentId: Int): Int {
        val mapComment = mapDeleteComments.get(commentId)
            ?: throw NoteCommentNotFoundException("Не найден Комментарий заметки с id $commentId")
        val note = listNotes.find { it.id == mapComment.keys.first() }
            ?: throw NoteNotFoundException("Заметка, в которой был комментарий, удалена")
        note.comments.add(mapComment[mapComment.keys.first()]!!)
        return 1
    }
}