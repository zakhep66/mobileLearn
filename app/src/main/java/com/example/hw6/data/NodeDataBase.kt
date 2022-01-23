package com.example.hw6.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext


@Database(entities = [Node::class], version = 1, exportSchema = false)
abstract class NodeDataBase: RoomDatabase() {

    abstract fun nodeDao(): NodeDao

    companion object{
        @Volatile
        private var INSTANCE: NodeDataBase? = null // переменная экземпляра изначально равна null

        fun getDataBase(context: Context): NodeDataBase{ // функция  получения базы данных
            val tempInstance = INSTANCE
            if (tempInstance != null){ // если экземпляр уже существует
                return tempInstance // возвращаем его
            } // если нет
            synchronized(this){ // создаём новый экземпляр
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NodeDataBase::class.java,
                    "node_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}