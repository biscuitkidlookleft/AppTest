package com.biscuitkid.apptest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.biscuitkid.apptest.R
import com.biscuitkid.apptest.model.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mOnItemClickListener : OnItemClickListener? = null
    private var list =  listOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_user, parent, false
        )
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder.itemView.textName.text  = "${list[position].firstName} ${list[position].lastName}"

        holder.itemView.setOnClickListener {
            mOnItemClickListener?.onItemClick(list[position], position)
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.mOnItemClickListener = listener
    }

    fun addAll(itemList: List<User>) {
        list = itemList
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(item: User, position: Int)
    }

    internal class UserViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
