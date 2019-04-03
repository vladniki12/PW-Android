package com.smcorp.pw.common.extension

import android.content.res.Resources
import android.view.View

fun View.onClick(handler: () -> Unit) {
    setOnClickListener {
        handler()

    }
}

fun View.convertDpToPx(dp : Float) : Int {

    return (dp * Resources.getSystem().displayMetrics.density).toInt()
}

fun androidx.recyclerview.widget.RecyclerView.setVerticalLlm() {
    val llm = androidx.recyclerview.widget.LinearLayoutManager(this.context)
    llm.orientation = androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
    this.layoutManager = llm
}