package com.example.leafdoctorapp.data.model.networkmodel.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class PredictResponse(

	@field:SerializedName("data")
	val data: PredictData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable

@Parcelize
data class Result(

	@field:SerializedName("accuracy")
	val accuracy: Double? = null,

	@field:SerializedName("isDetected")
	val isDetected: Boolean? = null,

	@field:SerializedName("labels")
	val labels: String? = null
) : Parcelable

@Parcelize
data class PredictData(

	@field:SerializedName("result")
	val result: Result? = null,

	@field:SerializedName("historyId")
	val historyId: String? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("category")
	val category: String? = null
) : Parcelable
