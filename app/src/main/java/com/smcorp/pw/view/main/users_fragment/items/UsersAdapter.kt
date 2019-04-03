package com.smcorp.pw.view.main.users_fragment.items

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smcorp.pw.R
import com.smcorp.pw.common.extension.onClick
import kotlinx.android.synthetic.main.users_item.view.*

class UsersAdapter(val items : List<UsersModel>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.users_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvName?.text = items.get(position).name
        holder?.prepareView(items.get(position).onClick)
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }


}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvName = view.textViewName

    fun prepareView(onClick: (String) -> Unit) {
        tvName.onClick {
            onClick(tvName.text.toString())
        }
    }


}

class UsersModel(var name: String, var onClick: (String) -> Unit )