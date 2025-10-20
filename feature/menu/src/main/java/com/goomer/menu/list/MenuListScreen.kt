package com.goomer.menu.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goomer.designsystem.components.MenuItem
import com.goomer.designsystem.components.MenuItemEntity
import com.goomer.designsystem.theme.Colors
import com.goomer.menu.R
import com.goomer.menu.list.contract.MenuListContract

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuListScreen(state: MenuListContract.State, event: (MenuListContract.Event) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.background)
    ) {
        stickyHeader {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Colors.primary,
                    titleContentColor = Colors.tertiary
                )
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Colors.background)
                    .padding(16.dp),
                text = stringResource(R.string.menu_title),
                fontSize = 22.sp
            )
        }

        when {
            state.showLoading -> {
                item {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
            }

            state.showError -> {
                item {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = stringResource(R.string.error_generic)
                        )

                        Button(onClick = { event.invoke(MenuListContract.Event.OnStart) }) {
                            Text(text = stringResource(R.string.retry))
                        }
                    }
                }
            }

            else -> {
                items(state.list) { item ->
                    MenuItem(
                        entity = MenuItemEntity(
                            name = item.name,
                            description = item.description,
                            price = stringResource(R.string.price, item.price),
                            imageUrl = item.imageUrl
                        )
                    ) {
                        event.invoke(MenuListContract.Event.OnNavigate(item))
                    }
                }
            }
        }
    }
}
