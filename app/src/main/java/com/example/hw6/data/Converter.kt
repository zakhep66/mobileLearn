package com.example.hw6.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converter {

    @TypeConverter
    fun fromList(list: List<Node>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromString(value: String): List<Node> {
        val listType = object : TypeToken<List<Node>>() {}.type
        return Gson().fromJson(value, listType)
    }
}
