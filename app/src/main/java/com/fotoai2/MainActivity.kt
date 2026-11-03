
package com.fotoai2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FotoAI2App()
        }
    }
}

@Composable
fun FotoAI2App() {
    var selectedOption by remember { mutableStateOf<String?>(null) }

    val options = listOf(
        "✨ AI uređivanje fotografija",
        "💪 Transformacija tijela",
        "😊 Uređivanje lica",
        "🌄 Promjena pozadine",
        "🪄 Uklanjanje objekata",
        "🎨 Generiranje AI slika",
        "🎬 Pretvaranje slike u video"
    )

    val purple = Color(0xFF9B5CFF)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF100D1B), Color(0xFF251339))
                )
            )
            .padding(22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(35.dp))

        Text(
            "FotoAI2",
            color = Color.White,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            "Tvoj AI foto i video studio",
            color = Color.LightGray,
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(35.dp))

        if (selectedOption == null) {
            options.forEach { option ->
                Button(
                    onClick = { selectedOption = option },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .height(58.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF34234B)
                    )
                ) {
                    Text(
                        option,
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        } else {
            Text(
                selectedOption!!,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                "Ovdje ćemo dodati alat za ovu AI funkciju.",
                color = Color.LightGray,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = { selectedOption = null },
                colors = ButtonDefaults.buttonColors(
                    containerColor = purple
                )
            ) {
                Text("← Natrag", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            "POWERED BY AI",
            color = purple,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
