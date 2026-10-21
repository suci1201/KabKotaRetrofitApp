package com.example.kabkotaretrofitapp.response

import com.example.kabkotaretrofitapp.model.KabKotaItem
import com.google.gson.annotations.SerializedName

data class KabKotaResponse(

	@field:SerializedName("data")
	val data: List<KabKotaItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)