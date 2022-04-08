package com.example.coreui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

interface Group<T> {
	val light: T

	val dark: T
}

class ColorTheme(
	val primary: Int,
	val primaryVariant: Int,
	val secondary: Int,
	val secondaryVariant: Int,
	val background: Int,
	val surface: Int,
	val error: Int,
	val onPrimary: Int,
	val onSecondary: Int,
	val onBackground: Int,
	val onSurface: Int,
	val onError: Int,
)

object MyColors : Group<ColorTheme> {
	override val light = ColorTheme(
		primary = 0x1351B4,
		primaryVariant = 0xADCDFF,
		secondary = 0x168821,
		secondaryVariant = 0xE3F5E1,
		background = 0xFFF,
		surface = 0xFFF,
		error = 0xE52207,
		onPrimary = 0xFFF,
		onSecondary = 0x000,
		onBackground = 0x000,
		onSurface = 0x000,
		onError = 0xFFF,
	)

	override val dark = light
}


@Composable
fun MyTheme(content: @Composable () -> Unit) {
	val isLightTheme = !isSystemInDarkTheme()
	val colors = if (isLightTheme)
		MyColors.light.getColors(isLightTheme)
	else
		MyColors.dark.getColors(isLightTheme)
	MaterialTheme(
		colors = colors,
		content = content
	)

}

fun ColorTheme.getColors(isLight: Boolean): Colors = Colors(
	primary = this.primary.color,
	primaryVariant = this.primaryVariant.color,
	secondary = this.secondary.color,
	secondaryVariant = this.secondaryVariant.color,
	background = this.background.color,
	surface = this.surface.color,
	error = this.error.color,
	onPrimary = this.onPrimary.color,
	onSecondary = this.onSecondary.color,
	onBackground = this.onBackground.color,
	onSurface = this.onSurface.color,
	onError = this.onError.color,
	isLight = isLight
)


private val Int.color: Color get() = Color(this)
val color = Color(color = 0x000)