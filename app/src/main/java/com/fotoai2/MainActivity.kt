
package com.fotoai2

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FotoAIApp()
        }
    }
}

@Composable
fun FotoAIApp() {
    var selectedOption by remember { mutableStateOf<String?>(null) }
    var selectedImage by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        selectedImage = uri
    }

    val purple = Color(0xFF9B5CFF)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF160D20))
            .padding(22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(35.dp))

        Text(
            "FotoAI2",
            color = Color.White,
            fontSize = 34.sp
        )

        Text(
            "Tvoj AI foto i video studio",
            color = Color.LightGray,
            fontSize = 15.sp
        )

        Spacer(Modifier.height(35.dp))

        if (selectedOption == null) {
            val options = listOf(
                "✨ AI uređivanje fotografija",
                "💪 Transformacija tijela",
                "😊 Uređivanje lica",
                "🌄 Promjena pozadine",
                "🪄 Uklanjanje objekata",
                "🎨 Generiranje AI slika",
                "🎬 Pretvaranje slike u video"
            )

            options.forEach { option ->
                Button(
                    onClick = {
                        selectedOption = option
                        selectedImage = null
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .height(55.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF34234F)
                    )
                ) {
                    Text(option, color = Color.White)
                }
            }
        } else {
            Text(
                selectedOption!!,
                color = Color.White,
                fontSize = 23.sp
            )

            Spacer(Modifier.height(25.dp))

            Button(
                onClick = { launcher.launch("image/*") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = purple
                )
            ) {
                Text("📷 Odaberi fotografiju", color = Color.White)
            }

            selectedImage?.let { uri ->
                Spacer(Modifier.height(20.dp))

                AndroidView(
                    factory = { context ->
                        ImageView(context).apply {
                            scaleType = ImageView.ScaleType.FIT_CENTER
                        }
                    },
                    update = { it.setImageURI(uri) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
            }

            Spacer(Modifier.height(25.dp))

            Button(
                onClick = {
                    selectedOption = null
                    selectedImage = null
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = purple
                )
            ) {
                Text("← Natrag", color = Color.White)
            }
        }

        Spacer(Modifier.weight(1f))

        Text(
            "POWERED BY AI",
            color = purple,
            fontSize = 12.sp
        )
    }
}
