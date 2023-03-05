package com.thetrusttech.getacarparts.utils

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.thetrusttech.getacarparts.BuildConfig
import com.thetrusttech.getacarparts.R
import java.io.File

class MemoryStorageManager(private val context: Context) {

    private val filename = "SampleFile.txt"
    private val filepath = "QuranImages"

    fun getTmpFileUri(): Uri {
        val tmpFile = File.createTempFile("tmp_image_file", ".jpg", context.filesDir).apply {
            createNewFile()
            deleteOnExit()
        }
        return FileProvider.getUriForFile(context, "${BuildConfig.APPLICATION_ID}.provider", tmpFile)
    }

    private fun loadPhotosFromInternalStorage(): List<InternalStoragePhoto> {
        val files = context.filesDir.listFiles()
        return files?.filter {
            it.canRead() && it.isFile && it.name.endsWith(".jpg")
        }?.map {
            InternalStoragePhoto(it.name, it.toUri())
        } ?: listOf()
    }

    fun loadFilesFromFromFileDIR(): Array<File> {
        return context.filesDir.listFiles()
    }

    fun displayImage(view: ImageView) {
        Glide.with(context)
            .load(loadPhotosFromInternalStorage()[0].uri)
            .into(view)
    }

    data class InternalStoragePhoto(
        val name: String,
        val uri: Uri?
    )

    fun saveImageIntoGallery(itemImage: View, activity: Activity) {
        /*val fileName: String
        val imageFromView = getBitmapFromView(itemImage)

        ByteArrayOutputStream().apply {
            imageFromView.compress(Bitmap.CompressFormat.JPEG, 100, this)
            fileName = UUID.nameUUIDFromBytes(this.toByteArray()).toString().replace("-", "")
        }*/

        val drawable: Drawable = activity.getDrawable(R.drawable.a)!!
        val bitmap = drawable.toBitmap()

        val imageFile =
            File("${activity.filesDir}/$filepath/quran2.jpg/")

        if (!imageFile.exists()) {

            val contentResolver = ContentValues().apply {
                put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis())
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                put(MediaStore.Images.Media.DATA, imageFile.absolutePath)
            }

            activity.contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentResolver
            ).apply {
                bitmap.compress(
                    Bitmap.CompressFormat.JPEG,
                    100,
                    activity.contentResolver.openOutputStream(this!!)
                )
            }


            Toast.makeText(activity, "saved", Toast.LENGTH_SHORT).show()
        } else
            Toast.makeText(activity, "Already saved", Toast.LENGTH_SHORT).show()
    }

    fun getBitmapFromView(view: View): Bitmap {
        return Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888).apply {
            Canvas(this).apply {
                view.draw(this)
            }
        }
    }
}