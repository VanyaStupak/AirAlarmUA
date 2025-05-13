package dev.stupak.main

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import dev.stupak.main.components.AlertsHistoryBottomSheet
import dev.stupak.main.components.AlertsInfo
import dev.stupak.main.components.AlertsList
import dev.stupak.main.components.ErrorMessage
import dev.stupak.main.components.MapImage
import dev.stupak.main.components.MapToggleButtons
import dev.stupak.main.maps.common.MapArgs
import dev.stupak.main.maps.common.getRegionMap
import dev.stupak.ui.R
import dev.stupak.ui.maps.getUkraineMap
import dev.stupak.ui.maps.regionsUkraine
import dev.stupak.ui.theme.Theme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    uiState: MainScreenState,
    onSettingsButtonClick: () -> Unit,
    regionName: String,
    isFirstRun: Boolean,
    initialPage: Int,
) {
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(false) }

    val pagerState = rememberPagerState(initialPage = initialPage) { 2 }
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val offsetY = remember { Animatable(1000f) }
    var selectedMap by remember { mutableStateOf("Ukraine") }
    var selectedRange by remember { mutableStateOf("month") }

    LaunchedEffect(isFirstRun) {
        if (isFirstRun && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS,
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    (context as Activity),
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    1001,
                )
            }
        }
    }
    val colors = Theme.color

    val colorsMap =
        remember(uiState.alertsList) {
            uiState.alertsList
                .map { alert ->
                    val oblastId = regionsUkraine.indexOf(alert.locationOblast)
                    oblastId to alert
                }.groupBy { it.first }
                .mapValues { (_, group) ->
                    if (group.any { it.second.locationType == "oblast" }) {
                        colors.mapAlert
                    } else {
                        colors.warning
                    }
                }
        }

    val filteredLocations =
        remember(uiState.alertsList, regionName) {
            uiState.alertsList
                .filter { alert ->
                    when (regionName) {
                        "Київська область" ->
                            alert.locationOblast == regionName ||
                                alert.locationOblast == "м. Київ"

                        else -> alert.locationOblast == regionName
                    }
                }.groupBy { it.alertType }
                .mapValues { (_, alerts) ->
                    alerts
                        .mapNotNull { alert ->
                            when {
                                regionName == "Київська область" ->
                                    alert.locationRaion ?: alert.locationOblast

                                alert.locationType == "raion" ->
                                    alert.locationTitle

                                else -> alert.locationRaion
                            }
                        }.toSet()
                }
        }
    val oblastAlert =
        remember(uiState.alertsList, regionName) {
            uiState.alertsList.any {
                it.locationTitle == regionName
            }
        }

    val mapArgs =
        MapArgs(
            textColor = Theme.color.mapText,
            strokeColor = Theme.color.neutral05,
            defaultColor = Theme.color.neutral5,
            districtsSet = filteredLocations,
            oblastAlert = oblastAlert,
        )

    LaunchedEffect(pagerState.currentPage) {
        listState.scrollToItem(0)

        selectedMap = if (pagerState.currentPage == 0) "Ukraine" else "Oblast"

        offsetY.apply {
            snapTo(1000f)
            animateTo(0f, animationSpec = tween(700))
        }
    }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(Theme.color.neutral2)
                .statusBarsPadding(),
    ) {
        if (!uiState.isConnected) {
            ErrorMessage(stringResource(dev.stupak.localisation.R.string.no_internet_connection))
        }
        if (uiState.error != null) {
            ErrorMessage(uiState.error)
        }
        if (showBottomSheet) {
            AlertsHistoryBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
                historyError = uiState.historyError,
                noInternet = !uiState.isConnected && uiState.alertsHistoryList.isEmpty(),
                regionName = regionName,
                uiState = uiState,
                selectedRange = selectedRange,
                onRangeSelected = { selectedRange = it },
            )
        }

        Column(
            modifier =
                Modifier
                    .padding(top = 20.dp, start = 12.dp, end = 12.dp),
        ) {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 8.dp, end = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
            ) {
                MapToggleButtons(
                    modifier = Modifier.padding(end = 8.dp),
                    selectedMap = selectedMap,
                    onMapChange = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(
                                page = if (it == "Ukraine") 0 else 1,
                            )
                        }
                        selectedMap = it
                    },
                    oblast = regionName,
                )
                IconButton(
                    modifier = Modifier,
                    onClick = {
                        onSettingsButtonClick()
                    },
                ) {
                    Icon(
                        imageVector =
                            ImageVector
                                .vectorResource(R.drawable.ic_settings),
                        contentDescription = "Settings",
                        tint = Theme.color.neutral9,
                    )
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
            ) { page ->
                when (page) {
                    0 -> {
                        key(colorsMap) {
                            if (uiState.isLoading) {
                                Box(
                                    modifier =
                                        Modifier
                                            .fillMaxWidth()
                                            .aspectRatio(1.3f),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    CircularProgressIndicator(color = Theme.color.secondary100)
                                }
                            } else {
                                MapImage(
                                    imageVector =
                                        getUkraineMap(
                                            colorsMap = colorsMap,
                                            strokeColor = Theme.color.mapStroke,
                                            defaultColor = Theme.color.mapDefault,
                                        ),
                                )
                            }
                        }
                    }

                    1 -> {
                        if (uiState.isLoading) {
                            Box(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1.3f),
                                contentAlignment = Alignment.Center,
                            ) {
                                CircularProgressIndicator(
                                    color = Theme.color.secondary100,
                                )
                            }
                        } else {
                            MapImage(
                                imageVector = getRegionMap(regionName, mapArgs),
                            )
                        }
                    }
                }
            }

            AlertsInfo(
                modifier = Modifier.padding(start = 12.dp, bottom = 12.dp, top = 4.dp),
                isRegionShown = pagerState.currentPage == 1,
            )
        }
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(
                        Theme.color.neutral05,
                        RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp),
                    ).padding(top = 8.dp),
        ) {
            Row(
                modifier =
                    Modifier
                        .padding(start = 20.dp, end = 20.dp, bottom = 4.dp),
            ) {
                Text(
                    modifier =
                        Modifier
                            .padding(top = 8.dp)
                            .align(Alignment.Bottom),
                    text = stringResource(dev.stupak.localisation.R.string.active_alerts),
                    style = Theme.typography.heading5,
                    color = Theme.color.neutral9,
                )
                if (pagerState.currentPage == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                    Row(
                        modifier =
                            Modifier
                                .clip(RoundedCornerShape(360.dp))
                                .background(Theme.color.button)
                                .clickable {
                                    showBottomSheet = true
                                }.padding(vertical = 8.dp, horizontal = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = stringResource(dev.stupak.localisation.R.string.statistics),
                            style = Theme.typography.textRegularNormal,
                            color = Theme.color.white,
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_statistics),
                            contentDescription = null,
                            tint = Theme.color.white,
                        )
                    }
                }
            }

            AlertsList(
                alerts =
                    if (pagerState.currentPage == 0) {
                        uiState.alertsList
                    } else {
                        uiState.alertsList.filter { alert ->
                            when (regionName) {
                                "Київська область" ->
                                    alert.locationOblast == regionName ||
                                        alert.locationOblast == "м. Київ"

                                else -> alert.locationOblast == regionName
                            }
                        }
                    },
                modifier =
                    Modifier
                        .background(Theme.color.neutral05)
                        .padding(top = 4.dp, start = 12.dp, end = 12.dp)
                        .offset { IntOffset(0, offsetY.value.toInt()) },
                listState = listState,
            )
        }
    }
}
