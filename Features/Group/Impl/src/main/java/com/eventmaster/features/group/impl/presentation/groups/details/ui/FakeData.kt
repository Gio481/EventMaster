package com.eventmaster.features.group.impl.presentation.groups.details.ui

import com.eventmaster.core.presentation.R


data class FakeData(
    val title: String,
    val time: String,
    val location: String,
    val comments: List<Comment>? = null,
) {
    data class Comment(
        val name: String,
        val comment: String,
        val hoursAgo: String,
        val nestedComments: List<Comment>? = null,
        val emojis: List<Emoji>? = null
    )
}

data class Emoji(
    val emojiId: Int,
    val amount: Int
)

enum class EmojiType(val id: Int) {
    Heart(R.drawable.em_ic_heart_fill),
    Party(R.drawable.em_ic_party),
    Excited(R.drawable.em_ic_excited),
    Stars(R.drawable.em_ic_stars)
}

fun fakeData(): List<FakeData> {
    val fakeData = FakeData(
        title = "New Car",
        time = "1pm",
        location = "Akhaltsikhe",
        comments = listOf(
            FakeData.Comment(
                name = "Gio",
                comment = "Eventer",
                hoursAgo = "1h ago",
            ),
            FakeData.Comment(
                name = "Gio",
                comment = "second comment",
                hoursAgo = "5min ago",
                emojis = listOf(Emoji(EmojiType.Party.id, 3)),
                nestedComments = listOf(
                    FakeData.Comment(
                        name = "Nested First",
                        comment = "nested comment first",
                        hoursAgo = "4min ago",
                        emojis = listOf(
                            Emoji(EmojiType.Party.id, 13),
                            Emoji(EmojiType.Heart.id, 7),
                            Emoji(EmojiType.Stars.id, 1)
                        )
                    ),
                    FakeData.Comment(
                        name = "Nested Second",
                        comment = "nested comment second",
                        hoursAgo = "4min ago"
                    )
                )
            ),
            FakeData.Comment(
                name = "Gio",
                comment = "Eventer",
                hoursAgo = "1h ago",
                emojis = listOf(
                    Emoji(EmojiType.Excited.id, 3),
                    Emoji(EmojiType.Party.id, 13),
                    Emoji(EmojiType.Stars.id, 5),
                    Emoji(EmojiType.Heart.id, 7)
                )
            ),
            FakeData.Comment(
                name = "Gio",
                comment = "second comment",
                hoursAgo = "5min ago",
                nestedComments = listOf(
                    FakeData.Comment(
                        name = "Nested First",
                        comment = "nested comment first",
                        hoursAgo = "4min ago"
                    ),
                    FakeData.Comment(
                        name = "Nested Second",
                        comment = "nested comment second",
                        hoursAgo = "4min ago"
                    )
                )
            ),
        )
    )
    val fakeDataWithoutComments = FakeData(
        title = "Second Event",
        time = "1pm",
        location = "Tbilisi",
    )
    val thirdFakeData = FakeData(
        title = "Third Event",
        time = "1pm",
        location = "Tbilisi",
        comments = listOf(
            FakeData.Comment(
                name = "Comment 1",
                comment = "comment first",
                hoursAgo = "4min ago"
            ),
            FakeData.Comment(
                name = "Comment 2",
                comment = "comment second",
                hoursAgo = "4min ago"
            )
        )
    )
   return listOf(fakeData, fakeDataWithoutComments, thirdFakeData)
}