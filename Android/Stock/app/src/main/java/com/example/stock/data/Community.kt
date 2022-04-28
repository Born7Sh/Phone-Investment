package com.example.stock.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Community(
    var userId : String,
    var companyName : String,
    var title : String,
    var content : String
): Parcelable