package com.example.leafdoctorapp.data.model.networkmodel.response

import com.google.gson.annotations.SerializedName

data class HistoryDetailResponse(

	@field:SerializedName("data")
	val data: History? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class History(

	@field:SerializedName("data")
	val itemData: ItemData? = null,

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
)

data class ItemData(

	@field:SerializedName("imageFilename")
	val imageFilename: String? = null,

	@field:SerializedName("confidence")
	val confidence: Any? = null,

	@field:SerializedName("prediction")
	val prediction: String? = null,

	@field:SerializedName("category")
	val category: String? = null
)
