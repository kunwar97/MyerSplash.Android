package com.juniperphoton.myersplash.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.juniperphoton.myersplash.R

class ThanksToAdapter(private val mContext: Context) : RecyclerView.Adapter<ThanksToAdapter.ThanksToViewHolder>() {
    private var data: List<String>? = null

    fun refresh(data: List<String>) {
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThanksToViewHolder {
        return ThanksToViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_thanks_to,
                parent, false))
    }

    override fun onBindViewHolder(holder: ThanksToViewHolder, position: Int) {
        holder.bind(data!![holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return if (data == null) 0 else data!!.size
    }

    inner class ThanksToViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mTextView: TextView = itemView as TextView

        fun bind(str: String) {
            mTextView.text = str
        }
    }
}