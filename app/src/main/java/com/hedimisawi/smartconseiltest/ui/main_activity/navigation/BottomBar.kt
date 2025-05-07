package com.hedimisawi.smartconseiltest.ui.main_activity.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hedimisawi.smartconseiltest.ui.theme.bottomBarIndicatorBlue


data class BottomBarItem(
    @DrawableRes val icon: Int,
    val label: String,
    val route: String,
)


@Composable
fun BottomBar(
    selectedRoute: String?, onSelected: (String) -> Unit, items: List<BottomBarItem>,
) {
        androidx.compose.material3.NavigationBar(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
            tonalElevation = 0.dp,
        ) {
            items.forEach { item ->
                val selected = item.route == selectedRoute
                NavigationBarItem(colors = NavigationBarItemDefaults.colors(
                    indicatorColor = bottomBarIndicatorBlue,
                ),
                    selected = selected,
                    label = { Text(item.label) },
                    onClick = { onSelected(item.route) },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.label,
                            tint = if(selected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onBackground.copy(0.6f)
                        )

                    })
            }

        }
}