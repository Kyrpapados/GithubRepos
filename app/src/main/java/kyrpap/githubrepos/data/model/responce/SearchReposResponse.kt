package kyrpap.githubrepos.data.model.responce

import kyrpap.githubrepos.data.model.local.Repository
import com.google.gson.annotations.SerializedName

data class SearchReposResponse (@SerializedName("items") var repositories: List<Repository>)