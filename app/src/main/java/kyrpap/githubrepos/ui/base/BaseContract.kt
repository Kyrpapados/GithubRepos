package kyrpap.githubrepos.ui.base

interface BaseContract {
    interface IView {
        fun showLoading()

        fun hideLoading()

        fun loadError(e: Throwable)

        fun loadError(msg: String)
    }

    interface Presenter<V : IView> {
        fun onAttach(view: V)

        fun onDetach()
    }
}