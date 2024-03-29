fun main() {
    ChatService.sendMessage(0, 0, "messageText0")
    ChatService.sendMessage(0, 0, "messageText1")
    ChatService.sendMessage(0, 1, "messageText2")
    ChatService.sendMessage(0, 2, "messageText3")

    println()
    for (chat in ChatService.getChats()) {
        println(chat)
    }

    println()
    println(ChatService.getUnreadChatsCount())

    println()
    println(ChatService.getLastMessages(5, 1))

    println("getMessagesByCompanion")
    println(ChatService.getMessagesByCompanion(0, 5))
    println(ChatService.getMessagesByCompanion(1, 5))
    println(ChatService.getMessagesByCompanion(0, 5))

    println()
    println(ChatService.getUnreadChatsCount())

    ChatService.deleteMessage(0, 1)

    println()
    for (chat in ChatService.getChats()) {
        println(chat)
    }

    ChatService.deleteChat(0)

    println()
    for (chat in ChatService.getChats()) {
        println(chat)
    }
}