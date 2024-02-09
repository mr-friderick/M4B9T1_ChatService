fun main() {

    // 0
    Notes.add(
        title = "title1",
        text = "text1"
    )
    // 1
    Notes.add(
        title = "title2",
        text = "text2"
    )
    // 2
    Notes.add(
        title = "title3",
        text = "text3"
    )


    // 0
    Notes.createComment(
        noteId = 0,
        message = "message1"
    )
    // 1
    Notes.createComment(
        noteId = 0,
        message = "message2"
    )
    // 2
    Notes.createComment(
        noteId = 1,
        message = "message1"
    )

    println()
    val listNotes = Notes.get("0,1,2")
    for (note in listNotes) {
        println(note.toString())
        for (comment in note.comments) {
            println(comment.toString())
        }
        println()
    }

    // - 1
    Notes.deleteComment(1)

    println("----------------------------------------")
    val listNotes1 = Notes.get("0,1,2")
    for (note in listNotes1) {
        println(note.toString())
        for (comment in note.comments) {
            println(comment.toString())
        }
        println()
    }

    // - 1
    Notes.delete(0)

    println("----------------------------------------")
    val listNotes2 = Notes.get("0,1,2")
    for (note in listNotes2) {
        println(note.toString())
        for (comment in note.comments) {
            println(comment.toString())
        }
        println()
    }

    Notes.restoreComment(1)

//    // - 1
//    Notes.deleteComment(1)
//
//    println("-------------------------------")
//    val listNotes1 = Notes.get("0,1,2")
//    for (note in listNotes1) {
//        println(note.toString())
//        for (comment in note.comments) {
//            println(comment.toString())
//        }
//        println()
//    }
//
//    Notes.edit(
//        noteId = 0,
//        title = "title_new",
//        text = "text_new"
//    )
//
//    Notes.editComment(
//        commentId = 0,
//        message = "message_new"
//    )
//
//    println("-------------------------------")
//    val listNotes2 = Notes.get("0,1,2")
//    for (note in listNotes2) {
//        println(note.toString())
//        for (comment in note.comments) {
//            println(comment.toString())
//        }
//        println()
//    }
//
//    val listNotes3434 = Notes.get("0,2")
//
//    val note = Notes.getById(2)
//
//    val listComments = Notes.getComments(0)
//
//    Notes.restoreComment(1)
//
//    println("-------------------------------")
//    val listNotes3 = Notes.get("0,1,2")
//    for (note in listNotes3) {
//        println(note.toString())
//        for (comment in note.comments) {
//            println(comment.toString())
//        }
//        println()
//    }
}