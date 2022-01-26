package com.example.hw6.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


// Вадя посоветовал, он нашёл на гите
// конвертит данные, чтобы их можно было записать в бд и когда вытащу использовать
// список нельзя хранить в бд - сложный объект, поэтому нужно его преобразовать в примитивный тип данных - строку
class Converter {

    @TypeConverter
    fun fromNode (nodes: MutableList<Node>): String {
        return Gson().toJson(nodes)
    }

    @TypeConverter
    fun toNode (nodes: String): MutableList<Node>{
        val listType = object : TypeToken<MutableList<Node>>() {}.type
        return Gson().fromJson(nodes, listType)
    }
}
