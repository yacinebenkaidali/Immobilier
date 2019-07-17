package com.esi.projetmobile.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.ParcelFileDescriptor
import com.esi.projetmobile.model.RealEstate
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
    context: Context
): Bitmap? {
    val bitmap = getBitmapFromUri(Uri.parse(imageUrl), context)
    return Bitmap.createScaledBitmap(bitmap, 250, 200, true)
}

fun getCompressedBitmap(
    realEstate: RealEstate,
    context: Context
): Bitmap? {
    val bitmap = getBitmapFromUri(Uri.parse(realEstate.images[0]), context)
    return Bitmap.createScaledBitmap(bitmap, (bitmap.width * 0.7).toInt(), (bitmap.height * 0.7).toInt(), true)
}


