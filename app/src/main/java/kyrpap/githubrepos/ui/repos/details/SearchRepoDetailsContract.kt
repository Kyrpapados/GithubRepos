package kyrpap.githubrepos.ui.repos.details

import kyrpap.githubrepos.data.model.local.Subscribers
import kyrpap.githubrepos.ui.base.BaseContract

interface SearchRepoDetailsContract {
    interface View : BaseContract.IView{

        fun showSubscribers(subscribersList : List<Subscribers>)

    }


    interface Presenter<V : SearchRepoDetailsContract.View> : BaseContract.Presenter<V>{
        fun getSubscribers(owner : String, name : String)
    }
}