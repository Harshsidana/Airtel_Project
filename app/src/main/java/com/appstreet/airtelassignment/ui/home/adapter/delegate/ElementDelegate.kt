package com.appstreet.airtelassignment.ui.home.adapter.delegate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appstreet.base.cell.Cell
import com.appstreet.airtelassignment.R
import com.appstreet.home.cell.HomeCell
import com.appstreet.home.model.response.ApiReponse
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate


import kotlinx.android.synthetic.main.single_element.view.*

class ElementDelegate(val onClick: (ApiReponse) -> Unit) :
    AbsListItemAdapterDelegate<HomeCell, Cell, ElementDelegate.ElementViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup): ElementViewHolder {

        return ElementViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.single_element,
                parent,
                false
            ), onClick
        )
    }

    override fun isForViewType(item: Cell, items: MutableList<Cell>, position: Int): Boolean {
        return item is HomeCell
    }

    override fun onBindViewHolder(
        item: HomeCell,

        holder: ElementViewHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item.response)
    }


    class ElementViewHolder(itemView: View, private val onClickListener: (ApiReponse) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(item: ApiReponse) {

            with(itemView) {
                repoName.text = item.repo.name
                userName.text = item.username
                Glide.with(avatarImage)
                    .load(item.avatar)
                    .into(avatarImage)
            }

            itemView.setOnClickListener {
                onClickListener(item)
            }
        }
    }

}
