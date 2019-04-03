package com.smcorp.pw.common.UI

import android.app.Dialog
import android.os.Bundle
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.smcorp.pw.R
import com.smcorp.pw.common.extension.onClick
import kotlinx.android.synthetic.main.fragment_alert_dialog.*


class AlertDialogFragment: DialogFragment() {

    private var mTitle: String? = null
    private var mMessage: String? = null
    private var mTextLeftButton: String? = null
    private var mTextRightButton: String? = null
    private var mSpannableMessage: SpannableString? = null
    private var inflatedView: View? = null
    private var mCancelButtonVisible = false
    private var redColorTextRightButton = true

    var succesFun = {}

    val fragmentTag = "Alert"

    interface LeftButtonDialogListener {
        fun onLeftButtonDialogClick()
    }

    interface OnRightButtonDialogListener {
        fun onRightButtonDialogClick()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflatedView
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!)
        val inflater = activity!!.layoutInflater
        inflatedView = inflater.inflate(R.layout.fragment_alert_dialog, null)
        builder.setView(inflatedView)

        return builder.create()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mTitle?.let {
            titleTextView.text = it
        }
        if (mTitle.isNullOrBlank()) {
            rootLinearLayout.removeView(titleTextView)
        }
        mMessage?.let {
            messageTextView.text = it
        }
        mSpannableMessage?.let {
            messageTextView.text = it
        }

        if (!redColorTextRightButton && this.context != null) {
            cancelButton.setTextColor(ContextCompat.getColor(this.context!!, R.color.dark))
        }

        if (mCancelButtonVisible) {
            cancelButton.visibility = View.VISIBLE
        } else {
            cancelButton.visibility = View.GONE
        }

        mTextRightButton?.let {
            cancelButton.text = it
        }

        mTextLeftButton?.let {
            okButton.text = it
        }

        okButton.onClick {
            val listener = targetFragment as? LeftButtonDialogListener
            listener?.onLeftButtonDialogClick()
            if (!mCancelButtonVisible) {
                succesFun()
            }
            dialog.cancel()
        }


        cancelButton.onClick {
            val listener = targetFragment as? OnRightButtonDialogListener
            listener?.onRightButtonDialogClick()
            succesFun()
            dialog.cancel()
        }


    }


    fun setTitle(title: String) {
        mTitle = title
    }

    fun setMessage(message: String) {
        mMessage = message
    }

    fun setSpannableMessage(message: SpannableString) {
        mSpannableMessage = message
    }

    fun setVisibleRightBtn(show: Boolean) {
        mCancelButtonVisible = show
    }

    fun setTextLeftButton(text: String) {
        mTextLeftButton = text
    }

    fun setTextRightButton(text: String, colorTextRed: Boolean = true) {
        mTextRightButton = text
        redColorTextRightButton = colorTextRed
    }
}
