package com.example.user.motherearthbinar

import com.google.gson.annotations.SerializedName

data class BeritaModel(
    @SerializedName("id") var id: Int? = 0,
    @SerializedName("url") var url: String? = "url",
    @SerializedName("image") var gambar: String? = "image",
    @SerializedName("source") var source: String? = "source",
    @SerializedName("content") var content: String? = "content"
    )

