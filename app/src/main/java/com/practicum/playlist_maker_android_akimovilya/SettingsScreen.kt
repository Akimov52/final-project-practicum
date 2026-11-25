package com.practicum.playlist_maker_android_akimovilya

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen(onBackClick: () -> Unit) {
    val context = LocalContext.current

    var darkThemeEnabled by rememberSaveable { mutableStateOf(false) }

    val shareMessage = stringResource(R.string.share_message)
    val shareChooserTitle = stringResource(R.string.share_chooser_title)

    val developerEmail = stringResource(R.string.developer_email)
    val emailSubject = stringResource(R.string.developer_email_subject)
    val emailBody = stringResource(R.string.developer_email_body)

    val offerUrl = stringResource(R.string.practicum_offer_url)

    val emailErrorMessage = stringResource(R.string.email_client_not_found)
    val browserErrorMessage = stringResource(R.string.browser_not_found)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ScreenHeader(
            title = stringResource(R.string.settings_screen_title),
            onBackClick = onBackClick
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.settings_dark_theme),
                color = PlaylistText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.weight(1f))

            Switch(
                checked = darkThemeEnabled,
                onCheckedChange = { darkThemeEnabled = it }
            )
        }

        Divider(color = Color(0xFFE6E8EB))

        SettingsActionRow(
            title = stringResource(R.string.settings_share_app),
            icon = Icons.Default.Share,
            onClick = {
                val sendIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, shareMessage)
                }

                context.startActivity(
                    Intent.createChooser(sendIntent, shareChooserTitle)
                )
            }
        )

        SettingsActionRow(
            title = stringResource(R.string.settings_contact_developers),
            icon = Icons.Default.Email,
            onClick = {
                val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:$developerEmail")
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(developerEmail))
                    putExtra(Intent.EXTRA_SUBJECT, emailSubject)
                    putExtra(Intent.EXTRA_TEXT, emailBody)
                }

                launchExternalIntent(
                    context = context,
                    intent = emailIntent,
                    errorMessage = emailErrorMessage
                )
            }
        )

        SettingsActionRow(
            title = stringResource(R.string.settings_offer),
            icon = Icons.Default.Description,
            onClick = {
                val offerIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(offerUrl)
                )

                launchExternalIntent(
                    context = context,
                    intent = offerIntent,
                    errorMessage = browserErrorMessage
                )
            }
        )
    }
}

@Composable
private fun SettingsActionRow(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = PlaylistText,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = title,
            color = PlaylistText,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = PlaylistArrow,
            modifier = Modifier.size(22.dp)
        )
    }
}

private fun launchExternalIntent(
    context: Context,
    intent: Intent,
    errorMessage: String
) {
    try {
        context.startActivity(intent)
    } catch (exception: ActivityNotFoundException) {
        Toast.makeText(
            context,
            errorMessage,
            Toast.LENGTH_SHORT
        ).show()
    }
}
