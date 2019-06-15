package com.esi.projetmobile.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.ParcelFileDescriptor
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
    return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
}

fun getCompressedBitmap(
    realEstate: RealEstate,
    bytearrayoutputstream: ByteArrayOutputStream, context: Context
): Bitmap? {
    val bitmap = getBitmapFromUri(Uri.parse(realEstate.images[0]), context)
    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytearrayoutputstream)
    val bytes = bytearrayoutputstream.toByteArray()
    return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
}


