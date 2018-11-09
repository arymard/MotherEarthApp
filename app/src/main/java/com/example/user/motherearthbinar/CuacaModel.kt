package com.example.user.motherearthbinar

import com.google.gson.annotations.SerializedName

data class CuacaModel(@SerializedName("id") val status: String?,
                      @SerializedName("location") val lokasi: String?,
                      @SerializedName("code") val code: Int,
                      @SerializedName("date") val date: String?,
                      @SerializedName("high") val high: Int,
                      @SerializedName("low") val low: Int,
                      @SerializedName("level") val level: String?
)