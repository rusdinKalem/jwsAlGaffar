package com.roesch.jwsalgaffar.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ShortCut(
    name: String,
    @DrawableRes image: Int
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ){

        Box(
            modifier =
            Modifier
                .border(
                    BorderStroke(2.dp, Color.Black),
                    RoundedCornerShape(corner = CornerSize(10.dp))
                )
                .shadow(elevation = 3.dp)

        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .clip(RectangleShape)
                    .size(80.dp)
                    .padding(10.dp)
            )
        }
        Text(
            text = name,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .padding(5.dp)
        )
    }
}