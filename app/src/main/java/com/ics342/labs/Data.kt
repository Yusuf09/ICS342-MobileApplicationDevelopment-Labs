package com.ics342.labs

// Add the data classes here.
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Person(
    @Json(name = "id") val id: Int,
    @Json(name = "give_name") val firstName: String,
    @Json(name = "family_name") val lastName: String,
    @Json(name = "age") val age: Int
)