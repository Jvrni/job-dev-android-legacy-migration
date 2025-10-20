package com.goomer.menu.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.goomer.designsystem.theme.Colors
import com.goomer.designsystem.theme.Dimens
import com.goomer.menu.R
import com.goomer.menu.details.contract.MenuDetailContract

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuDetailScreen(state: MenuDetailContract.State, event: (MenuDetailContract.Event) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {

        TopAppBar(
            navigationIcon = {
                IconButton(onClick = { event.invoke(MenuDetailContract.Event.OnBack) }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_left),
                        contentDescription = null,
                        tint = Colors.tertiary
                    )
                }
            },
            title = {
                Text(
                    text = state.menu?.name ?: ""
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Colors.primary,
                titleContentColor = Colors.tertiary
            )
        )

        Text(
            modifier = Modifier.padding(top = Dimens.medium_padding, start = Dimens.medium_padding),
            text = state.menu?.name ?: "",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier.padding(start = Dimens.medium_padding),
            text = state.menu?.description ?: ""
        )

        Text(
            modifier = Modifier.padding(
                top = Dimens.xxsmall_padding,
                start = Dimens.medium_padding
            ),
            text = stringResource(R.string.price, state.menu?.price ?: 0.0)
        )
    }
}