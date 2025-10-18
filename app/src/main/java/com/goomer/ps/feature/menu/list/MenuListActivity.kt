package com.goomer.ps.feature.menu.list

import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.goomer.common.base.BaseActivity
import com.goomer.ps.databinding.ActivityMenuListBinding
import com.goomer.ps.feature.menu.list.adapter.MenuAdapter
import com.goomer.ps.feature.menu.list.state.MenuListState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuListActivity() :
    BaseActivity<MenuListViewModel, ActivityMenuListBinding, MenuListState>(ActivityMenuListBinding::inflate) {

    override val viewModel: MenuListViewModel by viewModels()

    override fun setupUI() {
        viewModel.loadData()

        binding.btnRetry.setOnClickListener {
            viewModel.loadData()
        }
    }

    override fun onSuccess(data: MenuListState) {
        binding.rvMenu.layoutManager = LinearLayoutManager(this)
        binding.rvMenu.adapter = MenuAdapter(data.list) { item ->
            viewModel.navigateTo(this, item)
        }
    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.errorContainer.visibility = View.GONE
        binding.rvMenu.visibility = View.GONE
    }

    override fun hideLoading() {
        binding.progressBar.visibility = View.GONE
        binding.errorContainer.visibility = View.GONE
        binding.rvMenu.visibility = View.VISIBLE
    }

    override fun showError() {
        binding.progressBar.visibility = View.GONE
        binding.errorContainer.visibility = View.VISIBLE
        binding.rvMenu.visibility = View.GONE
    }
}