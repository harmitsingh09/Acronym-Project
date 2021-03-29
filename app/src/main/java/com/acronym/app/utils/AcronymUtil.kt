package com.acronym.app.utils

import com.acronym.app.model.AcronymResponse

object AcronymUtil {

    fun isAcronymValidToSearch(acronym: String) : Boolean {
        return acronym.length > 1
    }

    fun isListNotEmpty(list: List<AcronymResponse>?): Boolean {
        return list != null && list.isNotEmpty()
    }
}