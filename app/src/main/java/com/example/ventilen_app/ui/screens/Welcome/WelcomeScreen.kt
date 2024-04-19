package com.example.ventilen_app.ui.screens.Welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.R
import com.example.ventilen_app.ui.composables.CustomOutlinedButton
import com.example.ventilen_app.ui.composables.CustomFilledButton
import com.example.ventilen_app.ui.theme.CustomColorScheme

@Composable
fun WelcomeScreen(
    onClick: () -> Unit
) {

    Column(
        Modifier.background(CustomColorScheme.Mocha),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(110.dp))
        Image(
            painter = painterResource(id = R.drawable.vent_logo), // Replace with your image resource ID
            contentDescription = "Welcome Image",
            modifier = Modifier
                .width(166.dp),
            contentScale = ContentScale.FillWidth,
        )
        Spacer(modifier = Modifier.height(66.dp))
        Text(text = "Velkommen",
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(text = "log in eller tilmeld dig",
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(28.dp))
        CustomFilledButton(
            text = "Login",
            onClick = onClick,
            color = CustomColorScheme.Orange,
            padding = PaddingValues(horizontal = 25.dp, vertical = 0.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        CustomOutlinedButton(text = "Tilmeld", onClick = onClick)
    }
}