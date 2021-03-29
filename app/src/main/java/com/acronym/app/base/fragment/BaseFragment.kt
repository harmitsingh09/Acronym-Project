@file:Suppress("DEPRECATION")

package com.acronym.app.base.fragment

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.acronym.app.base.activity.BaseActivity
import com.acronym.app.base.viewmodel.BaseViewModel

@Suppress("DEPRECATION")
abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment(), LifecycleOwner {

    private var mActivity: BaseActivity<T, V>? = null
    private var mViewDataBinding: T? = null
    private var mViewModel: V? = null
    protected var mProgress: ProgressDialog? = null


    abstract fun getBindingVariable(): Int
    @LayoutRes
    abstract fun getLayoutId(): Int


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            mActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mProgress = ProgressDialog(context)
        mProgress?.setMessage("loading")
        mProgress?.setCancelable(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return mViewDataBinding?.root
    }


    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding!!.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding!!.executePendingBindings()
    }

    open fun getBaseActivity(): BaseActivity<T, V>? {
        return mActivity
    }

    open fun getViewDataBinding(): T? {
        return mViewDataBinding
    }

    open fun getViewModel(): V? {
        return mViewModel
    }


    protected open fun hideProgressDialog() {
        mProgress?.let { it.dismiss() }
    }

    protected open fun showProgressDialog() {
        mProgress?.let { it.show() }
    }


}

