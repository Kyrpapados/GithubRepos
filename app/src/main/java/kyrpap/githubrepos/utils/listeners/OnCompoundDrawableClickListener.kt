package kyrpap.githubrepos.utils.listeners

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import android.widget.TextView

class OnCompoundDrawableClickListener(private val mCompoundDrawableCallback: CompoundDrawableCallback) : View.OnTouchListener {

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP && view is TextView) {
            val compoundDrawable = view.compoundDrawables[DRAWABLE_RIGHT]

            if (compoundDrawable != null && event.rawX >= view.right - compoundDrawable.bounds.width()) {
                // compound drawable click
                mCompoundDrawableCallback.compoundDrawableClicked()
                return true
            } else {
                // edit text click
                mCompoundDrawableCallback.viewClicked()
                return true
            }
        }

        return true
    }

    interface CompoundDrawableCallback {
        fun compoundDrawableClicked()

        fun viewClicked()
    }

    companion object {
        private const val DRAWABLE_RIGHT = 2
    }
}
