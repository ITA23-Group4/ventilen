package com.example.ventilen_app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.R
import com.example.ventilen_app.ui.theme.CustomColorScheme


@Composable
fun TopAuthPageDesign(
    hasBackButton: Boolean,
    topText: String,
    bottomText: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(16.dp)
        ) {
            if (hasBackButton) {
                Icon(
                    Icons.Rounded.ArrowBack,
                    contentDescription = "Icon",
                    tint = CustomColorScheme.OffBlack,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(200.dp))

            Box {
                //there will be progressbar or something
            }

        }
        Image(
            painter = painterResource(id = R.drawable.vent_logo),
            contentDescription = "Welcome Image",
            modifier = Modifier.width(166.dp),
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.height(66.dp))
        Text(text = topText, style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(14.dp))
        Text(text = bottomText, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(28.dp))
    }
}