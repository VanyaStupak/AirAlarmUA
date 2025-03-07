package com.example.widget

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.ColorFilter
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.example.widget.components.DefaultAlertsView
import com.example.widget.components.MultipleAlertsView
import com.example.widget.components.NoAlertsView
import com.example.widget.components.SingleAlertView
import com.example.widget.model.AlertsUiModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.stupak.ui.R
import dev.stupak.ui.theme.AirAlarmUATheme
import dev.stupak.ui.theme.LocalAppTheme

class MyGlanceWidget : GlanceAppWidget() {

    private val gson = Gson()
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            AirAlarmUATheme(darkTheme = false) {
                val colors = LocalAppTheme.current.colors
                val sharedPreferences: SharedPreferences =
                    context.getSharedPreferences("alerts_prefs", Context.MODE_PRIVATE)
                val alertsJson = sharedPreferences.getString("alerts_list", "[]")
                val regionName = sharedPreferences.getString("region_name", "")
                val isConnected = sharedPreferences.getBoolean("is_connected", true)

                val alertsList: List<AlertsUiModel> =
                    gson.fromJson(alertsJson, object : TypeToken<List<AlertsUiModel>>() {}.type)
                val colorsMap = remember(alertsList) {
                    alertsList
                        .map { alert ->
                            val oblastId =
                                dev.stupak.ui.maps.regionsUkraine.indexOf(alert.locationOblast)
                            oblastId to alert
                        }
                        .groupBy { it.first }
                        .mapValues { (_, group) ->
                            if (group.any { it.second.locationType == "oblast" }) colors.mapAlert
                            else colors.warning
                        }
                }
                val defaultColor = Color.White
                val imagesWithTint = listOf(
                    Pair(R.drawable.region_kyiv, colorsMap[9] ?: defaultColor),
                    Pair(R.drawable.city_kyiv, colorsMap[26] ?: defaultColor),
                    Pair(R.drawable.region_dnipropetrovsk, colorsMap[4] ?: defaultColor),
                    Pair(R.drawable.region_kirovograd, colorsMap[10] ?: defaultColor),
                    Pair(R.drawable.region_kharkiv, colorsMap[17] ?: defaultColor),
                    Pair(R.drawable.region_kherson, colorsMap[18] ?: defaultColor),
                    Pair(R.drawable.region_khmelnytsk, colorsMap[0] ?: defaultColor),
                    Pair(R.drawable.region_cherkasy, colorsMap[19] ?: defaultColor),
                    Pair(
                        R.drawable.region_crimea,
                        if (colorsMap.containsKey(24)) Color(0xFFEB5B52) else defaultColor
                    ),
                    Pair(R.drawable.region_chernigiv, colorsMap[20] ?: defaultColor),
                    Pair(R.drawable.region_chernivtsi, colorsMap[21] ?: defaultColor),
                    Pair(R.drawable.region_donetsk, colorsMap[23] ?: defaultColor),
                    Pair(R.drawable.region_volyn, colorsMap[3] ?: defaultColor),
                    Pair(R.drawable.region_vinnytsia, colorsMap[1] ?: defaultColor),
                    Pair(R.drawable.region_ivano_frankivsk, colorsMap[8] ?: defaultColor),
                    Pair(
                        R.drawable.region_luhansk,
                        if (colorsMap.containsKey(11)) Color(0xFFEB5B52) else defaultColor
                    ),
                    Pair(R.drawable.region_lviv, colorsMap[22] ?: defaultColor),
                    Pair(R.drawable.region_odesa, colorsMap[13] ?: defaultColor),
                    Pair(R.drawable.region_poltava, colorsMap[14] ?: defaultColor),
                    Pair(R.drawable.region_rivne, colorsMap[2] ?: defaultColor),
                    Pair(R.drawable.region_mykolaiv, colorsMap[12] ?: defaultColor),
                    Pair(R.drawable.region_sumy, colorsMap[15] ?: defaultColor),
                    Pair(R.drawable.region_ternopil, colorsMap[16] ?: defaultColor),
                    Pair(R.drawable.region_zhitomir, colorsMap[5] ?: defaultColor),
                    Pair(R.drawable.region_zakarpattia, colorsMap[6] ?: defaultColor),
                    Pair(R.drawable.region_zaporizhzhia, colorsMap[7] ?: defaultColor)
                )

                val filteredList = alertsList.filter { alert ->
                    when (regionName) {
                        "Київська область" -> alert.locationOblast == regionName
                                || alert.locationOblast == "м. Київ"

                        else -> alert.locationOblast == regionName
                    }
                }

                val oblastAlert = alertsList.any {
                    it.locationTitle == regionName
                }

                Column(
                    modifier = GlanceModifier.fillMaxSize()
                        .background(colors.neutral85),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (!isConnected) {
                        Box(
                            modifier = GlanceModifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = context.getString(R.string.no_internet_connection),
                                style = TextStyle(
                                    color = ColorProvider(Color.White),
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp
                                )
                            )
                        }

                    } else {
                        when {
                            oblastAlert && filteredList.size == 1 -> SingleAlertView(
                                regionName,
                                alertsList
                            )

                            !oblastAlert && filteredList.isNotEmpty() -> MultipleAlertsView(
                                regionName,
                                filteredList
                            )

                            filteredList.isEmpty() -> NoAlertsView(regionName)
                            else -> DefaultAlertsView(regionName, filteredList)
                        }
                        Box(modifier = GlanceModifier.fillMaxWidth()) {
                            imagesWithTint.chunked(10).forEach { chunk ->
                                Box(modifier = GlanceModifier.fillMaxWidth()) {
                                    chunk.forEach { (image, tint) ->
                                        Image(
                                            provider = ImageProvider(image),
                                            contentDescription = null,
                                            colorFilter = ColorFilter.tint(ColorProvider(tint))
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

class MyGlanceWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = MyGlanceWidget()
}
