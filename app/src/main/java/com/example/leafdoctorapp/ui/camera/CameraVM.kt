package com.example.leafdoctorapp.ui.camera

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leafdoctorapp.R
import com.example.leafdoctorapp.ml.LiteTomatoesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import javax.inject.Inject

@HiltViewModel
class CameraVM @Inject constructor(
    private val tomatoesModel: LiteTomatoesModel,
    @ApplicationContext applicationContext: Context
): ViewModel() {

    private val _result = MutableLiveData<List<Int>>()
    val result : LiveData<List<Int>> get() = _result


    /***
     * MASIH BELOM SIAP
     */
//    init {
//        viewModelScope.launch {
//            val bitmap = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.tomat)
//            _result.value = predictTomatoes(bitmap)
//        }
//    }
    fun predictTomatoes(bitmap: Bitmap) : List<Int>{
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap,224,224,true)
        val mlModel = tomatoesModel
        val byteBuffer = TensorImage.fromBitmap(scaledBitmap).buffer

        val inputFeature0 =
            TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
        inputFeature0.loadBuffer(byteBuffer)

        val outputs = tomatoesModel.process(inputFeature0)
        val outputTensorBuffer = outputs.outputFeature0AsTensorBuffer
        return getMax(outputTensorBuffer.floatArray)
    }

    private fun getMax(arr: FloatArray): List<Int> {
        var ind = 0
        var ind1 = 0
        var ind2 = 0
        var min = 0.0f
        var max1 = 0.0f
        var max2 = 0.0f

        for (i in 0..10) {
            if (arr[i] > min) {
                min = arr[i]
                ind = i
                max1 = arr[i]
            }
        }
        min = 0.0f
        for (i in 0..10) {
            if (arr[i] > min && arr[i] < max1) {
                min = arr[i]
                ind1 = i
                max2 = arr[i]
            }
        }

        min = 0.0f
        for (i in 0..10) {
            if (arr[i] > min && arr[i] < max2) {
                min = arr[i]
                ind2 = i
            }
        }

        return listOf(ind, ind1, ind2)
    }
}