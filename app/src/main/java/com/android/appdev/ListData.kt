package com.android.appdev

import android.net.Uri
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

data class ListData(
    val listimage: Uri,
    val listtitle: String,
    val listprogress: Int,
    val listprogresstv: String
    )