package com.esi.projetmobile.Utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.ParcelFileDescriptor
import android.support.v7.app.AlertDialog
import java.io.FileDescriptor

class Utils {

     fun getBitmapFromUri(uri: Uri, context: Context): Bitmap {
        val parcelFileDescriptor: ParcelFileDescriptor = context.contentResolver.openFileDescriptor(uri, "r")!!
        val fileDescriptor: FileDescriptor = parcelFileDescriptor.fileDescriptor
        val image: Bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        parcelFileDescriptor.close()
        return image
    }

     fun openDialog(dialog: AlertDialog, context: Context) {
        val chooseFile = Intent(Intent.ACTION_GET_CONTENT)
        val intent: Intent
        chooseFile.type = "*/*"
        intent = Intent.createChooser(chooseFile, "Choose a file")
        (context as Activity).startActivityForResult(intent, 301)
        dialog.dismiss()

    }
}