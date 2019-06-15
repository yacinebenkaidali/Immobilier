package com.esi.projetmobile.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.ParcelFileDescriptor
import androidx.appcompat.app.AlertDialog
import com.esi.projetmobile.model.RealEstate
import java.io.ByteArrayOutputStream
import java.io.FileDescriptor


fun getBitmapFromUri(uri: Uri, context: Context): Bitmap {
    val parcelFileDescriptor: ParcelFileDescriptor = context.contentResolver.openFileDescriptor(uri, "r")!!
    val fileDescriptor: FileDescriptor = parcelFileDescriptor.fileDescriptor
    val image: Bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
    parcelFileDescriptor.close()
    return image
}

fun getCompressedBitmap(
    imageUrl: String,
    bytearrayoutputstream: ByteArrayOutputStream, context: Context
): Bitmap? {
    val bitmap = getBitmapFromUri(Uri.parse(imageUrl), context)
    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytearrayoutputstream)
    val bytes = bytearrayoutputstream.toByteArray()
    val bitmapRes = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    return bitmapRes
}

fun getCompressedBitmap(
    realEstate: RealEstate,
    bytearrayoutputstream: ByteArrayOutputStream, context: Context
): Bitmap? {
    val bitmap = getBitmapFromUri(Uri.parse(realEstate.images[0]), context)
    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytearrayoutputstream)
    val bytes = bytearrayoutputstream.toByteArray()
    val bitmapRes = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    return bitmapRes
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
