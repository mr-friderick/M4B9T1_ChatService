object Notes {
    private var noteId        = 1
    private var noteCommentId = 1

    var listNotes         = mutableListOf<Note>()
    var mapDeleteComments = mutableMapOf<Int, NoteComment>()

    fun add(title: String, text: String, privacy: Int = 0, commentPrivacy: Int = 0): Int {
        listNotes.add(
            Note(
                id = noteId++,
                title = title,
                text = text,
                privacy = privacy,
                commentPrivacy = commentPrivacy
            )
        )
        return noteId
    }

    fun createComment(noteId: Int, message: String) : Int {
        for (note in listNotes) {
            if (note.id == noteId) {
                note.comments.add(
                    NoteComment(
                        id = noteCommentId++,
                        message = message
                    )
                )
                return noteCommentId
            }
        }
       throw NoteNotFoundException("Не найдена Заметка с id $noteId")
    }

    fun delete(noteId: Int) : Int {
        for (note in listNotes) {
            if (note.id == noteId) {
                listNotes.remove(note)
                return 1
            }
        }
        /*
        По документации сказано, что возвращаем 180, если не найдена заметка,
        но для единообразия сделал вызов исключения
        */
        throw NoteNotFoundException("Не найдена Заметка с id $noteId")
    }

    fun deleteComment(commentId: Int) : Int {
        for (note in listNotes) {
            for (noteComment in note.comments) {
                if (noteComment.id == commentId) {
                    mapDeleteComments.put(commentId, noteComment)
                    note.comments.remove(noteComment)
                    return 1
                }
            }
        }
        throw NoteCommentNotFoundException("Не найден Комментарий заметки с id $commentId")
    }

    fun edit(noteId: Int, title: String, text: String, privacy: Int?, commentPrivacy: Int?) : Int {
        for (note in listNotes) {
            if (note.id == noteId) {
                note.title = title
                note.text = text
                note.privacy = privacy ?: note.privacy
                note.commentPrivacy = commentPrivacy ?: note.commentPrivacy
                return 1
            }
        }
        throw NoteNotFoundException("Не найдена Заметка с id $noteId")
    }

    fun editComment(commentId: Int, message: String) : Int {
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
    */
    fun get(noteIds: String) : List<Note> {
        var listN = mutableListOf<Note>()

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
   fun getById(noteId: Int) : Note {
       return listNotes.find { it.id == noteId } ?: throw NoteNotFoundException("Не найдена Заметка с id $noteId")
   }
}