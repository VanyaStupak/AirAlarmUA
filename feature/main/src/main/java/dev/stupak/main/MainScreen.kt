package dev.stupak.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.stupak.main.maps.getDnipropetrovskRegionMap
import dev.stupak.main.maps.getDonetskRegionMap
import dev.stupak.main.maps.getKharkivRegionMap
import dev.stupak.main.maps.getKhersonRegionMap
import dev.stupak.main.maps.getKirovogradRegionMap
import dev.stupak.main.maps.getOdesaRegionMap
import dev.stupak.main.maps.getPoltavaRegionMap
import dev.stupak.main.maps.getUkraineMap
import dev.stupak.main.maps.getZaporizhzhiaRegionMap
import dev.stupak.main.maps.regionsUkraine
import dev.stupak.main.util.Const
import dev.stupak.main.viewModel.MainScreenState
import dev.stupak.main.viewModel.MainViewModel
import dev.stupak.ui.theme.AirAlarmTheme
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val uiState = viewModel.uiState.collectAsState()
    val pagerState = rememberPagerState(initialPage = 1) { 2 }
    val coroutineScope = rememberCoroutineScope()
    var selectedMap by remember { mutableStateOf("Ukraine") }
    val listState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AirAlarmTheme.colors.neutral05)
    ) {
        when (val state = uiState.value) {
            is MainScreenState.Loading -> {
                Text(text = Const.LOADING, modifier = Modifier.padding(16.dp))
            }

            is MainScreenState.Success -> {
                val colorsMap = state.data.alerts
                    .mapNotNull { alert ->
                        val oblastId =
                            regionsUkraine.entries.find { it.value == alert.locationOblast }?.key
                        oblastId?.let { it to alert }
                    }
                    .groupBy { it.first }
                    .mapValues { (_, group) ->
                        if (group.any { it.second.locationType == Const.OBLAST }) AirAlarmTheme.colors.secondary120
                        else AirAlarmTheme.colors.warning
                    }

                val filteredLocations = state.data.alerts
                    .filter { it.locationOblast == "Дніпропетровська область" }
                    .mapNotNull { it.locationRaion }.toSet()

                val oblastAlert = state.data.alerts.any {
                    it.locationTitle == "Дніпропетровська область"
                }



                Box(
                    modifier = Modifier
                        .fillMaxHeight(0.2f)
                        .wrapContentWidth()
                        .padding(bottom = 16.dp)
                        .align(Alignment.End),
                ) {
                    MapToggleButton(
                        modifier = Modifier.align(Alignment.BottomEnd),
                        selectedMap = selectedMap,
                        onMapChange = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(
                                    page = if (it == "Ukraine") 0 else 1,
                                    animationSpec = tween(
                                        durationMillis = 400,
                                        easing = LinearEasing
                                    )
                                )
                            }
                            selectedMap = it
                        }
                    )
                }

                LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
                    listState.scrollToItem(0)
                    if (!pagerState.isScrollInProgress) {
                        selectedMap = if (pagerState.currentPage == 0) "Ukraine" else "Oblast"
                    }
                }

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth()
                ) { page ->
                    when (page) {
                        0 -> {
                            Image(
                                imageVector = getUkraineMap(
                                    colorsMap = colorsMap,
                                    strokeColor = AirAlarmTheme.colors.neutral05,
                                    defaultColor = AirAlarmTheme.colors.neutral4
                                ),
                                contentDescription = "Map of Ukraine",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1.52f)
                                    .padding(horizontal = 4.dp),
                                contentScale = ContentScale.Crop
                            )
                        }

                        1 -> {
                            Image(
                                imageVector = getOdesaRegionMap(
                                    districtsSet = filteredLocations,
                                    textColor = AirAlarmTheme.colors.neutral7,
                                    strokeColor = AirAlarmTheme.colors.neutral05,
                                    defaultColor = AirAlarmTheme.colors.neutral3,
                                    oblastAlert = false
                                ),
                                contentDescription = "Oblast",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 4.dp),
                                contentScale = ContentScale.FillWidth
                            )
                        }
                    }
                }

                AlertsList(
                    alerts = if (pagerState.currentPage == 0) state.data.alerts
                    else state.data.alerts.filter { it.locationOblast == "Дніпропетровська область" },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp),
                    listState = listState
                )
            }

            is MainScreenState.Error -> {
                Text(text = "Помилка ${state.error}", modifier = Modifier.padding(16.dp))
            }
        }
    }
}

