package kyrpap.githubrepos.data.model.local

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class Owner(@SerializedName("id") var id: Int,
                 @SerializedName("login") var login: String,
                 @SerializedName("avatar_url") var avatar: String): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(login)
        parcel.writeString(avatar)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Owner> {
        override fun createFromParcel(parcel: Parcel): Owner {
            return Owner(parcel)
        }

        override fun newArray(size: Int): Array<Owner?> {
            return arrayOfNulls(size)
        }
    }
}