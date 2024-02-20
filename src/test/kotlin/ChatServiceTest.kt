import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class ChatServiceTest {

    @Before
    fun clear() {
        ChatService.clear()
    }

    @Test
    fun sendMessage() {
        assertEquals(1,
            ChatService.sendMessage(
                ownerId = 0,
                companionId = 0,
                messageText = "messageText"
            )
        )
    }

    @Test
    fun deleteChat() {
        ChatService.sendMessage(
            ownerId = 0,
            companionId = 0,
            messageText = "messageText"
        )
        assertEquals(true, ChatService.deleteChat(chatId = 0))
    }

    @Test(expected = ChatNotFoundException::class)
    fun shouldThrowChatNotFoundDeleteMessage() {
        ChatService.deleteMessage(chatId = 0, messageId = 0)
    }

    @Test
    fun deleteMessage() {
        ChatService.sendMessage(
            ownerId = 0,
            companionId = 0,
            messageText = "messageText"
        )
        assertEquals(true, ChatService.deleteMessage(chatId = 0, messageId = 0))
    }

    @Test(expected = NoSuchElementException ::class)
    fun shouldThrowChatNotFoundGetMessagesByCompanion() {
        ChatService.getMessagesByCompanion(companionId = 0, count = 1)
    }

    @Test
    fun getMessagesByCompanion() {
        ChatService.sendMessage(
            ownerId = 0,
            companionId = 0,
            messageText = "messageText"
        )
        ChatService.sendMessage(
            ownerId = 0,
            companionId = 0,
            messageText = "messageText1"
        )

        assertEquals(1, ChatService.getMessagesByCompanion(0, 1).count())
    }

    @Test
    fun getUnreadChatsCount() {
        ChatService.sendMessage(
            ownerId = 0,
            companionId = 0,
            messageText = "messageText"
        )
        ChatService.sendMessage(
            ownerId = 1,
            companionId = 1,
            messageText = "messageText"
        )
        ChatService.sendMessage(
            ownerId = 2,
            companionId = 2,
            messageText = "messageText"
        )

        ChatService.getMessagesByCompanion(0, 1)

        assertEquals(2, ChatService.getUnreadChatsCount())
    }

    @Test
    fun getChats() {
        ChatService.sendMessage(
            ownerId = 0,
            companionId = 0,
            messageText = "messageText"
        )

        assertEquals(1, ChatService.getChats().count())
    }

    @Test
    fun getLastMessages() {
        ChatService.sendMessage(
            ownerId = 0,
            companionId = 0,
            messageText = "messageText"
        )
        ChatService.sendMessage(
            ownerId = 0,
            companionId = 0,
            messageText = "messageText1"
        )
        ChatService.sendMessage(
            ownerId = 0,
            companionId = 0,
            messageText = "messageText2"
        )

        assertEquals(3, ChatService.getLastMessages(chatId = 0, count = 3).count())
    }
}