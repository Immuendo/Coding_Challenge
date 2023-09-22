package com.mundo.codingtest.data.model

import com.google.gson.annotations.SerializedName


data class Language(
    @SerializedName("code")
    val code: String,
    @SerializedName("iso639_2")
    val iso: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("nativeName")
    val nativeName: String
)