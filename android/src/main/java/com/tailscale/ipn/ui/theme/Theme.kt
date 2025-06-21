// Copyright (c) Tailscale Inc & AUTHORS
// SPDX-License-Identifier: BSD-3-Clause

package com.tailscale.ipn.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun AppTheme(useDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
  val colors =
      if (useDarkTheme) {
        DarkColors
      } else {
        LightColors
      }

  val typography =
      Typography(
          // titleMedium is styled to be slightly larger than bodyMedium for emphasis
          titleMedium =
              MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp, lineHeight = 26.sp),
          // bodyMedium is styled to use same line height as titleMedium to ensure even vertical
          // margins in list items.
          bodyMedium = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp))

  // TODO: Migrate to Activity.enableEdgeToEdge
  @Suppress("deprecation") val systemUiController = rememberSystemUiController()

  DisposableEffect(systemUiController, useDarkTheme) {
    systemUiController.setStatusBarColor(color = colors.surfaceContainer)
    systemUiController.setNavigationBarColor(color = Color.Black)
    onDispose {}
  }

  MaterialTheme(colorScheme = colors, typography = typography, content = content)
}

private val LightColors = lightColorScheme(
    primary = Color(0xFFE10F73),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFE5F0),
    onPrimaryContainer = Color(0xFFE10F73),

    surface = Color(0xFFE9E9EA),  // 统一背景色
    background = Color(0xFFE9E9EA),
    surfaceContainer = Color(0xFFE9E9EA),
    surfaceContainerLow = Color(0xFFE9E9EA),
    surfaceContainerHigh = Color(0xFFE9E9EA),
    surfaceContainerHighest = Color(0xFFE9E9EA),
    surfaceContainerLowest = Color.White,  // 可保留一点极限浮层

    onSurface = Color(0xFF5C5E65),
    onSurfaceVariant = Color(0xFF7A7C82),

    error = Color(0xFFB22C30),
    onError = Color.White,
    errorContainer = Color(0xFFFEF6F3),
    onErrorContainer = Color(0xFF930921),

    surfaceDim = Color(0xFFE9E9EA),
    surfaceBright = Color.White,
    surfaceVariant = Color(0xFFF5F5F5),

    outline = Color(0xFFCCCCCC),
    outlineVariant = Color(0xFFE0E0E0),
    inverseSurface = Color(0xFF5C5E65),
    inverseOnSurface = Color.White,
    scrim = Color(0xAA000000),
)


private val DarkColors = darkColorScheme(
    primary = Color(0xFFE10F73),
    onPrimary = Color.White,
    primaryContainer = Color(0xFF5C5E65),
    onPrimaryContainer = Color(0xFFE10F73),

    surface = Color(0xFF18181A),  // 统一深背景
    background = Color(0xFF18181A),
    surfaceContainer = Color(0xFF18181A),
    surfaceContainerLow = Color(0xFF18181A),
    surfaceContainerHigh = Color(0xFF18181A),
    surfaceContainerHighest = Color(0xFF18181A),
    surfaceContainerLowest = Color(0xFF141415),

    onSurface = Color(0xFFE9E9EA),
    onSurfaceVariant = Color(0xFFAFAFAF),

    error = Color(0xFFEF5350),
    onError = Color.White,
    errorContainer = Color(0xFFfff6f4),
    onErrorContainer = Color(0xFF940821),

    surfaceDim = Color(0xFF18181A),
    surfaceBright = Color(0xFF232325),
    surfaceVariant = Color(0xFF333335),

    outline = Color(0xFF666666),
    outlineVariant = Color(0xFF2E2D2D),
    inverseSurface = Color(0xFFEDEBEA),
    inverseOnSurface = Color.Black,
    scrim = Color(0xAA000000),
)


val ColorScheme.warning: Color
  @Composable
  get() =
      if (isSystemInDarkTheme()) {
        Color(0xFFBB5504) // yellow-400
      } else {
        Color(0xFFD97917) // yellow-300
      }

val ColorScheme.onWarning: Color
  get() = Color(0xFFFFFFFF) // white

val ColorScheme.warningContainer: Color
  get() = Color(0xFFFFFAEE) // orange-0

val ColorScheme.onWarningContainer: Color
  get() = Color(0xFF7E1E22) // orange-600

val ColorScheme.success: Color
  get() = Color(0xFF0A825D) // green-400

val ColorScheme.onSuccess: Color
  get() = Color(0xFFFFFFFF) // white

val ColorScheme.successContainer: Color
  get() = Color(0xFFEFFEEC) // green-0

val ColorScheme.onSuccessContainer: Color
  get() = Color(0xFF0E4B3B) // green-600

val ColorScheme.on: Color
  get() = Color(0xFF1CA672) // green-300

val ColorScheme.off: Color
  @Composable
  get() =
      if (isSystemInDarkTheme()) {
        Color(0xFF444342) // gray-600
      } else {
        Color(0xFFD9D6D5) // gray-300
      }

val ColorScheme.link: Color
  get() = onPrimaryContainer

val ColorScheme.customError: Color
  @Composable
  get() =
      if (isSystemInDarkTheme()) {
        Color(0xFF940821) // red-600
      } else {
        Color(0xFFB22D30) // red-500
      }

val ColorScheme.customErrorContainer: Color
  @Composable
  get() =
      if (isSystemInDarkTheme()) {
        Color(0xFF760012) // red-700
      } else {
        Color(0xFF940821) // red-600
      }

/**
 * Main color scheme for list items, uses onPrimaryContainer color for leading and trailing icons.
 */
val ColorScheme.listItem: ListItemColors
  @Composable
  get() {
    val default = ListItemDefaults.colors()
    return ListItemColors(
        containerColor = default.containerColor,
        headlineColor = default.headlineColor,
        leadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
        overlineColor = default.overlineColor,
        supportingTextColor = default.supportingTextColor,
        trailingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
        disabledHeadlineColor = default.disabledHeadlineColor,
        disabledLeadingIconColor = default.disabledLeadingIconColor,
        disabledTrailingIconColor = default.disabledTrailingIconColor)
  }

/** Like listItem, but with the overline content using the onSurface color. */
val ColorScheme.titledListItem: ListItemColors
  @Composable
  get() {
    val default = listItem
    return ListItemColors(
        containerColor = default.containerColor,
        headlineColor = default.headlineColor,
        leadingIconColor = default.leadingIconColor,
        overlineColor = MaterialTheme.colorScheme.onSurface,
        supportingTextColor = default.supportingTextColor,
        trailingIconColor = default.trailingIconColor,
        disabledHeadlineColor = default.disabledHeadlineColor,
        disabledLeadingIconColor = default.disabledLeadingIconColor,
        disabledTrailingIconColor = default.disabledTrailingIconColor)
  }

/** Color scheme for disabled list items. */
val ColorScheme.disabledListItem: ListItemColors
  @Composable
  get() {
    val default = ListItemDefaults.colors()
    return ListItemColors(
        containerColor = default.containerColor,
        headlineColor = MaterialTheme.colorScheme.disabled,
        leadingIconColor = default.leadingIconColor,
        overlineColor = default.overlineColor,
        supportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        trailingIconColor = default.trailingIconColor,
        disabledHeadlineColor = default.disabledHeadlineColor,
        disabledLeadingIconColor = default.disabledLeadingIconColor,
        disabledTrailingIconColor = default.disabledTrailingIconColor)
  }

/** Color scheme for list items that should be styled as a surface container. */
val ColorScheme.surfaceContainerListItem: ListItemColors
  @Composable
  get() {
    val default = ListItemDefaults.colors()
    return ListItemColors(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        headlineColor = MaterialTheme.colorScheme.onSurface,
        leadingIconColor = MaterialTheme.colorScheme.onSurface,
        overlineColor = MaterialTheme.colorScheme.onSurfaceVariant,
        supportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        trailingIconColor = MaterialTheme.colorScheme.onSurface,
        disabledHeadlineColor = default.disabledHeadlineColor,
        disabledLeadingIconColor = default.disabledLeadingIconColor,
        disabledTrailingIconColor = default.disabledTrailingIconColor)
  }

/** Color scheme for list items that should be styled as a primary item. */
val ColorScheme.primaryListItem: ListItemColors
  @Composable
  get() {
    val default = ListItemDefaults.colors()
    return ListItemColors(
        containerColor = MaterialTheme.colorScheme.primary,
        headlineColor = MaterialTheme.colorScheme.onPrimary,
        leadingIconColor = MaterialTheme.colorScheme.onPrimary,
        overlineColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
        supportingTextColor = MaterialTheme.colorScheme.onPrimary,
        trailingIconColor = MaterialTheme.colorScheme.onPrimary,
        disabledHeadlineColor = default.disabledHeadlineColor,
        disabledLeadingIconColor = default.disabledLeadingIconColor,
        disabledTrailingIconColor = default.disabledTrailingIconColor)
  }

/** Color scheme for list items that should be styled as a warning item. */
val ColorScheme.warningListItem: ListItemColors
  @Composable
  get() {
    val default = ListItemDefaults.colors()
    return ListItemColors(
        containerColor = MaterialTheme.colorScheme.warning,
        headlineColor = MaterialTheme.colorScheme.onPrimary,
        leadingIconColor = MaterialTheme.colorScheme.onPrimary,
        overlineColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
        supportingTextColor = MaterialTheme.colorScheme.onPrimary,
        trailingIconColor = MaterialTheme.colorScheme.onPrimary,
        disabledHeadlineColor = default.disabledHeadlineColor,
        disabledLeadingIconColor = default.disabledLeadingIconColor,
        disabledTrailingIconColor = default.disabledTrailingIconColor)
  }

/** Color scheme for list items that should be styled as an error item. */
val ColorScheme.errorListItem: ListItemColors
  @Composable
  get() {
    val default = ListItemDefaults.colors()
    return ListItemColors(
        containerColor = MaterialTheme.colorScheme.customError,
        headlineColor = MaterialTheme.colorScheme.onPrimary,
        leadingIconColor = MaterialTheme.colorScheme.onPrimary,
        overlineColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
        supportingTextColor = MaterialTheme.colorScheme.onPrimary,
        trailingIconColor = MaterialTheme.colorScheme.onPrimary,
        disabledHeadlineColor = default.disabledHeadlineColor,
        disabledLeadingIconColor = default.disabledLeadingIconColor,
        disabledTrailingIconColor = default.disabledTrailingIconColor)
  }

/** Main color scheme for top app bar, styles it as a surface container. */
@OptIn(ExperimentalMaterial3Api::class)
val ColorScheme.topAppBar: TopAppBarColors
  @Composable
  get() =
      TopAppBarDefaults.topAppBarColors()
          .copy(
              containerColor = MaterialTheme.colorScheme.surfaceContainer,
              navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
              titleContentColor = MaterialTheme.colorScheme.onSurface,
          )

val ColorScheme.secondaryButton: ButtonColors
  @Composable
  get() {
    val defaults = ButtonDefaults.buttonColors()
    if (isSystemInDarkTheme()) {
      return ButtonColors(
          containerColor = Color(0xFF4B70CC), // blue-500
          contentColor = Color(0xFFFFFFFF), // white
          disabledContainerColor = defaults.disabledContainerColor,
          disabledContentColor = defaults.disabledContentColor)
    } else {
      return ButtonColors(
          containerColor = Color(0xFF5A82DC), // blue-400
          contentColor = Color(0xFFFFFFFF), // white
          disabledContainerColor = defaults.disabledContainerColor,
          disabledContentColor = defaults.disabledContentColor)
    }
  }

val ColorScheme.errorButton: ButtonColors
  @Composable
  get() {
    val defaults = ButtonDefaults.buttonColors()
    if (isSystemInDarkTheme()) {
      return ButtonColors(
          containerColor = Color(0xFFB22D30), // red-500
          contentColor = Color(0xFFFFFFFF), // white
          disabledContainerColor = defaults.disabledContainerColor,
          disabledContentColor = defaults.disabledContentColor)
    } else {
      return ButtonColors(
          containerColor = Color(0xFFD04841), // red-400
          contentColor = Color(0xFFFFFFFF), // white
          disabledContainerColor = defaults.disabledContainerColor,
          disabledContentColor = defaults.disabledContentColor)
    }
  }

val ColorScheme.warningButton: ButtonColors
  @Composable
  get() {
    val defaults = ButtonDefaults.buttonColors()
    if (isSystemInDarkTheme()) {
      return ButtonColors(
          containerColor = Color(0xFFD97917), // yellow-300
          contentColor = Color(0xFFFFFFFF), // white
          disabledContainerColor = defaults.disabledContainerColor,
          disabledContentColor = defaults.disabledContentColor)
    } else {
      return ButtonColors(
          containerColor = Color(0xFFE5993E), // yellow-200
          contentColor = Color(0xFFFFFFFF), // white
          disabledContainerColor = defaults.disabledContainerColor,
          disabledContentColor = defaults.disabledContentColor)
    }
  }

val ColorScheme.defaultTextColor: Color
  @Composable
  get() =
      if (isSystemInDarkTheme()) {
        Color.White
      } else {
        Color.Black
      }

val ColorScheme.logoBackground: Color
  @Composable
  get() =
      if (isSystemInDarkTheme()) {
        Color(0xFFFFFFFF) // white
      } else {
        Color(0xFF1F1E1E)
      }

val ColorScheme.standaloneLogoDotEnabled: Color
  @Composable
  get() =
      if (isSystemInDarkTheme()) {
        Color(0xFFFFFFFF)
      } else {
        Color(0xFF000000)
      }

val ColorScheme.standaloneLogoDotDisabled: Color
  @Composable
  get() =
      if (isSystemInDarkTheme()) {
        Color(0x66FFFFFF)
      } else {
        Color(0x661F1E1E)
      }

val ColorScheme.onBackgroundLogoDotEnabled: Color
  @Composable
  get() =
      if (isSystemInDarkTheme()) {
        Color(0xFF141414)
      } else {
        Color(0xFFFFFFFF)
      }

val ColorScheme.onBackgroundLogoDotDisabled: Color
  @Composable
  get() =
      if (isSystemInDarkTheme()) {
        Color(0x66141414)
      } else {
        Color(0x66FFFFFF)
      }

val ColorScheme.exitNodeToggleButton: ButtonColors
  @Composable
  get() {
    val defaults = ButtonDefaults.buttonColors()
    return if (isSystemInDarkTheme()) {
      ButtonColors(
          containerColor = Color(0xFF444342), // grey-600
          contentColor = Color(0xFFFFFFFF), // white
          disabledContainerColor = defaults.disabledContainerColor,
          disabledContentColor = defaults.disabledContentColor)
    } else {
      ButtonColors(
          containerColor = Color(0xFFEDEBEA), // grey-300
          contentColor = Color(0xFF000000), // black
          disabledContainerColor = defaults.disabledContainerColor,
          disabledContentColor = defaults.disabledContentColor)
    }
  }

val ColorScheme.disabled: Color
  get() = Color(0xFFAFACAB) // gray-400

@OptIn(ExperimentalMaterial3Api::class)
val ColorScheme.searchBarColors: TextFieldColors
  @Composable
  get() {
    return OutlinedTextFieldDefaults.colors(
        focusedLeadingIconColor = MaterialTheme.colorScheme.onSurface,
        unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurface,
        focusedTextColor = MaterialTheme.colorScheme.onSurface,
        unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
        disabledContainerColor = MaterialTheme.colorScheme.surfaceContainer,
        focusedBorderColor = Color.Transparent,
        unfocusedBorderColor = Color.Transparent)
  }

val TextStyle.short: TextStyle
  get() = copy(lineHeight = 20.sp)

val Typography.minTextSize: TextUnit
  get() = 10.sp
