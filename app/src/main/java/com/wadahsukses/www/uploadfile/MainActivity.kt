package com.wadahsukses.www.uploadfile

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import com.ipaulpro.afilechooser.utils.FileUtils
import com.wadahsukses.www.uploadfile.Remote.IUploadAPI
import com.wadahsukses.www.uploadfile.Remote.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    val BASE_URL = "http://192.168.99.210"

    val apiUpload:IUploadAPI
    get()  = RetrofitClient.getClient(BASE_URL).create(IUploadAPI::class.java)

    private val PERMISSION_REQUEST: Int = 1000
    lateinit var mService:IUploadAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //request runtime permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), PERMISSION_REQUEST)

        //service
        mService = apiUpload

        image_view.setOnClickListener{
            chooseImage()
        }

    }

    private fun chooseImage() {
        val getContentIntent = FileUtils.createGetContentIntent()
        val intent = Intent.createChooser(getContentIntent, "SElect file")
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK)
        {
            if (requestCode == PICK_IMAGE_REQUEST)
            {
                if (data != null)
                {
                    selectedFileUri = data.data
                    if (selectedFileUri != null && )
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode)
        {
            PERMISSION_REQUEST -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "GRANTED", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this, "DENIED", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
