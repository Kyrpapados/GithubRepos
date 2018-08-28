package kyrpap.githubrepos.ui.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePreseneter<V : BaseContract.IView> : BaseContract.Presenter<V> {
    lateinit var mView : V

    override fun onAttach(view: V) {
        mView = view
    }

    override fun onDetach() {
        compositeDisposables.clear()
        compositeDisposables.dispose()
    }


    companion object {

        /*
        var compositeDisposables: CompositeDisposable
        Every method which will be part of presenter lyer will be added in it so we could dispose off them once they are no more in our use
        */
        var compositeDisposables: CompositeDisposable = CompositeDisposable()


    }


    protected fun view(): V {
        return mView
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposables.add(disposable)
    }

}