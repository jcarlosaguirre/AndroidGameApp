package com.example.gameapp.classes

import com.google.gson.annotations.SerializedName

data class AdventuresResponse(
    @SerializedName("status") var status: String,
    @SerializedName("message") var imageUrls: List<String>
    )
