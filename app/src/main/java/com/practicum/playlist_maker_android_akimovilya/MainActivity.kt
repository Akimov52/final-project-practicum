package com.practicum.playlist_maker_android_akimovilya

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                MainScreen(
                    onSearchClick = {
                        startActivity(
                            Intent(this, SearchActivity::class.java)
                        )
                    },
                    onPlaylistClick = {
                        Toast.makeText(
                            this,
                            "Нажата кнопка \"Плейлисты\"",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    onFavoritesClick = {
                        Toast.makeText(
                            this,
                            "Нажата кнопка \"Избранное\"",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    onSettingsClick = {
                        startActivity(
                            Intent(this, SettingsActivity::class.java)
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun MainScreen(
    onSearchClick: () -> Unit,
    onPlaylistClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    val blue = Color(0xFF3775E8)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(82.dp)
                .background(
                    blue,
                    RoundedCornerShape(
                        topStart = 12.dp,
                        topEnd = 12.dp
                    )
                )
                .padding(16.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                text = "Playlist maker",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = (-12).dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 18.dp,
                        topEnd = 18.dp
                    )
                )
                .background(Color.White)
                .padding(top = 8.dp)
        ) {
            MenuItem(
                title = "Поиск",
                icon = Icons.Default.Search,
                onClick = onSearchClick
            )

            MenuItem(
                title = "Плейлисты",
                icon = Icons.Default.LibraryMusic,
                onClick = onPlaylistClick
            )

            MenuItem(
                title = "Избранное",
                icon = Icons.Outlined.FavoriteBorder,
                onClick = onFavoritesClick
            )

            MenuItem(
                title = "Настройки",
                icon = Icons.Default.Settings,
                onClick = onSettingsClick
            )
        }
    }
}

@Composable
fun MenuItem(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
            .clickable { onClick() }
            .padding(horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = Color(0xFF19191F),
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF19191F)
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = Color(0xFFADB0B7),
            modifier = Modifier.size(24.dp)
        )
    }
}
