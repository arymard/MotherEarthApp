package com.example.user.motherearthbinar

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class GempaModel(
    @SerializedName("id") var id: String? = "1",
    @SerializedName("earthquake_time") var waktu: String? = "2018-10-24T01:42:53.000Z",
    @SerializedName("longitude") var longitude: Double? = 119.86,
    @SerializedName("latitude") var latitude: Double? = 119.86,
    @SerializedName("magnitude") var magnitude: Double? = 3.3,
    @SerializedName("depth") var kedalaman: Int? = 10,
    @SerializedName("location") var lokasi: String? = "lokasi"
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(waktu)
        parcel.writeValue(longitude)
        parcel.writeValue(latitude)
        parcel.writeValue(magnitude)
        parcel.writeValue(kedalaman)
        parcel.writeString(lokasi)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GempaModel> {
        override fun createFromParcel(parcel: Parcel): GempaModel {
            return GempaModel(parcel)
        }

        override fun newArray(size: Int): Array<GempaModel?> {
            return arrayOfNulls(size)
        }
    }
}



