package com.smcorp.pw.common.UI

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import com.claudiodegio.msv.BaseMaterialSearchView
import com.claudiodegio.msv.R
import com.smcorp.pw.common.extension.convertDpToPx
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject


class ToolbarSearchView : BaseMaterialSearchView {

    private val rightMargin = 40f

    private val searchQueryChangeSubject = PublishSubject.create<String>()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var hint : String = ""

    val searchQueryChangeObservable: Flowable<String> = searchQueryChangeSubject.toFlowable(BackpressureStrategy.DROP)


    fun setEndSelectCursor() {
        mETSearchText.setSelection(mETSearchText.text.length)
    }

    override fun showSearch(animate: Boolean) {

        mETSearchText.hint = hint


        mETSearchText.setTextColor(resources.getColor(com.smcorp.pw.R.color.colorPrimary))
        mETSearchText.setPaddingInDp(0f, 0f, rightMargin,0f)
        super.showSearch(animate)
        this.showKeyboard(this.mETSearchText)
    }

    override fun getLayoutId(): Int {
        return com.smcorp.pw.R.layout.toolbar_search_view_layout
    }

    override fun onTextChanged(charSequence: CharSequence?, i: Int, i1: Int, i2: Int) {
        searchQueryChangeSubject.onNext(charSequence.toString())
        super.onTextChanged(charSequence, i, i1, i2)
    }

    fun setText(text: String) {
        mETSearchText.setText(text)
    }
}

fun EditText.setPaddingInDp(bottom : Float, left : Float, right : Float, top : Float) {

    this.setPadding(
        if (bottom != 0f) convertDpToPx (bottom) else 0,
        if (left != 0f) convertDpToPx (left) else 0,
        if (right != 0f) convertDpToPx (right) else 0,
        if (top != 0f) convertDpToPx (top) else 0
    )
}