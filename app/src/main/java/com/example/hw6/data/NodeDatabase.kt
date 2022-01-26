package com.example.hw6.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


// создание базы данных
@Database(entities = [Node::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)

abstract class NodeDatabase: RoomDatabase() {

    abstract fun nodeDao(): NodeDao

    companion object{
        @Volatile
        private var INSTANCE: NodeDatabase? = null

        fun getDatabase(context: Context): NodeDatabase{ // реализация singleton
            val tempInstance = INSTANCE
            if(tempInstance != null){ // если база данных уже существует - не равна null
                return tempInstance // возвращаем базу данных
            }
            synchronized(this){ // в противном случае - создаём
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NodeDatabase::class.java,
                    "node_database"
                ).build()
                INSTANCE = instance
                return instance // и возвращаем
            }
        }
    }
}
