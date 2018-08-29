package kyrpap.githubrepos.ui.repos

import kyrpap.githubrepos.data.model.local.Repository
import kyrpap.githubrepos.ui.base.BaseContract

interface SearchRepoContract {
    interface View : BaseContract.IView{
        fun showAllRepositories(competitionList: MutableList<Repository>)

        fun addRepositories(competitionList: MutableList<Repository>)

    }


    interface Presenter<V : SearchRepoContract.View> : BaseContract.Presenter<V>{
        fun getRepositories(search : String)

        fun requestReposByPage(search: String, page: Int)
    }
}