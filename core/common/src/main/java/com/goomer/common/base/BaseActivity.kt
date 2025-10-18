package com.goomer.common.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseActivity<VM : BaseViewModel<T>, VB : ViewBinding, T>(
    private val bindingInflater: (LayoutInflater) -> VB,
) : AppCompatActivity() {

    protected lateinit var binding: VB
    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingInflater(layoutInflater)

        setContentView(binding.root)
        observeUiState()
        setupUI()
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { state ->
                    when (state) {
                        is UiState.Idle -> Unit
                        is UiState.Loading -> showLoading()
                        is UiState.Success -> {
                            hideLoading()
                            onSuccess(state.data)
                        }
                        is UiState.Error -> {
                            hideLoading()
                            showError()
                        }
                    }
                }
            }
        }
    }

    open fun setupUI() {}
    open fun showLoading() {}
    open fun hideLoading() {}
    open fun showError() {}
    open fun onSuccess(data: T) {}
}
