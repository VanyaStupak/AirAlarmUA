package com.example.settings

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.core.app.NotificationManagerCompat
import com.example.settings.components.SettingsItem
import com.example.settings.components.TopBar
import dev.stupak.ui.AirAlarmUATheme
import dev.stupak.ui.R
import dev.stupak.ui.theme.Theme

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    onChangeRegionClick: () -> Unit,
    uiState: SettingsState,
    onThemeUpdated: (Boolean) -> Unit,
    onAction: (SettingsIntent) -> Unit,
) {
    val context = LocalContext.current

    var isSwitchChecked by remember { mutableStateOf(false) }
    val isSystemInDarkTheme = isSystemInDarkTheme()

    fun isNotificationPermissionGranted(): Boolean =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) ==
                android.content.pm.PackageManager.PERMISSION_GRANTED
        } else {
            NotificationManagerCompat.from(context).areNotificationsEnabled()
        }

    val permissionLauncher =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
                isSwitchChecked = granted
            }
        } else {
            null
        }

    LaunchedEffect(Unit) {
        isSwitchChecked = isNotificationPermissionGranted()
    }

    var expanded by remember { mutableStateOf(false) }

    val themeAuto = stringResource(dev.stupak.localisation.R.string.auto)
    val themeLight = stringResource(dev.stupak.localisation.R.string.light)
    val themeDark = stringResource(dev.stupak.localisation.R.string.dark)

    val themeOptions =
        listOf(
            themeAuto,
            themeLight,
            themeDark,
        )

    val selectedTheme =
        when (uiState.theme) {
            SettingsState.Theme.AUTO -> themeOptions[0]
            SettingsState.Theme.LIGHT -> themeOptions[1]
            SettingsState.Theme.DARK -> themeOptions[2]
        }

    Scaffold(
        containerColor = Theme.color.neutral2,
        topBar = {
            TopBar(onBackClick = onBackClick)
        },
        content = { paddingValues ->
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, start = 12.dp, end = 12.dp)
                        .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Column(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .background(Theme.color.primary100, RoundedCornerShape(32.dp))
                            .padding(top = 32.dp, bottom = 24.dp, start = 32.dp, end = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Text(
                        text = stringResource(dev.stupak.localisation.R.string.notifications),
                        style = Theme.typography.heading4,
                        color = Theme.color.white,
                    )

                    SettingsItem(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_notifications),
                        title = stringResource(dev.stupak.localisation.R.string.allow_notifications),
                        contentColor = Theme.color.white,
                    ) {
                        Switch(
                            checked = isSwitchChecked,
                            onCheckedChange = {
                                if (!isSwitchChecked) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                        permissionLauncher?.launch(Manifest.permission.POST_NOTIFICATIONS)
                                    } else {
                                        isSwitchChecked =
                                            NotificationManagerCompat
                                                .from(context)
                                                .areNotificationsEnabled()
                                    }
                                }
                            },
                            enabled = !isSwitchChecked,
                            colors =
                                SwitchDefaults.colors(
                                    checkedThumbColor = Theme.color.neutral05,
                                    checkedTrackColor = Theme.color.primary120,
                                    checkedBorderColor = Color.Transparent,
                                    uncheckedThumbColor = Theme.color.white,
                                    uncheckedTrackColor = Theme.color.neutral7,
                                    uncheckedBorderColor = Color.Transparent,
                                    disabledCheckedTrackColor = Theme.color.primary120,
                                    disabledCheckedThumbColor = Theme.color.primary80,
                                ),
                        )
                    }

                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Theme.color.white,
                    )

                    SettingsItem(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_alert),
                        title = stringResource(dev.stupak.localisation.R.string.alarm_notifications),
                        contentColor = Theme.color.white,
                    ) {
                        Switch(
                            checked = uiState.alertsNotifications,
                            onCheckedChange = {
                                onAction.invoke(SettingsIntent.ToggleAlertsNotifications)
                            },
                            colors =
                                SwitchDefaults.colors(
                                    checkedThumbColor = Theme.color.white,
                                    checkedTrackColor = Theme.color.primary120,
                                    checkedBorderColor = Color.Transparent,
                                    uncheckedThumbColor = Theme.color.white,
                                    uncheckedTrackColor = Theme.color.switchTrack,
                                    uncheckedBorderColor = Color.Transparent,
                                ),
                        )
                    }

                    if (uiState.alertsNotifications) {
                        SettingsItem(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_telegram),
                            title = stringResource(dev.stupak.localisation.R.string.telegram_notifications),
                            contentColor = Theme.color.white,
                        ) {
                            Switch(
                                checked = uiState.telegramNotifications,
                                onCheckedChange = {
                                    onAction.invoke(SettingsIntent.ToggleTelegramNotifications)
                                },
                                colors =
                                    SwitchDefaults.colors(
                                        checkedThumbColor = Theme.color.white,
                                        checkedTrackColor = Theme.color.primary120,
                                        checkedBorderColor = Color.Transparent,
                                        uncheckedThumbColor = Theme.color.white,
                                        uncheckedTrackColor = Theme.color.switchTrack,
                                        uncheckedBorderColor = Color.Transparent,
                                    ),
                            )
                        }
                    }
                }

                Column(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .background(Theme.color.neutral4, RoundedCornerShape(32.dp))
                            .padding(top = 32.dp, bottom = 24.dp, start = 24.dp, end = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Box(
                        modifier =
                            Modifier
                                .background(Theme.color.neutral2, RoundedCornerShape(16.dp))
                                .padding(vertical = 8.dp, horizontal = 12.dp),
                    ) {
                        Text(
                            text = uiState.region,
                            style = Theme.typography.textLargeNormal,
                            color = Theme.color.neutral9,
                        )
                    }

                    SettingsItem(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_pin_area),
                        title = stringResource(dev.stupak.localisation.R.string.change_region),
                        modifier =
                            Modifier
                                .padding(start = 8.dp)
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() },
                                ) { onChangeRegionClick() },
                        contentColor = Theme.color.neutral9,
                    ) {
                        Box(
                            modifier =
                                Modifier
                                    .padding(vertical = 8.dp, horizontal = 16.dp),
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_next),
                                contentDescription = "Icon",
                                tint = Theme.color.neutral9,
                            )
                        }
                    }
                }

                Column(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .background(Theme.color.secondary100, RoundedCornerShape(32.dp))
                            .padding(top = 24.dp, bottom = 24.dp, start = 32.dp, end = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    SettingsItem(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_day_night),
                        title = stringResource(dev.stupak.localisation.R.string.theme),
                        contentColor = Theme.color.neutral9,
                    ) {
                        Box(
                            modifier =
                                Modifier
                                    .clip(shape = RoundedCornerShape(16.dp))
                                    .background(
                                        color = Theme.color.secondary40,
                                    ).clickable { expanded = !expanded }
                                    .padding(vertical = 8.dp, horizontal = 16.dp),
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.End,
                            ) {
                                Text(
                                    text = selectedTheme,
                                    style = Theme.typography.textMediumNormal,
                                    color = Theme.color.neutral9,
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(
                                    imageVector =
                                        if (expanded) {
                                            ImageVector.vectorResource(R.drawable.ic_arrow_up)
                                        } else {
                                            ImageVector.vectorResource(R.drawable.ic_arrow_down)
                                        },
                                    contentDescription = "Dropdown Icon",
                                    tint = Theme.color.neutral9,
                                )
                            }

                            DropdownMenu(
                                expanded = expanded,
                                containerColor = Theme.color.neutral2,
                                properties = PopupProperties(usePlatformDefaultWidth = true),
                                offset = DpOffset(x = 16.dp, y = 14.dp),
                                shape = RoundedCornerShape(16.dp),
                                onDismissRequest = { expanded = false },
                                modifier =
                                    Modifier
                                        .fillMaxWidth(),
                            ) {
                                themeOptions.forEach { option ->
                                    DropdownMenuItem(
                                        onClick = {
                                            onAction.invoke(
                                                SettingsIntent.ChangeTheme(
                                                    when (option) {
                                                        themeAuto -> SettingsState.Theme.AUTO
                                                        themeLight -> SettingsState.Theme.LIGHT
                                                        themeDark -> SettingsState.Theme.DARK
                                                        else -> {
                                                            SettingsState.Theme.AUTO
                                                        }
                                                    },
                                                ),
                                            )
                                            expanded = false
                                            onThemeUpdated.invoke(
                                                when (option) {
                                                    themeAuto -> isSystemInDarkTheme
                                                    themeLight -> false
                                                    themeDark -> true
                                                    else -> isSystemInDarkTheme
                                                },
                                            )
                                        },
                                        text = {
                                            Text(
                                                text = option,
                                                style = Theme.typography.textRegularMedium,
                                                color = Theme.color.neutral9,
                                            )
                                        },
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
    )
}

@Preview(device = "id:pixel_9", showSystemUi = true)
@Composable
fun SettingsScreenPreview() {
    AirAlarmUATheme {
        SettingsScreen(
            onBackClick = {},
            onChangeRegionClick = {},
            onAction = {},
            onThemeUpdated = {},
            uiState = SettingsState(),
        )
    }
}
