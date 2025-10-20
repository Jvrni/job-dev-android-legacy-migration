package com.goomer.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goomer.designsystem.theme.Colors
import com.goomer.designsystem.theme.Dimens

@Composable
fun MenuItem(
    entity: MenuItemEntity,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = Colors.background),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.medium_padding),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = entity.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = entity.description
            )

            Text(
                modifier = Modifier.padding(top = Dimens.xxsmall_padding),
                text = entity.price
            )
        }
    }
}

data class MenuItemEntity(
    val name: String,
    val description: String,
    val price: String,
    val imageUrl: String
)