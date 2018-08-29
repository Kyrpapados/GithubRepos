package kyrpap.githubrepos.data.network

import io.reactivex.Single
import kyrpap.githubrepos.data.model.local.Subscribers
import kyrpap.githubrepos.data.model.responce.SearchReposResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("search/repositories")
    fun getRepositories(@Query("q") q: String,
                        @Query("sort") sort: String,
                        @Query("order") order: String,
                        @Query("page") page: Int): Single<SearchReposResponse>

    @GET("repos/{owner}/{name}/subscribers")
    fun getSubscribers(@Path("owner") owner: String,
                       @Path("name") name: String): Single<List<Subscribers>>
}