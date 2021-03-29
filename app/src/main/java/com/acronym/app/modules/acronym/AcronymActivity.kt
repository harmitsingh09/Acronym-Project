package com.acronym.app.modules.acronym

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.acronym.app.R
import com.acronym.app.base.activity.BaseActivity
import com.acronym.app.databinding.ActivityAcronameBinding
import com.acronym.app.network.Resource
import com.acronym.app.utils.AcronymUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AcronymActivity : BaseActivity<ActivityAcronameBinding, AcronymViewModel>() {

    private val questionsViewModel: AcronymViewModel by viewModels()
    private  var acronymAdapter: AcronymAdapter?=null
    val SEARCH_TIME_DELAY = 500L

    companion object {
        fun createIntent(context: Context?): Intent {
            return Intent(context, AcronymActivity::class.java)
        }
    }

    override fun getBindingVariable(): Int {
        return 0
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_acroname
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        questionsViewModel // just to initialize view model
        init()
    }

    private fun init() {
        setupAcronymAdapter()
        subscribeToLiveData()
        setupSearchView()
    }

    private fun setupSearchView() {
        var job: Job? = null

        getViewDataBinding()!!.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                job?.cancel()
                job = MainScope().launch {
                    delay(SEARCH_TIME_DELAY)
                    s?.let {
                        if (AcronymUtil.isAcronymValidToSearch(s.toString())) {
                            getAbbreviation(s.toString())
                        }
                    }
                }
            }

        })
    }

    fun getAbbreviation(shortName: String) {
        questionsViewModel.getAbbreviation(shortName.toString())
    }

    private fun subscribeToLiveData() {
        questionsViewModel.acronameResponse.observe(this, Observer {
            when(it) {
                is Resource.Loading -> {
                    showLoading()
                }

                is Resource.Success -> {
                    hideLoading()

                    if (AcronymUtil.isListNotEmpty(it.data)) {
                        acronymAdapter?.differ?.submitList(it.data?.get(0)?.lfs)
                    } else {
                        setEmptyView()
                    }
                }

                is Resource.Failure -> {
                    setEmptyView()
                }
            }
        })
    }

    private fun setEmptyView() {
        getViewDataBinding()!!.progressBar.visibility = View.GONE
        getViewDataBinding()!!.emptyView.visibility = View.VISIBLE
        getViewDataBinding()!!.acromineRecycler.visibility = View.GONE
    }

    private fun showLoading() {
        getViewDataBinding()!!.progressBar.visibility = View.VISIBLE
        getViewDataBinding()!!.emptyView.visibility = View.GONE
        getViewDataBinding()!!.acromineRecycler.visibility = View.GONE
    }

    private fun hideLoading() {
        getViewDataBinding()!!.progressBar.visibility = View.GONE
        getViewDataBinding()!!.emptyView.visibility = View.GONE
        getViewDataBinding()!!.acromineRecycler.visibility = View.VISIBLE
    }

    private fun setupAcronymAdapter() {
        acronymAdapter = AcronymAdapter(this)
        getViewDataBinding()!!.acromineRecycler.apply {
            adapter = acronymAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

}