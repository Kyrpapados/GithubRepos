package kyrpap.githubrepos.data.model.local

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Repository (
        @SerializedName("id") var id: Int,
        @SerializedName("forks_count") var forks: Int,
        @SerializedName("name") var name: String,
        @SerializedName("full_name") var fullName: String,
        @SerializedName("subscribers_url") var subscribers: String,
        @SerializedName("owner") var owner: Owner,
        @SerializedName("private") var isPrivate: Boolean,
        @SerializedName("description") var description: String? = "",
        @SerializedName("created_at") var createdAt: String
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(Owner::class.java.classLoader),
            parcel.readByte() != 0.toByte(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(forks)
        parcel.writeString(name)
        parcel.writeString(fullName)
        parcel.writeString(subscribers)
        parcel.writeParcelable(owner, flags)
        parcel.writeByte(if (isPrivate) 1 else 0)
        parcel.writeString(description)
        parcel.writeString(createdAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Repository> {
        override fun createFromParcel(parcel: Parcel): Repository {
            return Repository(parcel)
        }

        override fun newArray(size: Int): Array<Repository?> {
            return arrayOfNulls(size)
        }
    }
}

