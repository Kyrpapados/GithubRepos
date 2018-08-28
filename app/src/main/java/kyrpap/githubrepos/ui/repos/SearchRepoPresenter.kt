package kyrpap.githubrepos.ui.repos

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import kyrpap.githubrepos.data.network.GithubApi
import kyrpap.githubrepos.ui.base.BasePreseneter
import kyrpap.githubrepos.utils.Statics.Companion.ORDER_BY
import kyrpap.githubrepos.utils.Statics.Companion.SORT_BY
import javax.inject.Inject

class SearchRepoPresenter<V : SearchRepoContract.View> @Inject constructor(var mGithubApi: GithubApi) : BasePreseneter<V>(), SearchRepoContract.Presenter<V> {

    override fun getRepositories(search: String) {
        compositeDisposables.add(mGithubApi.getRepositories(search, SORT_BY, ORDER_BY).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    mView.showAllRepositories(result.repositories)
                },
                        { error -> mView.loadError(error.message.toString()) }))
    }
}