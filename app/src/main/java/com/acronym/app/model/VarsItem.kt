package com.acronym.app.model

import com.google.gson.annotations.SerializedName

data class VarsItem(

	@field:SerializedName("freq")
	val freq: Int? = null,

	@field:SerializedName("lf")
	val lf: String? = null,

	@field:SerializedName("since")
	val since: Int? = null
)