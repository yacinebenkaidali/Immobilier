package com.esi.projetmobile.Utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.ParcelFileDescriptor
import androidx.appcompat.app.AlertDialog
import java.io.FileDescriptor
import androidx.core.app.ActivityCompat.startActivityForResult


class Utils {

    fun getBitmapFromUri(uri: Uri, context: Context): Bitmap {
        val parcelFileDescriptor: ParcelFileDescriptor = context.contentResolver.openFileDescriptor(uri, "r")!!
        val fileDescriptor: FileDescriptor = parcelFileDescriptor.fileDescriptor
        val image: Bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        parcelFileDescriptor.close()
        return image
    }

    fun openImageChooser(dialog: AlertDialog, context: Context) {
        val chooseFile = Intent(Intent.ACTION_GET_CONTENT)
        val intent: Intent
        chooseFile.type = "*/*"
        intent = Intent.createChooser(chooseFile, "Choose a file")
        (context as Activity).startActivityForResult(intent, 301)
        dialog.dismiss()
    }

    fun openMultipleImagesChooser(dialog: AlertDialog, context: Context) {
        val intent = Intent()
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        (context as Activity).startActivityForResult(Intent.createChooser(intent, "Select Picture"), 302)
        dialog.dismiss()
    }
}