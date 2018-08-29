package kyrpap.githubrepos.ui.repos

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import kyrpap.githubrepos.data.network.GithubApi
import kyrpap.githubrepos.ui.base.BasePreseneter
import kyrpap.githubrepos.utils.Statics.Companion.ORDER_BY
import kyrpap.githubrepos.utils.Statics.Companion.SORT_BY
import javax.inject.Inject

class SearchRepoPresenter<V : SearchRepoContract.View> @Inject constructor(var mGithubApi: GithubApi) : BasePreseneter<V>(), SearchRepoContract.Presenter<V> {


    companion object {
        internal val INITIAL_PAGE = 1
    }

    override fun getRepositories(search: String) {
        compositeDisposables.add(mGithubApi.getRepositories(search, SORT_BY, ORDER_BY, 1).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    mView.showAllRepositories(result.repositories)
                },
                        { error -> mView.loadError(error.message.toString()) }))
    }

    override fun requestReposByPage(search: String, page: Int) {
        compositeDisposables.add(mGithubApi.getRepositories(search, SORT_BY, ORDER_BY, page).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    mView.addRepositories(result.repositories)
                },
                        { error -> mView.loadError(error.message.toString()) }))
    }
}