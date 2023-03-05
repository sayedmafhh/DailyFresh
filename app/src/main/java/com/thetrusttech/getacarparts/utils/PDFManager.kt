package com.thetrusttech.getacarparts.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.pdf.PdfRenderer
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.os.ParcelFileDescriptor.MODE_READ_ONLY
import android.util.Log
import android.webkit.WebView
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream


class PDFManager {

    fun convertPDFtoImage(context: Context, file: File): List<Bitmap> {
        val fileDescriptor = ParcelFileDescriptor.open( File(file.toURI().path), MODE_READ_ONLY)
        val renderer = PdfRenderer(fileDescriptor)
        val pageCount = renderer.pageCount
        val listOfBitmap: MutableList<Bitmap> = mutableListOf()

        for (i in 0 until pageCount) {
            var filename = "pdf-temp-file-$i"

            val page = renderer.openPage(i)

            val bitmap = getBitmapFromView(page)
            listOfBitmap.add(bitmap)

            page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
            page.close()

            if (!bitmapIsBlankOrWhite(bitmap)) {
                val root = Environment.getExternalStorageDirectory().toString()
                val file = File("$root$filename.png")
                //if (file.exists()) file.delete()
                try {
                    val out = FileOutputStream(file)
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
                    Log.v("Saved Image - ", file.getAbsolutePath())
                    out.flush()
                    out.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return listOfBitmap
    }

    private fun bitmapIsBlankOrWhite(bitmap: Bitmap?): Boolean {
        if (bitmap == null) return true
        val w = bitmap.width
        val h = bitmap.height
        for (i in 0 until w) {
            for (j in 0 until h) {
                val pixel = bitmap.getPixel(i, j)
                if (pixel != Color.WHITE) {
                    return false
                }
            }
        }
        return true
    }

    fun getBitmapFromView(view: PdfRenderer.Page): Bitmap {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        Canvas(bitmap).apply {
            drawBitmap(bitmap, 0f, 0f, null)
            drawColor(Color.WHITE)
        }
        return bitmap
    }

    fun loadPdfFromGoogleDrive(webview: WebView) {
        webview.settings.javaScriptEnabled = true
        webview.settings.setSupportZoom(true)

        val pdfViewerURL = "https://drive.google.com/viewerng/viewer?embedded=true&url=";
        val pdfURL = "https://www.quran-pdf.com/arabic-quran2.pdf";
        var url = pdfViewerURL + pdfURL
        webview.loadUrl(url)
    }


    private val BUFFER_SIZE = 4 * 1024

    private fun copyStreamToFile(inputStream: InputStream, outputFile: File) {
        inputStream.use { input ->
            val outputStream = FileOutputStream(outputFile)
            outputStream.use { output ->
                val buffer = ByteArray(BUFFER_SIZE)
                while (true) {
                    val byteCount = input.read(buffer)
                    if (byteCount < 0) break
                    output.write(buffer, 0, byteCount)
                }
                output.flush()
            }
        }
    }
}