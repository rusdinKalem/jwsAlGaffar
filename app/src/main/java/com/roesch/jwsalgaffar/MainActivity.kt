@file:Suppress("DEPRECATION")

package com.roesch.jwsalgaffar

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roesch.jwsalgaffar.utils.BluetoothHandler
import com.roesch.jwsalgaffar.utils.DeviceConnectionReceiver
import com.roesch.jwsalgaffar.utils.PermissionManager
import java.io.IOException

@Suppress("UNUSED_EXPRESSION")
class MainActivity : ComponentActivity() {

    private lateinit var bluetoothAdapter: BluetoothAdapter
    private val bondedDevices = mutableListOf<BluetoothDevice>()
    private lateinit var bluetoothHandler: BluetoothHandler
    private lateinit var bluetoothSocket: BluetoothSocket
    private lateinit var device: BluetoothDevice
    private lateinit var progressDialog: ProgressDialog

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        PermissionManager(this).requestPermissions()
        bluetoothAdapter = getSystemService(BluetoothManager::class.java).adapter
        progressDialog = ProgressDialog(this)

        val filter = IntentFilter()
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED)
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED)
        registerReceiver(DeviceConnectionReceiver(), filter)

        if (!bluetoothAdapter.isEnabled) {
            val requestBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(requestBtIntent, 0)
            finish()
            startActivity(intent)
        }

        // Add bonded bluetooth devices to list
        for (device in bluetoothAdapter.bondedDevices) {
            bondedDevices.add(device)
        }
        setContent {
                    Column(
                        modifier = Modifier
                            .padding()
                    ) {  }
                    MainUI(bondedDevices = bondedDevices)
                    }
    }

    @SuppressLint("MissingPermission")
    @Composable
    fun MainUI(
        bondedDevices:MutableList<BluetoothDevice>
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,

        ) {
//            Image(
//                painter = painterResource(id = R.drawable.bg01),
//                contentDescription = null,
//                modifier = Modifier
//                    .fillMaxSize(),
//                contentScale = ContentScale.FillBounds
//            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight(0.9f)
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {

                Spacer(modifier = Modifier.height(10.dp))
                for(device in bondedDevices) {
                    Spacer(modifier = Modifier.height(10.dp))
                    PairedDevices(name = device.name, address = device.address)
                }

            }
        }
    }

    @Composable
    private fun PairedDevices(name: String?, address: String?) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .background(color = Color(0x0C1b1b1b))
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xff1b1b1b)
                )
                .padding(horizontal = 5.dp)
                .height(70.dp)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = name.toString(), fontSize = 18.sp, modifier = Modifier.padding(horizontal = 15.dp))
            Button(
                modifier = Modifier
                    .padding(5.dp)
                    .height(40.dp)
                    .width(130.dp),
                onClick = {
                    device = bluetoothAdapter.getRemoteDevice(address)
                    showDialog(device)
                    Thread{connectToDevice(address.toString())}.start()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff000000))
            ) {
                Text(text = "CONNECT")
            }
        }
    }


    private var count = 2


    @SuppressLint("MissingPermission")
    private fun connectToDevice(deviceAddress: String) {
        device = bluetoothAdapter.getRemoteDevice(deviceAddress)
        try {
            bluetoothHandler = BluetoothHandler(bluetoothAdapter, deviceAddress)
            bluetoothHandler.start()
            try {
                bluetoothSocket = bluetoothHandler.createSocket(device)
                if (bluetoothSocket.isConnected) {
                    bluetoothSocket.close()
                }
                while(!bluetoothSocket.isConnected) {
                    bluetoothSocket.connect()
                }
                if(bluetoothSocket.isConnected){
                    progressDialog.dismiss()
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("Address", deviceAddress)
                    startActivity(intent)
                    bluetoothSocket.close()
                    count++
                }

            } catch (e: Exception) {
                bluetoothSocket.close()
                progressDialog.dismiss()
                runOnUiThread { Toast.makeText(this, "$e", Toast.LENGTH_SHORT).show() }
            }

        } catch (e: IOException) {
            bluetoothSocket.close()
            progressDialog.dismiss()
            runOnUiThread { Toast.makeText(this, "$e", Toast.LENGTH_SHORT).show() }
        }

    }


    @SuppressLint("MissingPermission")
    private fun showDialog(bluetoothDevice: BluetoothDevice) {

        progressDialog.setTitle("Connecting...")
        progressDialog.setMessage("Connecting to ${bluetoothDevice.name}")
        progressDialog.setCancelable(true)
        progressDialog.setOnCancelListener {
            bluetoothSocket.close()
            Toast.makeText(this, "Connection Cancelled!", Toast.LENGTH_SHORT).show()
        }
        progressDialog.show()
    }

}
