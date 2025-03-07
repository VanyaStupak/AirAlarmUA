package dev.stupak.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import dev.stupak.ui.R
import dev.stupak.ui.maps.getUkraineMap
import dev.stupak.ui.maps.regionsUkraine
import dev.stupak.ui.theme.AirAlarmUATheme
import dev.stupak.ui.theme.LocalAppTheme
import kotlinx.coroutines.delay

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WelcomeScreen(
    onBackClick: () -> Unit,
    onNavigateToMain: (String) -> Unit,
    onAction: (WelcomeIntent) -> Unit,
    region: String,
    isFirstRun: Boolean
) {

    val colors = LocalAppTheme.current.colors
    val typography = LocalAppTheme.current.typography
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()
    var expanded by remember { mutableStateOf(false) }

    var selectedText by remember { mutableStateOf(region) }

    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    var colorsMap by remember { mutableStateOf<Map<Int, Color>>(emptyMap()) }

    var filteredRegions by remember { mutableStateOf(regionsUkraine.dropLast(2)) }

    val isButtonEnabled = remember(selectedText) {
        filteredRegions.any { it.equals(selectedText.trim(), ignoreCase = false) }
    }

    val oblastColor = colors.mapAlert

    val isKeyboardOpen = WindowInsets.isImeVisible
    LaunchedEffect(key1 = isKeyboardOpen) {
        delay(200)
        scrollState.animateScrollTo(scrollState.maxValue)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(colors.neutral2)
            .statusBarsPadding()
            .imePadding()
    ) {
        if (!isFirstRun) {
            Box(
                modifier = Modifier
                    .padding(top = 12.dp, start = 12.dp)
                    .clip(RoundedCornerShape(360.dp))
                    .background(colors.neutral4)
                    .clickable {
                        onBackClick()
                    }
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_back),
                    contentDescription = "Icon",
                    tint = colors.neutral9
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 42.dp, start = 24.dp, end = 24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.select_region),
                    color = colors.neutral9,
                    style = typography.heading4
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_pin_area),
                    contentDescription = "Arrow Icon",
                    tint = colors.neutral9
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.enter_region_name),
                color = colors.neutral8,
                style = typography.textRegularMedium
            )


            OutlinedTextField(
                value = selectedText,
                textStyle = typography.textMediumMedium,
                onValueChange = {
                    val capitalizedText = it.replaceFirstChar { char ->
                        if (char.isLowerCase()) char.titlecase() else char.toString()
                    }
                    selectedText = capitalizedText
                    colorsMap = List(regionsUkraine.size) { index ->
                        index to oblastColor
                    }.toMap()

                    filteredRegions = regionsUkraine.dropLast(2).filter { region ->
                        region.contains(it.trim(), ignoreCase = true)
                    }

                    val exactMatch = regionsUkraine.find { region ->
                        region.equals(it.trim(), ignoreCase = true)
                    }

                    colorsMap = if (exactMatch != null) {
                        mapOf(regionsUkraine.indexOf(exactMatch) to oblastColor)
                    } else if (it.isNotEmpty()) {
                        filteredRegions.mapIndexed { _, region ->
                            regionsUkraine.indexOf(region) to oblastColor
                        }.toMap()
                    } else {
                        emptyMap()
                    }

                    if ("Київська область" in filteredRegions && it.isNotEmpty()) {
                        colorsMap = colorsMap + (26 to oblastColor)
                    }


                    expanded = it.isNotEmpty()
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .onGloballyPositioned { coordinates ->
                        textFieldSize = coordinates.size.toSize()
                    },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                placeholder = {
                    Text(
                        text = stringResource(R.string.kyiv_region),
                        color = colors.neutral6,
                        style = typography.textMediumMedium
                    )
                },
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            expanded = !expanded
                        },
                        imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_down),
                        contentDescription = null,
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = colors.infoFocus,
                    unfocusedLabelColor = colors.neutral9,
                    unfocusedTextColor = colors.neutral9,
                    focusedTextColor = colors.neutral9,
                    unfocusedContainerColor = colors.neutral05,
                    unfocusedTrailingIconColor = colors.neutral6,
                    focusedTrailingIconColor = colors.neutral9
                ),
                shape = RoundedCornerShape(16.dp)
            )

            if (expanded) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                        .heightIn(max = 300.dp)
                        .background(
                            colors.neutral05, RoundedCornerShape(16.dp)
                        )
                        .align(Alignment.CenterHorizontally)
                ) {
                    items(filteredRegions) { label ->
                        Text(
                            text = label,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .clickable {
                                    focusManager.clearFocus()
                                    selectedText = label
                                    colorsMap = mapOf(regionsUkraine.indexOf(label) to oblastColor)
                                    if (label == "Київська область") {
                                        colorsMap = colorsMap + (26 to oblastColor)
                                    }
                                    expanded = false
                                }
                                .padding(16.dp),
                            color = colors.neutral8,
                            style = typography.textMediumMedium
                        )
                    }
                }
            }

            Image(
                imageVector = getUkraineMap(
                    colorsMap = colorsMap,
                    strokeColor = colors.neutral05,
                    defaultColor = colors.mapDefault
                ),
                contentDescription = "Map",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                contentScale = ContentScale.Fit,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .clip(RoundedCornerShape(360.dp))
                    .background(
                        if (isButtonEnabled) colors.secondary100
                        else colors.neutral4
                    )
                    .clickable(enabled = isButtonEnabled) {
                        onAction.invoke(WelcomeIntent.SetRegion(selectedText))
                        onNavigateToMain(region)
                    }
                    .padding(vertical = 16.dp, horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = if (isFirstRun) stringResource(R.string.start) else stringResource(R.string.done) ,
                    style = typography.textMediumSemiBold,
                    color = if (isButtonEnabled) colors.neutral9
                    else colors.neutral7
                )

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_next),
                    contentDescription = null,
                    tint = if (isButtonEnabled) colors.neutral9
                    else colors.neutral7,
                )
            }
        }
    }
}


@Preview(showSystemUi = true, device = "id:pixel_9")
@Composable
fun WelcomeScreenPreview() {
    AirAlarmUATheme {
        WelcomeScreen(
            onNavigateToMain = { },
            onAction = {},
            region = "",
            isFirstRun = false,
            onBackClick = {}
        )
    }
}