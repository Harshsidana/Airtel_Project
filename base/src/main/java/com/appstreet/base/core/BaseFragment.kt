package com.appstreet.base.core

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.appstreet.base.extension.getColorFromRes

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    companion object {
        const val ALERT_ERROR = "alert_error"
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    @ColorRes
    open fun statusBarColor(): Int? = null

    open fun lightColorIcon(): Boolean = false

    abstract val viewModel: VM

    fun fragmentTag(): String = this::class.java.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutRes(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeStatusBarColor()
        changeStatusBarIconColor()
        viewInitialization(view)
        onPreparationFinished(view)
        viewModel.doOnViewAttached()
    }

    protected fun showLoading(isShow: Boolean) {
        (activity as BaseActivity<*>?)?.showLoading(isShow)
    }

    private fun changeStatusBarColor() {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return
        activity?.window?.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor()?.let { color -> statusBarColor = context.getColorFromRes(color) }
        }
    }

    fun showBackButton() {
        (activity as BaseActivity<*>).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun hideBackButton() {
        (activity as BaseActivity<*>).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    fun setToolBarTitle(title: String) {
        (activity as BaseActivity<*>).supportActionBar?.title = title
    }

    private fun changeStatusBarIconColor() {
        activity?.window?.decorView?.rootView?.systemUiVisibility = if (lightColorIcon()) 0 else 8192
    }


    /**
     * This method is called after view has been created.
     * This method should be used to initialize all views that are needed to be created (and recreated after fragment is reattached)
     * @param view The root view of the fragment
     */
    open fun viewInitialization(view: View) {}

    /**
     * This method is called after viewInitialization(view) is finished
     * @param view The root view of the fragment
     */
    open fun onPreparationFinished(view: View) {}

    open fun onNetworkRequestFailure(errorCode: Int, errorMap: Map<String, String>) {}
}