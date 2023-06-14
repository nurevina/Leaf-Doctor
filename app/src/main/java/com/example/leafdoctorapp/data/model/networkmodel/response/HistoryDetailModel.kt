package com.example.leafdoctorapp.data.model.networkmodel.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class HistoryDetailResponse(

	@field:SerializedName("data")
	val data: DataHistory? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable

@Parcelize
data class DetectionResult(
	@field:SerializedName("isDetected")
	val isDetected: Boolean? = null,
	@field:SerializedName("labels")
	val labels: String? = null,
	@field:SerializedName("accuracy")
	val accuracy: Double? = null
) :Parcelable

@Parcelize
data class History(

	@field:SerializedName("data")
	val data: DetectionResult? = null,

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("categories")
	val categories: String? = null
) : Parcelable

@Parcelize
data class DataHistory(

	@field:SerializedName("history")
	val history: History? = null,

) : Parcelable
