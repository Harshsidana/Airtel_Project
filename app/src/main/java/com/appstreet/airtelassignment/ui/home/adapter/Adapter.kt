package com.appstreet.airtelassignment.ui.home.adapter

import com.appstreet.base.cell.Cell
import com.appstreet.airtelassignment.ui.home.adapter.delegate.ElementDelegate
import com.appstreet.home.model.response.ApiReponse
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class Adapter(private val modelList: MutableList<Cell>,cellListener: CellListener) :  ListDelegationAdapter<List<Cell>>() {
    interface CellListener {
        fun onCellActivityClick(response: ApiReponse)

    }
    init {
        delegatesManager
            .addDelegate(ElementDelegate(cellListener::onCellActivityClick))

        setItems(modelList)
    }}