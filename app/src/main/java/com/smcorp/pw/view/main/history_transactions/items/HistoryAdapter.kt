package com.smcorp.pw.view.main.history_transactions.items

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smcorp.data.model.response.Transaction
import com.smcorp.pw.R
import com.smcorp.pw.common.extension.onClick
import kotlinx.android.synthetic.main.history_item.view.*


class HistoryAdapter(val items : List<Transaction>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.history_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvName?.text = items.get(position).username
        holder?.tvBalance?.text = items.get(position).balance.toString()
        holder?.tvDate?.text = items.get(position).date
        holder?.tvAmount?.text = items.get(position).amount.toString()
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }


}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvName = view.textViewName
    val tvBalance = view.textViewBalance
    val tvDate = view.textViewDate
    val tvAmount = view.textViewAmount



}
