fun main() {
    ChatService.sendMessage(0, 0, "messageText0")
    ChatService.sendMessage(0, 0, "messageText1")
    ChatService.sendMessage(0, 1, "messageText2")
    ChatService.sendMessage(0, 2, "messageText3")

    for (chat in ChatService.getChats()) {
        println(chat)
    }

    println(ChatService.getUnreadChatsCount())


}