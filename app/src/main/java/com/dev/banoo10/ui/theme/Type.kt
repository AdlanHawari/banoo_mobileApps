package com.dev.banoo10.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.dev.banoo10.R


private val OpenSans = FontFamily(
    Font(R.font.opensans_light,
        weight= FontWeight.Light),
//    Font(R.font.opensans_lightitalic,
//        weight= FontWeight.W300),
    Font(R.font.opensans_regular,
        weight=FontWeight.Normal),
    Font(R.font.opensans_medium,
        weight= FontWeight.Medium),
//    Font(R.font.opensans_mediumitalic,
//        weight= FontWeight.W500),
    Font(R.font.opensans_semibold,
        weight= FontWeight.SemiBold),
    Font(R.font.opensans_bold,
        weight= FontWeight.Bold),
//    Font(R.font.opensans_bolditalic,
//        weight= FontWeight.W700),
    Font(R.font.opensans_extrabold,
        weight= FontWeight.ExtraBold),
//    Font(R.font.opensans_extrabolditalic,
//        weight= FontWeight.W800),
)

// Set of Material typography styles to start with
//val Typography = Typography(
val OpenSansTypography = Typography(
//    body1 = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp
//    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
    defaultFontFamily = OpenSans,
    h1 = TextStyle(
//        fontFamily = OpenSans,
        fontWeight = FontWeight.Bold,
        fontSize = 96.sp,
        letterSpacing = (-1.5).sp
    ),
    h2= TextStyle(
//        fontFamily = OpenSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 60.sp,
        letterSpacing = (-0.5).sp
    ),
    h3 = TextStyle(
//        fontFamily = OpenSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 48.sp,
        letterSpacing = 0.sp
    ),
    h4= TextStyle(
//        fontFamily = OpenSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 34.sp,
        letterSpacing = 0.25.sp
    ),
    h5 = TextStyle(
//        fontFamily = OpenSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        letterSpacing = 0.sp
    ),
    h6 = TextStyle(
//        fontFamily = OpenSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        letterSpacing = 0.15.sp
    ),
    subtitle1 = TextStyle(
//        fontFamily = OpenSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp
    ),
    subtitle2 = TextStyle(
//        fontFamily = OpenSans,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 0.1.sp
    ),
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.5.sp
    ),
    body2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.25.sp
    ),
    button = TextStyle(
//        fontFamily = OpenSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        letterSpacing = 0.sp,
        textAlign = TextAlign.Center
    ),
    caption= TextStyle(
//        fontFamily = OpenSans,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.4.sp
    ),
    overline = TextStyle(
//        fontFamily = OpenSans,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        letterSpacing = 1.5.sp
    )

)