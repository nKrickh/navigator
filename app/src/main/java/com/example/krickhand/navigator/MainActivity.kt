package com.example.krickhand.navigator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.declaration.ui.theme.NaviGatorTheme
import com.example.krickhand.navigator.viewmodel.DayViewModel

class MainActivity : ComponentActivity() {

    private val dayViewModel: DayViewModel by viewModels {DayViewModel.Factory}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NaviGatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DayScreen() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .border(width = 1.dp, Color.Blue, shape = RoundedCornerShape(1.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = stringResource(R.string.app_name))

        Text(text = stringResource(R.string.app_name))

        Row(modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                Color.Blue,
                shape = RoundedCornerShape(1.dp)
            )){
            IconButton(
                onClick = { /* ... */ },
                modifier = Modifier.border(
                    width = 2.dp,
                    Color.Black,
                    shape = RoundedCornerShape(2.dp)
                ),
                enabled = true,
                colors = IconButtonDefaults.outlinedIconButtonColors()
            ) {
                Icon(Icons.Outlined.PlayArrow, contentDescription = null)
            }
        }
    }
}