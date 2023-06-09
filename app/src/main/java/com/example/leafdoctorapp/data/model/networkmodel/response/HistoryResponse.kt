package com.example.leafdoctorapp.data.model.networkmodel.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class HistoryResponse(

	@field:SerializedName("data")
	val data: HistoryData? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable

@Parcelize
data class HistoryData(

	@field:SerializedName("history")
	val history: List<HistoryItem>? = null
) : Parcelable

@Parcelize
data class HistoryItem(

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("categories")
	val categories: String? = null
) : Parcelable
