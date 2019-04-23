package com.emintolgahanpolat.toolbarexample

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.ImageView
import android.widget.TextView


class CustomRecyclerViewAdapter(var context: Context, var objectList: MutableList<Any>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_QUESTION = 1
    private val TYPE_ANSWER = 2


    override fun getItemViewType(position: Int): Int {
        return TYPE_QUESTION
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = holder.itemViewType
        when (viewType) {
            TYPE_QUESTION -> {
                val questionModel = objectList[position] as Model
                (holder as MyViewHolder).showDetails(questionModel)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (objectList == null) 0 else objectList.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        val viewHolder: RecyclerView.ViewHolder?

        when (viewType) {
            TYPE_QUESTION -> {
                return MyViewHolder(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_row, parent, false)
                )
            }
            else -> viewHolder = null
        }


        return viewHolder!!
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ivIcon = view.findViewById<ImageView>(R.id.ivIcon)
        var tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        var tvSubtitle = view.findViewById<TextView>(R.id.tvSubtitle)

        fun showDetails(model: Model) {
            ivIcon.setImageResource(model.Icon)
            tvTitle.text = model.Title
            tvSubtitle.text = model.Subtitle
        }
    }
}