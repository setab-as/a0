package com.example.decisionmaking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.decisionmaking.ui.theme.DecisionMakingTheme
import kotlin.random.Random
import androidx.compose.ui.graphics.Color


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DecisionMakingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DecisionScreen()
                }
            }
        }
    }
}

@Composable
fun DecisionScreen(modifier: Modifier = Modifier) {
    var answer by remember { mutableStateOf("Want to go?") }
    var isYes by remember { mutableStateOf(false) }
    var clickCount by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // my ccid and student number (MAY REFORMAT LATER)
        Text(
            text = "Student ID: 1854082\nCCID: avnil",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(56.dp)
        )
        // click counter
        Text(
            text = "Click count: $clickCount",
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )
        // result on screen
        Text(
            text = answer,
            fontSize = 48.sp,
            color = if (clickCount > 0) {
                if (isYes) Color.Green else Color.Red
            } else {
                Color.Unspecified
            }, // green if "yes", red if "no" but only change colours after clicking it atleast once
            modifier = Modifier.padding(16.dp)
        )
        // below is the "OKAY!", "MEH...", and "NO." buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            // "OKAY!" button 50% chance of "Yes"
            Button(
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp),
                onClick = {
                    // nextBoolean is a straight 50/50
                    isYes = Random.nextBoolean()
                    answer = if (isYes) {
                        "Yes"
                    } else {
                        "No"
                    }

                    clickCount++ // increment up the click counter
                }
            ) {
                Text("OKAY!", fontSize = 24.sp)
            }
            // "MEH..." button 25% chance of "Yes"
            Button(
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp),
                onClick = {
                    isYes = Random.nextFloat() < 0.25f // enforce 25% chance of being "yes"
                    answer = if (isYes) {
                        "Yes"
                    } else {
                        "No"
                    }

                    clickCount++ // increment same as before
                }
            ) {
                Text("MEH...", fontSize = 24.sp)
            }
            // "NO." button 10% chance of "Yes"
            Button(
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp),
                onClick = {
                    isYes = Random.nextFloat() < 0.10f // enforce 10% chance of being "yes"
                    answer = if (isYes) {
                        "Yes"
                    } else {
                        "No"
                    }

                    clickCount++ // increment same as before
                }
            ) {
                Text("NO.", fontSize = 24.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DecisionScreenPreview() {
    DecisionMakingTheme {
        DecisionScreen()
    }
}