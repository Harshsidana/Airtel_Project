package com.appstreet.airtelassignment.ui.home

import android.os.Bundle
import com.appstreet.airtelassignment.R
import com.bumptech.glide.Glide
import com.view.AbstractUserDetailActivity
import kotlinx.android.synthetic.main.activity_user_details.*

class UserDetailsActivity : AbstractUserDetailActivity() {
    override fun getLayoutRes(): Int {
        return R.layout.activity_user_details
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.getBundleExtra(resources.getString(R.string.bundle_text))


        userNamevalue.text = bundle.getString(resources.getString(R.string.username_text))
        nameTextvalue.text = bundle.getString(resources.getString(R.string.name_text))
        urlTextValue.text = bundle.getString(resources.getString(R.string.url_text))
        Glide.with(avatarImage)
            .load(bundle.getString(resources.getString(R.string.avatar_text)))
            .into(avatarImage)

        repo_detail_text_value.text = bundle.getString(resources.getString(R.string.repo_desc_text))
        repo_name_value.text = bundle.getString(resources.getString(R.string.repo_name_text))
        repo_url_text_value.text = bundle.getString(resources.getString(R.string.repo_url_text))
    }
}
