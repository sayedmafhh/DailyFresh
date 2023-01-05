package com.asgl.sdk.common

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color.BLACK
import android.graphics.Color.WHITE
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.IBinder
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.GsonBuilder
/*import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix*/
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.base.CoroutineUseCase
import com.thetrusttech.getacarparts.base.GlobalRuntimeVariableAccess
import org.jetbrains.annotations.NotNull
import org.json.JSONObject
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


val SNACKBAR_TEXT_MAX_LINES: Int = 5

/**
 * Created by bjayaram on 26,August,2019
 */

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

//An extension method to the live data observer where we can set the value to be observed once
fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            observer.onChanged(t)
            removeObserver(this)
        }
    })
}

fun SharedPreferences.contains(context: Context, prefKey: String, prefValueKey: String): Boolean {
    val sharedPreference = context.getSharedPreferences(prefKey, Context.MODE_PRIVATE)
    return sharedPreference.contains(prefValueKey)
}

fun showSnackBar(view: View, message: String, length: Int) {
    var snackbar = Snackbar.make(view, message, length)
    snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).maxLines = SNACKBAR_TEXT_MAX_LINES
    return snackbar.show()
}

fun showSnackBarAction(view: View, message: String, length: Int, actionMessage: String, action: ISnackBarAction) {
    Snackbar
        .make(view, message,length)
        .setAction(actionMessage) {
            action.onAction()
        }.show()
}

fun EditText.clearText() {
    this.setOnTouchListener { _, event ->
        if (this.compoundDrawables[2] != null) {
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (this.right - this.compoundDrawables[2].bounds.width())) {
                    this.text.clear()
                    true
                }
            }
        }
        false
    }
}

fun EditText.setupClearEditText(s: String?,@NotNull left: Int, @NotNull right: Int) {
    if (!TextUtils.isEmpty(s)) {
        this.setCompoundDrawablesRelativeWithIntrinsicBounds(left, 0, right, 0)
        this.clearText()
    } else {
        this.setCompoundDrawablesRelativeWithIntrinsicBounds(left, 0, 0, 0)
    }
}
// Generates a QR code and assigns it to an ImageView. dimension parameter is in dp.
/*fun generateQRCode(input: String, dimensions: Int, bitmap: (Bitmap) -> Unit) {
    CoroutineUseCase.io {
        val multiFormatWriter = MultiFormatWriter()
        var bitMatrix: BitMatrix? = null
        try {
            bitMatrix = multiFormatWriter.encode(
                input, BarcodeFormat.QR_CODE,
                dimensions.toPx(), dimensions.toPx()
            )
        } catch (e: WriterException) {
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
        bitMatrix?.let {
            getQRCodeBitmap(it){
                bitmap(it)
            }
        }
    }
}*/
fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density.toInt())

fun getDeviceInfo(): String {
    val brand = android.os.Build.BRAND
    val manuf = android.os.Build.MANUFACTURER
    val model = android.os.Build.MODEL
    val release = android.os.Build.VERSION.RELEASE
    val sdk = android.os.Build.VERSION.SDK_INT

    return "Brand - $brand\nManufacturer - $manuf\nModel - $model\n\nOS Version - $release\nSDK Version - $sdk\n\n"
}

/*private suspend fun getQRCodeBitmap(bitMatrix: BitMatrix, bitmap: (Bitmap) -> Unit) {
    CoroutineUseCase.withContextIo {
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bitmap: Bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (bitMatrix[x, y]) BLACK else WHITE)
            }
        }
        bitmap(bitmap)
        false
    }
}*/

/**
 * Hide Keyboard
 *
 * token can be taken from view like this:
 *
 * token = requireView().windowToken
 */
fun Context.hideKeyboard(token: IBinder) {
    val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    manager?.hideSoftInputFromWindow(token, 0)
}

interface ISnackBarAction {
    fun onAction()
}

fun <T: Any> String.convertToObject(clazz: Class<T>) : T {
    val gson = GsonBuilder().setLenient().create()
    return gson.fromJson(this, clazz)
}

fun Any.convertToString() = Gson().toJson(this)

class CommonUitlities {

    companion object {
        @JvmStatic
        fun getObjectFromResponse(response: String, classObject: Class<out Any>): Any? {
            //val jsonStringResponse = Gson().toJson(response)
            val responseObject =
                Gson().fromJson(response, classObject)
            return responseObject
        }

        @JvmStatic
        fun convertAnytoJson(jsonAsAny: Any, classObject: Class<out Any>): Any? {
            var map = JSONObject((jsonAsAny as Map<String,String>)).toString()
            var gson = GsonBuilder().setLenient().create()
            var data = gson.fromJson(map, classObject)
            return data
        }

        @JvmStatic
        fun convertJsonToString(classObject: Any): String {
            return Gson().toJson(classObject)
        }

        @JvmStatic
        fun showAlert(
            context: Context,
            listener: IDialogListener,
            title: String,
            message: String,
            positiveText: String,
            negativeText: String? = null,
            neutralText: String? = null
        ) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveText) { dialog, i ->
                    listener.onPositiveTapped(dialog, i)
                }
            if (!negativeText.isNullOrEmpty()) {
                builder.setNegativeButton(negativeText) { dialog, i ->
                    listener.onNegativeTapped(dialog, i)
                }
            }
            val dialog = builder.create()
            dialog.setCanceledOnTouchOutside(false)
            dialog.setCancelable(false)
            dialog.setOnShowListener { d ->
                listener.onAdditionalTapped(d,0)
            }
            dialog.show()
        }

        interface IDialogListener {
            fun onPositiveTapped(dialog: DialogInterface, position: Int)
            fun onNegativeTapped(dialog: DialogInterface, position: Int)
            fun onAdditionalTapped(dialog: DialogInterface, position: Int)
        }

        fun showPermissionAlert(context: Context,
                                listener: IDialogListener,
                                title: String,
                                optionOne: String,
                                optionTwo: String,
                                cancelOrDeny: String) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
                val options = arrayOf(optionOne, optionTwo, cancelOrDeny)
            builder.setItems(options) { dialog, i ->
                when (i) {
                    0 -> listener.onPositiveTapped(dialog, i)
                    1 -> listener.onNegativeTapped(dialog, i)
                    2 -> listener.onAdditionalTapped(dialog, i)
                }
            }
            builder.create().show()
        }

        fun compressImage(file:File ){
            try {
                // BitmapFactory options to downsize the image
                var bitmapOptions = BitmapFactory.Options()
                bitmapOptions.inJustDecodeBounds = true
                // bitmapOptions.inSampleSize = 6
                // factor of downsizing the image

                var inputStream = FileInputStream(file)
                //Bitmap selectedBitmap = null;
                BitmapFactory.decodeStream(inputStream, null, bitmapOptions)
                inputStream.close()

                // The new size we want to scale to
                var MAX_SIZE = GlobalRuntimeVariableAccess.context!!.getResources().getInteger(R.integer.max_edge_size)

                // Find the correct scale value. It should be the power of 2.
                var scale:Double = 1.0

                //Finding the if any edge length exceeds MAX_SIZE
                if(bitmapOptions.outHeight>MAX_SIZE || bitmapOptions.outWidth>MAX_SIZE){
                    //Finding whether height or width is longer edge
                    if(bitmapOptions.outHeight>bitmapOptions.outWidth){
                        //Height edge length is greater than width edge length
                        scale = (bitmapOptions.outHeight.toDouble()/MAX_SIZE.toDouble())
                    }
                    else{
                        scale = (bitmapOptions.outWidth.toDouble()/MAX_SIZE.toDouble())
                    }
                }


                var bitmapOptions2 = BitmapFactory.Options()
                bitmapOptions2.inSampleSize = Math.round(scale).toInt()
                inputStream =  FileInputStream(file)

                var selectedBitmap = BitmapFactory.decodeStream(inputStream, null, bitmapOptions2)
                selectedBitmap = getRotatedBitmap(file, selectedBitmap)
                inputStream.close()

                // here i override the original image file
                file.createNewFile()
                var outputStream = FileOutputStream(file)

                selectedBitmap!!.compress(
                    Bitmap.CompressFormat.JPEG,
                    GlobalRuntimeVariableAccess.context!!.getResources().getInteger(R.integer.image_quality_percent), outputStream)
            } catch (e: Exception) {
                Log.d("CommonUtilities", "Image compression failed")
            }
        }

        private fun getRotatedBitmap(
            file: File,
            selectedBitmap: Bitmap?
        ): Bitmap? {
            var selectedBitmap1 = selectedBitmap
            val exif = ExifInterface(file.absolutePath)
            var orientation = exif
                .getAttributeInt(ExifInterface.TAG_ORIENTATION, 1)
            Log.e("CommonUtilities", "" + orientation)
            val m = Matrix()

            if (orientation == 3) {
                m.postRotate(180F)
                m.postScale(
                    selectedBitmap1!!.getWidth() as Float,
                    selectedBitmap1!!.getHeight() as Float
                )
                // if(m.preRotate(90)){
                Log.e("CommonUtilities", "" + orientation)
                selectedBitmap1 = Bitmap.createBitmap(
                    selectedBitmap1, 0, 0, selectedBitmap1.getWidth(),
                    selectedBitmap1.getHeight(), m, true
                )
            } else if (orientation == 6) {
                m.postRotate(90F)
                Log.e("CommonUtilities", "" + orientation)
                selectedBitmap1 = Bitmap.createBitmap(
                    selectedBitmap1!!, 0, 0, selectedBitmap1.getWidth(),
                    selectedBitmap1.getHeight(), m, true
                )
            } else if (orientation == 8) {
                m.postRotate(270F)
                Log.e("CommonUtilities", "" + orientation)
                selectedBitmap1 = Bitmap.createBitmap(
                    selectedBitmap1!!, 0, 0, selectedBitmap1.getWidth(),
                    selectedBitmap1.getHeight(), m, true
                )
            }
            return selectedBitmap1
        }

        fun calculateFileSize(path:String): Double {
            val fileSize = File(path).length()

            val sizeInMb = fileSize / (1024.0 * 1024)

            val sizeInMbStr = "%.2f".format(sizeInMb)
            return sizeInMb
        }


        fun getImageResolution(path:String): String {
            var options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeFile(File(path).toString(), options)
            var imageHeight = options.outHeight;
            var imageWidth = options.outWidth;
            return "Width: $imageWidth  Height: $imageHeight"
        }
/*
        fun getErrorMessageForCode(context: Context, errorCode: Int): String {
            var errorMessage = context.getString(R.string.error_message_unknown_error)
            when (errorCode){
                SdkConstants.ERROR_304->{errorMessage = context.getString(R.string.error_message_304)}
                SdkConstants.ERROR_301->{errorMessage = context.getString(R.string.error_message_301)}
                in 300..399           ->{errorMessage = context.getString(R.string.error_message_3xx)}
                SdkConstants.ERROR_400->{errorMessage = context.getString(R.string.error_message_400)}
                SdkConstants.ERROR_401->{errorMessage = context.getString(R.string.error_message_401)}
                SdkConstants.ERROR_404->{errorMessage = context.getString(R.string.error_message_404)}
                SdkConstants.ERROR_403->{errorMessage = context.getString(R.string.error_message_403)}
                SdkConstants.ERROR_409->{errorMessage = context.getString(R.string.error_message_unknown_error)}
                in 400..499           ->{errorMessage = context.getString(R.string.error_message_4xx)}
                SdkConstants.ERROR_500->{errorMessage = context.getString(R.string.error_message_500)}
                SdkConstants.ERROR_503->{errorMessage = context.getString(R.string.error_message_503)}
                in 500..599           ->{errorMessage = context.getString(R.string.error_message_5xx)}
            }
            return errorMessage
        }*/

        fun getErrorMessageFromJson(json: String, classObject: Class<out Any>): Any? {
            //var parser: JsonParser = JsonParser()
            //var json = parser.parse(cleanjson)

            var cleanjson = json
                .replace("}\"","}")
                .replace("\"{","{")
                .replace("\\","")
            return getObjectFromResponse(cleanjson, classObject)
        }

        /**
         * Extension function to simplify setting an afterTextChanged action to EditText components.
         */
        fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
            this.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(editable: Editable?) {
                    afterTextChanged.invoke(editable.toString())
                }

                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//                afterTextChanged.invoke(s.toString())
                }
            })
        }

        /*fun setViewPagerSpecialEffectConfigurations(viewPager2: ViewPager2
                                                    , dimenNextItemVisiblePx: Float
                                                    , dimenCurrentItemHorizontalMarginPx: Float): ViewPager2 {
            // Add a PageTransformer that translates the next and previous items horizontally
            // towards the center of the screen, which makes them visible
            val nextItemVisiblePx = dimenNextItemVisiblePx
            val currentItemHorizontalMarginPx = dimenCurrentItemHorizontalMarginPx
            val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
            val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
                page.translationX = -pageTranslationX * position

                // Next line scales the item's height. You can remove it if you don't want this effect
                //page.scaleY = 1 - (0.25f * abs(position))

                // If you want a fading effect uncomment the next line:
                page.alpha = 0.25f + (1 - Math.abs(position))
            }
            viewPager2.setPageTransformer(pageTransformer)

            // The ItemDecoration gives the current (centered) item horizontal margin so that
            // it doesn't occupy the whole screen width. Without it the items overlap
            val itemDecoration = HorizontalMarginItemDecoration(dimenCurrentItemHorizontalMarginPx)
            viewPager2.addItemDecoration(itemDecoration)
            return viewPager2
        }*/

        fun isOnline(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager != null) {
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                        return true
                    }
                }
            }
            return false
        }
    }
}