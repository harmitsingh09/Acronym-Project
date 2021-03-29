package com.acronym.app.logs

import android.util.Log

class SnippetLogs {
    companion object logs {


        fun debug(TAG: String, debugMessage: String?) {
            if (debugMessage != null) {
                Log.d(TAG, debugMessage)
            }

        }

        fun error(TAG: String, errorMessage: String) {
            Log.e(TAG, errorMessage)
        }

        fun info(TAG: String, infoMessage: String) {
            Log.i(TAG, infoMessage)
        }

        fun warn(TAG: String, warnMessage: String) {
            Log.w(TAG, warnMessage)
        }

        fun verbose(TAG: String, verboseMessage: String) {
            Log.v(TAG, verboseMessage)
        }


    }
}