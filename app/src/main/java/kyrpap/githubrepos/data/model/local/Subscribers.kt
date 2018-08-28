package kyrpap.githubrepos.data.model.local

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Subscribers(
	@SerializedName("gists_url")
	val gistsUrl: String? = null,
	@SerializedName("repos_url")
	val reposUrl: String? = null,
	@SerializedName("following_url")
	val followingUrl: String? = null,
	@SerializedName("starred_url")
	val starredUrl: String? = null,
	@SerializedName("login")
	val login: String? = null,
	@SerializedName("followers_url")
	val followersUrl: String? = null,
	@SerializedName("type")
	val type: String? = null,
	@SerializedName("url")
	val url: String? = null,
	@SerializedName("subscriptions_url")
	val subscriptionsUrl: String? = null,
	@SerializedName("received_events_url")
	val receivedEventsUrl: String? = null,
	@SerializedName("avatar_url")
	val avatarUrl: String? = null,
	@SerializedName("events_url")
	val eventsUrl: String? = null,
	@SerializedName("html_url")
	val htmlUrl: String? = null,
	@SerializedName("site_admin")
	val siteAdmin: Boolean? = null,
	@SerializedName("id")
	val id: Int? = null,
	@SerializedName("gravatar_id")
	val gravatarId: String? = null,
	@SerializedName("node_id")
	val nodeId: String? = null,
	@SerializedName("organizations_url")
	val organizationsUrl: String? = null
): Parcelable{
	constructor(parcel: Parcel) : this(
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
			parcel.readValue(Int::class.java.classLoader) as? Int,
			parcel.readString(),
			parcel.readString(),
			parcel.readString()) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(gistsUrl)
		parcel.writeString(reposUrl)
		parcel.writeString(followingUrl)
		parcel.writeString(starredUrl)
		parcel.writeString(login)
		parcel.writeString(followersUrl)
		parcel.writeString(type)
		parcel.writeString(url)
		parcel.writeString(subscriptionsUrl)
		parcel.writeString(receivedEventsUrl)
		parcel.writeString(avatarUrl)
		parcel.writeString(eventsUrl)
		parcel.writeString(htmlUrl)
		parcel.writeValue(siteAdmin)
		parcel.writeValue(id)
		parcel.writeString(gravatarId)
		parcel.writeString(nodeId)
		parcel.writeString(organizationsUrl)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Subscribers> {
		override fun createFromParcel(parcel: Parcel): Subscribers {
			return Subscribers(parcel)
		}

		override fun newArray(size: Int): Array<Subscribers?> {
			return arrayOfNulls(size)
		}
	}

}