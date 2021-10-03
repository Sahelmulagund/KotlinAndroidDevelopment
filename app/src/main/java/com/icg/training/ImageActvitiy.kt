package com.icg.training

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_image_actvitiy.*
import java.io.File

class ImageActvitiy : AppCompatActivity() {


    private var latestTmpUri: Uri? = null
    private val takeImageResult = registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
        if (isSuccess) {

            latestTmpUri?.let { uri ->
                ivImage.setImageURI(uri)
            }
        }
    }

    private val selectImageFromGalleryResult = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { ivImage.setImageURI(uri) }
        latestTmpUri = uri
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_actvitiy)

        btnImg.setOnClickListener {


            val dialog = BottomSheetDialog(this)
            val itemView = View.inflate(this, R.layout.layout_show_image_option, null)
            val cameraImage = itemView.findViewById(R.id.camera) as ImageView

            val galleryImage = itemView.findViewById(R.id.gallery) as ImageView

            cameraImage.setOnClickListener {
                takeImage()
                dialog.dismiss()
            }
            galleryImage.setOnClickListener {
                selectImageFromGallery()
                dialog.dismiss()

            }
            dialog.setContentView(itemView)

            dialog.window?.apply {
                setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
            dialog.show()

        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1002->if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
               Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()

            }else {
                AlertDialog.Builder(this)
                    .setMessage("This permission is disabled. Enable it from settings")
                    .setPositiveButton("OKAY"){dialog,_->
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri: Uri = Uri.fromParts("package", packageName, null)
                        intent.data = uri
                        startActivity(intent)

                    }.setNegativeButton("CANCEL"){dialog,_ ->
                        dialog.dismiss()
                    }
                    .show()

            }
            1003->if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()

            }else {
                AlertDialog.Builder(this)
                    .setMessage("This permission is disabled. Enable it from settings")
                    .setPositiveButton("OKAY"){dialog,_->
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri: Uri = Uri.fromParts("package", packageName, null)
                        intent.data = uri
                        startActivity(intent)

                    }.setNegativeButton("CANCEL"){dialog,_ ->
                        dialog.dismiss()
                    }
                    .show()

            }
        }
    }

    private fun takeImage() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=
            PackageManager.PERMISSION_GRANTED){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){
                    showRationaleDialog(
                        getString(R.string.title_rationale),
                        getString(R.string.desc_rationale),
                        Manifest.permission.CAMERA,
                        1002
                    )
                }else{
                    requestPermissions(arrayOf(Manifest.permission.CAMERA), 1002)
                }
            }

        }else{
            lifecycleScope.launchWhenStarted {
                getTmpFileUri().let { uri ->
                    latestTmpUri = uri
                    takeImageResult.launch(uri)
                }
            }
        }
    }
    private fun getTmpFileUri(): Uri {
        val tmpFile = File.createTempFile("tmp_image_file", ".png",cacheDir).apply {
            createNewFile()
            deleteOnExit()
        }


        return FileProvider.getUriForFile(applicationContext!!, "${BuildConfig.APPLICATION_ID}.provider", tmpFile)
    }
    private fun selectImageFromGallery() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
            PackageManager.PERMISSION_GRANTED){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)){
                    showRationaleDialog(
                        getString(R.string.title_rationale),
                        getString(R.string.desc_rationale),
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        1003
                    )
                }else{
                    requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1003)
                }
            }

        }else{
            selectImageFromGalleryResult.launch("image/*")
        }
    }

    private fun showRationaleDialog(title: String, message: String, permission: String, reqCode: Int) {
        with(AlertDialog.Builder(this)){
            setCancelable(false)
            setTitle(title)
            setMessage(message)
            setPositiveButton("OKAY"){dialog,_->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    requestPermissions(arrayOf(permission), reqCode)
                    dialog.dismiss()
                }

            }.show()
        }
    }
}