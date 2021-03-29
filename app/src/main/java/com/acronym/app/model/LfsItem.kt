package com.acronym.app.model

import com.google.gson.annotations.SerializedName

data class LfsItem(

	@field:SerializedName("freq")
	val freq: Int? = null,

	@field:SerializedName("lf")
	val lf: String? = null,

	@field:SerializedName("vars")
	val vars: List<VarsItem?>? = null,

	@field:SerializedName("since")
	val since: Int? = null
)