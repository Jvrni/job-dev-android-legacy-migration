package com.goomer.ps.feature.menu.list

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.goomer.common.base.BaseViewModel
import com.goomer.data.MenuRepository
import com.goomer.data.models.Menu
import com.goomer.ps.feature.menu.list.state.MenuListState
import com.goomer.ps.navigation.MenuNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class MenuListViewModel @Inject constructor(
    private val repository: MenuRepository,
    private val navigator: MenuNavigator
) : BaseViewModel<MenuListState>() {
    fun loadData() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                setLoading()

                repository.getList()
                    .catch {
                        setError()
                    }.collect {
                        setSuccess(MenuListState(it))
                    }
            }
        }
    }

    fun navigateTo(context: Context, menu: Menu) {
        navigator.navigateToDetail(context, menu)
    }

}