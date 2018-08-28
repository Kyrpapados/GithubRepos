package kyrpap.githubrepos.ui.repos.details

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import kyrpap.githubrepos.data.network.GithubApi
import kyrpap.githubrepos.ui.base.BasePreseneter
import javax.inject.Inject

class SearchRepoDetailsPresenter<V : SearchRepoDetailsContract.View> @Inject constructor(private var mGithubApi: GithubApi) : BasePreseneter<V>(), SearchRepoDetailsContract.Presenter<V> {
    override fun getSubscribers(owner: String, name: String) {
        compositeDisposables.add(mGithubApi.getSubscribers(owner, name).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    mView.showSubscribers(result)
                },
                        { error ->
                            mView.loadError(error.message.toString())
                        }))
    }
}