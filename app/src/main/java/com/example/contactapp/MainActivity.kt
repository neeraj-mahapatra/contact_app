package com.example.contactapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

    private val PERMISSIONS_REQUEST_READ_CONTACTS = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


// Check if the permission to read contacts is granted
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionNotGranted()
        } else {
            // Permission has already been granted
            // Access the contacts here
            // Handle the scenario here
        }



        // Handle the permission request result
        fun onRequestPermissionsResult(
            requestCode: Int, permissions: Array<String>,
            grantResults: IntArray
        ) {
            if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission has been granted
                    // Access the contacts here
                    // Handle the scenario here

                } else {
                    // Permission has been denied
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            this,
                            Manifest.permission.READ_CONTACTS
                        )
                    ) {

                        // User denied the permission, but did not select "Don't ask again"
                        // Show an explanation to the user
                        AlertDialog.Builder(this)
                            .setTitle("Permission Request")
                            .setMessage("This app needs to access your contacts to function properly. Please grant the permission in the app settings.")
                            .setPositiveButton("OK") { dialogInterface, i ->
                                // Open the app settings
                                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                intent.data = Uri.fromParts("package", packageName, null)
                                startActivity(intent)
                            }
                            .setNegativeButton("Cancel") { dialogInterface, i ->
                                // User cancelled the dialog
                                // Handle the scenario here
                            }
                            .create()
                            .show()

                    } else {
                        // User denied the permission and selected "Don't ask again"
                        // Show a message explaining how to enable the permission manually in the app settings
                        Toast.makeText(
                            this,
                            "This app needs to access your contacts to function properly. Please grant the permission in the app settings.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

    }

    private fun permissionNotGranted() {
        // Permission is not granted
        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.READ_CONTACTS
            )
        ) {

            // Show an explanation to the user
            AlertDialog.Builder(this)
                .setTitle("Permission Request")
                .setMessage("This app needs to access your contacts to function properly.")
                .setPositiveButton("OK") { dialogInterface, i ->
                    // Request the permission
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.READ_CONTACTS),
                        PERMISSIONS_REQUEST_READ_CONTACTS
                    )
                }
                .setNegativeButton("Cancel") { dialogInterface, i ->
                    // User cancelled the dialog
                    // Handle the scenario here
                }
                .create()
                .show()

        } else {
            // No explanation needed, request the permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CONTACTS),
                PERMISSIONS_REQUEST_READ_CONTACTS
            )
        }
    }

}