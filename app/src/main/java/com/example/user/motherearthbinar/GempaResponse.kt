package com.example.user.motherearthbinar

import com.google.gson.annotations.SerializedName


data class GempaResponse(@SerializedName("status") val status: String?,
                           @SerializedName("results") val data: List<GempaModel>?)