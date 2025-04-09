package com.polina.android.weather.app.presentation.main.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.polina.android.weather.app.R
import com.polina.android.weather.app.utils.theme.DeepBlue

@Composable
fun MainBottomBar(
    onDetailsClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            shape = RoundedCornerShape(10.dp),
            onClick = onDetailsClick,
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = DeepBlue
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text(
                text = stringResource(R.string.details),
                fontSize = 35.sp
            )
        }
    }
}

@Preview
@Composable
fun BottomPreview() {
    MainBottomBar({})
}