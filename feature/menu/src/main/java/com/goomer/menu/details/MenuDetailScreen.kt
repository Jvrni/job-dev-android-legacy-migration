package com.goomer.menu.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.goomer.data.models.Menu
import com.goomer.designsystem.theme.Colors
import com.goomer.designsystem.theme.Dimens
import com.goomer.menu.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuDetailScreen(menu: Menu) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        TopAppBar(
            navigationIcon = {
                Icon(painter = painterResource(R.drawable.arrow_left), contentDescription = null)
            },
            title = {
                Text(
                    text = menu.name
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Colors.primary,
                titleContentColor = Colors.tertiary
            )
        )

        Text(
            text = menu.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = menu.description
        )

        Text(
            modifier = Modifier.padding(top = Dimens.xxsmall_padding),
            text = "${menu.price}"
        )
    }
}