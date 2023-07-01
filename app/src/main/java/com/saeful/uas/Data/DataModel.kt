package com.saeful.uas.Data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class DataModel(
    val image : Int,
    val title : String,
    val alamat : String,
    val rating : String,
    val desc : String,
    val jumlah_rating : String
) : Parcelable