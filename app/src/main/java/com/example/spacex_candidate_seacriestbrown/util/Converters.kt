package com.example.spacex_candidate_seacriestbrown.util

import androidx.room.TypeConverter
import com.example.spacex_candidate_seacriestbrown.data.model.local.EntityLaunchData
import com.google.gson.Gson


class Converters {

    @TypeConverter
    fun listToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}