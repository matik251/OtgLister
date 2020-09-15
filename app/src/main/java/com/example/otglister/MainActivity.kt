package com.example.otglister

import android.content.Context
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast

//  Mateusz Kula BDIS SSI AEI 2020

class MainActivity : AppCompatActivity() {
    private lateinit var listView : ListView
    private lateinit var manager : UsbManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById<ListView>(R.id.devices_list_view)
        manager = getSystemService(Context.USB_SERVICE) as UsbManager
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener()
        {
            checkDevices()
        }
    }

    private fun checkDevices(){
        var deviceList: HashMap<String, UsbDevice> = manager.deviceList
        var deviceCount = deviceList.count()
        var listItems = arrayOfNulls<String>(deviceCount)
        var i = 0
        deviceList.values.forEach { device ->
            listItems[i] = "" + device.deviceId + " : " + device.deviceName
            i++
        }
        val toast = Toast.makeText(applicationContext, "Found $deviceCount devices", Toast.LENGTH_LONG)
        toast.show()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        listView.adapter = adapter
    }
}