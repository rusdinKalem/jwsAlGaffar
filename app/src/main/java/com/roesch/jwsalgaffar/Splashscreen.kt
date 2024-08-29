package com.roesch.jwsalgaffar

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("CustomSplashScreen")
class Splashscreen : ComponentActivity() {
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SCALED,
            WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
        )
        Handler(Looper.getMainLooper()).postDelayed(
            {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            },
            3000
        )
//        }



        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,

                ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center)
                {
                    Image(
                        painter = painterResource(id = R.drawable.splash),
                        contentDescription = null,
                        modifier = Modifier
                            .size(250.dp),
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(text = "JWS alGAFFAR", fontSize = 36.sp, color = Color.Black)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Setting JWS Via Bluetooth ", fontSize = 18.sp, color = Color.Black)
                }
            }
        }
    }
}