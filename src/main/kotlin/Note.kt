data class Note (
    val id: Int,
    var title: String,
    var text: String,
    var privacy: Int = 0,
    var commentPrivacy: Int = 0,
    var comments: MutableList<NoteComment> = mutableListOf()
)
