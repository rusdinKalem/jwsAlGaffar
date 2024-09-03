package com.roesch.jwsalgaffar.screens

import android.bluetooth.BluetoothSocket
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.roesch.jwsalgaffar.R
import com.roesch.jwsalgaffar.components.DynamicSelectTextField
import com.roesch.jwsalgaffar.components.JudulInfo
import com.roesch.jwsalgaffar.components.TombolHome
import com.roesch.jwsalgaffar.components.TombolIcon
import com.roesch.jwsalgaffar.components.TombolKirim
import com.roesch.jwsalgaffar.components.listType
import java.io.OutputStream

@Composable
fun Screen01(navController: NavHostController, bluetoothSocket: BluetoothSocket) {
    val bluetoothOutputStream: OutputStream = bluetoothSocket.outputStream

    val options = listType()
    var nama by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    val datas = listOf(
        "NMT$type",
        "CMN$nama"
    )
    val maxChar = 50
    val jmlh = nama.count()

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {

        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ){
            JudulInfo(
                judul = "TYPE & NAMA TEMPAT SHOLAT",
                info = "Pilih type tempat sholat, dengan pilihan Masjid/Musholla/Surau/Langgar. " +
                        "Dan input nama tempat sholat dimaksud"
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
                )   {
                    DynamicSelectTextField(
                        selectedValue = type,
                        options = options,
                        label = "TYPE",
                        onValueChangedEvent ={type = it}
                    )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
                )   {

                        OutlinedTextField(
                            value = nama,
                            onValueChange = { nama = it },
                            label = { Text("NAMA") },
                            modifier = Modifier
                                .fillMaxWidth(0.93f)
                        )
                    }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end=20.dp),
                text = "$jmlh/$maxChar",
                fontSize = 16.sp,
                textAlign = TextAlign.End
            )
            Spacer(modifier = Modifier.height(40.dp))

            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier
                        .clickable(
                            onClick = {
                                for(data in datas) {
                                    val dataQ =
                                        buildString {
                                            append(data)
                                            append("\n")
                                        }
                                    bluetoothOutputStream.write(dataQ.toByteArray())
                                }
                            }
                        )
                ) {
                    TombolKirim()
                }
                Spacer(Modifier.height(20.dp))
                TombolHome(
                    "MENU UTAMA",
                    navController
                )
            }



        }
    }
}

