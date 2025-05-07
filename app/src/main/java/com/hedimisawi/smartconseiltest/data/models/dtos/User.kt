package com.hedimisawi.smartconseiltest.data.models.dtos


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)