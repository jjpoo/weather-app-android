package com.polina.android.weather.app.utils.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.polina.android.weather.app.presentation.theme.AndroidweatherappTheme

@Composable
fun ErrorScreen(
    errorMessage: String,
    modifier: Modifier = Modifier,
    onRetry: () -> Unit,
    onBackToMain: () -> Unit
) {
    Scaffold(
        topBar = {
            IconButton(
                onClick = onBackToMain,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Error",
                    tint = Color.Black,
                    modifier = Modifier.size(100.dp)
                )
            }
        }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "Error",
                tint = MaterialTheme.colorScheme.error,
                modifier = Modifier.size(84.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Oops! Something went wrong.",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = onRetry, modifier = Modifier
            ) {
                Text("Try Again")
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Preview()
@Composable
fun ErrorScreenPreview() {
    AndroidweatherappTheme {
        ErrorScreen(
            errorMessage = "Unable to connect to the weather service. Please check your internet connection.",
            onRetry = {},
            onBackToMain = {}
        )
    }
}