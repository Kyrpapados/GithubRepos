package kyrpap.githubrepos.data.model.responce

import com.google.gson.annotations.SerializedName
import kyrpap.githubrepos.data.model.local.Repository

data class SearchReposResponse (@SerializedName("items") var repositories: MutableList<Repository>)