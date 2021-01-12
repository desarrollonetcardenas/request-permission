package udemy.kotlin_course.permissionexample

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCameraPermission = findViewById<Button>(R.id.btnCameraPermission)

        btnCameraPermission.setOnClickListener {

            if ( ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "You're already have the permission for camera and gps", Toast.LENGTH_SHORT).show()

            } else {
                //Request Permission code
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.CAMERA,
                            Manifest.permission.ACCESS_FINE_LOCATION),
                    CAMERA_AND_FINE_LOCATION_PERMISSION_CODE)
            }

        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CAMERA_AND_FINE_LOCATION_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() ){

                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "Permission granted for the camera", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this, "Permission denied for camera", Toast.LENGTH_SHORT).show()


                if (grantResults[1] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "Permission granted for the gps", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this, "Permission denied for gps", Toast.LENGTH_SHORT).show()

            }
        }

    }

    companion object {

        private const val CAMERA_AND_FINE_LOCATION_PERMISSION_CODE = 12
    }
}