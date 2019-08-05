package com.appstreet.airtelassignment.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appstreet.airtelassignment.R
import com.appstreet.airtelassignment.ui.home.adapter.Adapter
import com.appstreet.base.cell.Cell
import com.appstreet.base.extension.showLongToast
import com.appstreet.base.model.DataState
import com.appstreet.home.cell.HomeCell
import com.appstreet.home.model.response.ApiReponse
import com.appstreet.home.view.AbstractHomeActivity
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AbstractHomeActivity(), Adapter.CellListener {


    override fun getLayoutRes(): Int {
        return R.layout.activity_home
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getCardCellDetails().observe(this, Observer<DataState<List<ApiReponse>>> {
            when (it) {
                is DataState.Success -> {
                    val list: MutableList<Cell> = ArrayList()
                    for (i in 0 until it.data.size) {
                        list.add(HomeCell(it.data[i]))
                    }

                    val rvAdapter = Adapter(list, this)
                    recyclerView.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
                    recyclerView.adapter = rvAdapter


                }
                is DataState.Failure -> {
                    showLongToast(resources.getString(R.string.error_fetching_text))
                }
            }

        })


    }

    override fun onCellActivityClick(response: ApiReponse) {
        val intent = Intent(applicationContext, UserDetailsActivity::class.java)
        val bundle = Bundle()
        bundle.putString(resources.getString(R.string.username_text), response.username)
        bundle.putString(resources.getString(R.string.name_text), response.name)
        bundle.putString(resources.getString(R.string.url_text), response.url)
        bundle.putString(resources.getString(R.string.avatar_text), response.avatar)
        bundle.putString(resources.getString(R.string.repo_desc_text), response.repo.description)
        bundle.putString(resources.getString(R.string.repo_name_text), response.repo.name)
        bundle.putString(resources.getString(R.string.repo_url_text), response.repo.url)
        intent.putExtra(resources.getString(R.string.bundle_text), bundle)
        startActivity(intent)


    }
}
