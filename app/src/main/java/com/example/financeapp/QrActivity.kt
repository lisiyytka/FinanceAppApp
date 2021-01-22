package com.example.financeapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import me.dm7.barcodescanner.zxing.ZXingScannerView

class QrActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private val TAG = "QrActivity"
    internal var scanner: ZXingScannerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr)
        Log.d(TAG, "onCreate")

        scanner = ZXingScannerView(this)
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(object: PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                        setContentView(scanner)
                        scanner!!.setResultHandler(this@QrActivity)
                        scanner!!.startCamera()
                    }

                    override fun onPermissionRationaleShouldBeShown(
                            permission: PermissionRequest?,
                            token: PermissionToken?
                    ) {

                    }

                    @SuppressLint("ShowToast")
                    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                        Toast.makeText(this@QrActivity,
                                "You should enable this permission", Toast.LENGTH_SHORT)
                    }
                }).check()
    }

    override fun handleResult(rawResult: com.google.zxing.Result?) {
        if (rawResult != null) {
            Log.e(Constants.HANDLER, rawResult.text)
        } // Prints scan results
        if (rawResult != null) {
            Log.e(Constants.HANDLER, rawResult.barcodeFormat.toString())
        } // Prints the scan format (qrcode)

        try {
            scanner!!.stopCamera()

            if (rawResult != null) {
                val intent = Intent(this, CategoryActivity::class.java )
                intent.putExtra("code", rawResult.text)
                startActivity(intent)
            }
            finish()

            println(Constants.STOP_CAMERA)
            // Stop camera on pause
        } catch (e: Exception) {
            Log.e(Constants.ERROR, e.message!!)
        }
    }
    override fun onBackPressed() { this.cleanResult() }

    private fun cleanResult() {
        try {
            scanner!!.stopCamera() // Stop camera on pause
        } catch (e: Exception) {
            Log.e(Constants.ERROR, e.message!!)
        }
    }
}