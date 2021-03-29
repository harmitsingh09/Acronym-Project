@file:Suppress("DEPRECATION")

package com.acronym.app.base.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.acronym.app.base.viewmodel.BaseViewModel
import com.google.android.material.snackbar.Snackbar


abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {
    private var mViewDataBinding: T? = null
    private var mViewModel: V? = null
    protected var mProgress: ProgressDialog? = null

    abstract fun getBindingVariable(): Int

    @LayoutRes
    abstract fun getLayoutId(): Int

    protected var mHandler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
    }


    open fun getViewModel(): V? {
        return mViewModel
    }

    open fun getViewDataBinding(): T? {
        return mViewDataBinding
    }

    protected open fun hideProgressDialog() {
        mProgress?.let { it.dismiss() }
    }

    protected open fun showProgressDialog() {
        mProgress?.let { it.show() }
    }

    protected open fun showSnackBar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }

    protected open fun attachFragment(containerId: Int, fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(containerId, fragment)
        fragmentTransaction.commitAllowingStateLoss()
    }

    open fun detachFragment(fragment: Fragment?) {
        if (fragment == null) return
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.remove(fragment)
        fragmentTransaction.commitAllowingStateLoss()
    }

}
