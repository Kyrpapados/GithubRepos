package kyrpap.githubrepos.ui.repos.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_search_repo_details.*
import kyrpap.githubrepos.R
import kyrpap.githubrepos.data.model.local.Repository
import kyrpap.githubrepos.data.model.local.Subscribers
import kyrpap.githubrepos.ui.base.BaseActivity
import kyrpap.githubrepos.utils.Statics.Companion.REPOSITORY
import javax.inject.Inject

class SearchRepoDetailsActivity : BaseActivity(), SearchRepoDetailsContract.View {

    @Inject
    lateinit var mPresenter: SearchRepoDetailsPresenter<SearchRepoDetailsContract.View>

    private lateinit var mRepository: Repository

    private var mAdapter: SubscribersAdapter? = null

    companion object {

        fun show(activity: Activity, repository: Repository) {
            val intent = Intent(activity, SearchRepoDetailsActivity::class.java)
            intent.putExtra(REPOSITORY, repository)
            activity.startActivity(intent)
        }
    }

    override fun setLayout(): Int = R.layout.activity_search_repo_details

    override fun attachView() {
        mPresenter.onAttach(this)
    }

    override fun detachView() {
        mPresenter.onDetach()
    }

    override fun handleIntent(intent: Intent) {
        mRepository = intent.getParcelableExtra(REPOSITORY)
    }

    override fun init(savedInstanceState: Bundle?) {
        nameRepo.text = mRepository.name

        val mLayoutManager = LinearLayoutManager(this)
        subscribersRecyclerView.layoutManager = mLayoutManager
        subscribersRecyclerView.itemAnimator = DefaultItemAnimator()
        subscribersRecyclerView.addItemDecoration(DividerItemDecoration(subscribersRecyclerView.context, mLayoutManager.orientation))

        mPresenter.getSubscribers(mRepository.owner.login, mRepository.name)

    }

    override fun showSubscribers(subscribersList: List<Subscribers>) {
        subscribers.text = getString(R.string.number_of_subscribers,   subscribersList.size.toString())

        mAdapter = SubscribersAdapter(this, subscribersList)

        subscribersRecyclerView.adapter = mAdapter
    }

}
