package kyrpap.githubrepos.ui.repos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_search_repos.*
import kyrpap.githubrepos.R
import kyrpap.githubrepos.data.model.local.Repository
import kyrpap.githubrepos.ui.base.BaseActivity
import kyrpap.githubrepos.ui.repos.details.SearchRepoDetailsActivity
import kyrpap.githubrepos.utils.listeners.OnCompoundDrawableClickListener
import javax.inject.Inject

class SearchReposActivity : BaseActivity(), SearchRepoContract.View, ItemClickListener {

    @Inject
    lateinit var mPresenter: SearchRepoPresenter<SearchRepoContract.View>

    private var mAdapter: SearchRepoAdapter? = null

    override fun setLayout(): Int = R.layout.activity_search_repos

    override fun attachView() {
        mPresenter.onAttach(this)
    }

    override fun detachView() {
        mPresenter.onDetach()
    }

    override fun handleIntent(intent: Intent) {

    }

    override fun init(savedInstanceState: Bundle?) {
        setupDrawInputText()

        val mLayoutManager = LinearLayoutManager(this)
        reposRecyclerView.layoutManager = mLayoutManager
        reposRecyclerView.itemAnimator = DefaultItemAnimator()
        reposRecyclerView.addItemDecoration(DividerItemDecoration(reposRecyclerView.context, mLayoutManager.orientation))
    }

    override fun showAllRepositories(competitionList: List<Repository>) {
        mAdapter = SearchRepoAdapter(this, competitionList, this::onItemClick)

        reposRecyclerView.adapter = mAdapter
    }

    override fun onItemClick(repository: Repository) {
        SearchRepoDetailsActivity.show(this, repository)
    }

    private fun setupDrawInputText() {
        draw_id_input_text.setOnTouchListener(OnCompoundDrawableClickListener(object : OnCompoundDrawableClickListener.CompoundDrawableCallback {
            override fun compoundDrawableClicked() {
                removeDrawIdFilterText()
            }

            override fun viewClicked() {
                draw_id_input_text.requestFocus()
                showKeyboard()
            }
        }))
        draw_id_input_text.setOnEditorActionListener({ v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard()
                showDrawIdCompoundDrawable()
                mPresenter.getRepositories(v.text.toString())
            }
            false
        })
    }

    private fun showKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(draw_id_input_text, 0)
    }

    private fun removeDrawIdFilterText() {
        if ("" != draw_id_input_text.text.toString()) {
            draw_id_input_text.setText("")
            draw_id_input_text.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
            reposRecyclerView.adapter = null
        }
    }

    private fun showDrawIdCompoundDrawable() {
        draw_id_input_text.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_mini, 0)
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(draw_id_input_text.windowToken, 0)
    }
}
