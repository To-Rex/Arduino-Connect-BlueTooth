package app.app.alfanar

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.graphics.PorterDuff
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var imageView: ImageView? = null
    var bluetoothDevice: BluetoothDevice? = null
    var click = true

    @SuppressLint("MissingInflatedId", "MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)

        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        println(bluetoothAdapter.name)

        bluetoothDevice = bluetoothAdapter.getRemoteDevice("00:21:04:08:29:99")
        val btsoket = bluetoothDevice!!.createRfcommSocketToServiceRecord(bluetoothDevice!!.uuids[0].uuid)
        btsoket.connect()
        imageView?.setOnClickListener{
            click = if (click){
                btsoket.outputStream.write("0".toByteArray())
                false
            }else{
                btsoket.outputStream.write("1".toByteArray())
                true
            }

            //btsoket.close()
        }




    }

}