package com.example.pr23106sborschikovpr22

import java.util.UUID

class EmojiModel(
    var char: String,
    var isVisible: Boolean = true,
    var isSelect: Boolean = false,
    var id: String = UUID.randomUUID().toString(),
) {}