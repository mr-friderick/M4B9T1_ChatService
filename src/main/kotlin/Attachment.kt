abstract class Attachment(val type: String)

class AudioAttachment(
    val aObject: Audio
): Attachment("audio")

class PhotoAttachment(
    val aObject: Photo
): Attachment("photo")

class VideoAttachment(
    val aObject: Video
): Attachment("video")

class FileAttachment(
    val aObject: File
): Attachment("file")

class StickerAttachment(
    val aObject: Sticker
): Attachment("sticker")

data class Audio(
    val id: Int,
    val artist: String,
    val title: String,
    val url: String?
)

data class Photo(
    val id: Int,
    val text: String,
)

data class Video(
    val id: Int,
    val title: String,
    val description: String?
)

data class File(
    val id: Int,
    val title: String,
    val size: Int,
    val url: String?
)

data class Sticker(
    val productId: Int,
    val stickerId: String
)