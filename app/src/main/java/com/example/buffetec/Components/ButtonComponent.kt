package com.example.buffetec.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buffetec.ui.theme.lexendFontFamily

@Composable
fun ButtonComponent(
    label: String,
    onClick: (String) -> Unit
) {
    Button(
        colors = ButtonColors( Color(0xFF0c00a3), Color.White, Color(0xFF0c00a3), Color(0xFF0c00a3)),
        onClick = { onClick(label) },
        modifier = Modifier.fillMaxWidth()
            .height(60.dp)
            .padding(2.dp, 2.dp)
            .background(Color(0xFF0c00a3), shape = RoundedCornerShape(100.dp )),


        ) {
        Text(
            text = label,
            fontFamily = lexendFontFamily, fontWeight = FontWeight.ExtraBold)
    }
}