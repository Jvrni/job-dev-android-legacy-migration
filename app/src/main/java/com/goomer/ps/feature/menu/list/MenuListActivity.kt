package com.goomer.ps.feature.menu.list

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.goomer.common.base.BaseActivity
import com.goomer.ps.MenuItem
import com.goomer.ps.databinding.ActivityMenuListBinding
import com.goomer.ps.feature.menu.details.MenuDetailActivity
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
            onNavigate(item)
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

    override fun showError(message: String?) {
        binding.progressBar.visibility = View.GONE
        binding.errorContainer.visibility = View.VISIBLE
        binding.rvMenu.visibility = View.GONE
    }

    private fun onNavigate(item: MenuItem) {
        val it = Intent(this, MenuDetailActivity::class.java)
        it.putExtra("id", item.id)
        it.putExtra("name", item.name)
        it.putExtra("description", item.description)
        it.putExtra("price", item.price)
        it.putExtra("imageUrl", item.imageUrl)
        startActivity(it)
    }
}