package kyrpap.githubrepos.ui.base

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatDelegate
import android.widget.Toast
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kyrpap.githubrepos.R


abstract class BaseActivity : DaggerAppCompatActivity(), BaseContract.IView {

    /**
     * A dialog showing a progress indicator and an optional text message or
     * view.
     */
    protected var mProgressDialog: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this) // Call before super!
        super.onCreate(savedInstanceState)
        attachView()
        setContentView(setLayout())
        handleIntent(intent)
        initialzeProgressDialoge()
        init(savedInstanceState)

    }

    fun initialzeProgressDialoge() {

        if (mProgressDialog == null) {

            mProgressDialog = ProgressDialog(this)
            mProgressDialog!!.isIndeterminate = true
            mProgressDialog!!.setCancelable(false)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        System.gc()
        detachView()
        System.runFinalization()
        dismissProgress()
        mProgressDialog = null
    }


    @LayoutRes
    abstract fun setLayout(): Int

    abstract fun attachView()
    abstract fun detachView()
    abstract fun init(savedInstanceState: Bundle?)
    abstract fun handleIntent(intent: Intent)

    fun showProgress(msgResId: Int,
                     keyListener: DialogInterface.OnKeyListener?) {
        if (isFinishing)
            return

        if (mProgressDialog!!.isShowing) {
            return
        }

        if (msgResId != 0) {
            mProgressDialog?.setMessage(resources.getString(msgResId))
        }

        if (keyListener != null) {
            mProgressDialog?.setOnKeyListener(keyListener)

        } else {
            mProgressDialog?.setCancelable(false)
        }
        mProgressDialog?.show()
    }

    /**
     * @param isCancel
     */
    fun setCancelableProgress(isCancel: Boolean) {
        if (mProgressDialog != null) {
            mProgressDialog?.setCancelable(true)
        }
    }

    /**
     * cancel progress dialog.
     */
    fun dismissProgress() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog?.dismiss()
        }
    }


    override fun hideLoading() {
        dismissProgress()
    }

    override fun showLoading() {
        showProgress(R.string.loading, null)
    }

    override fun loadError(e: Throwable) {
        showHttpError(e)
    }

    override fun loadError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


    /*
    Improper handling in real case
     */

    protected fun showHttpError(e: Throwable) {
        loadError(e.localizedMessage)
    }
}