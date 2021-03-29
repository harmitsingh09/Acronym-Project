package com.acronym.app.model

import com.google.gson.annotations.SerializedName

data class AcronymResponse(

	@field:SerializedName("sf")
	val sf: String? = null,

	@field:SerializedName("lfs")
	val lfs: List<LfsItem?>? = null
)