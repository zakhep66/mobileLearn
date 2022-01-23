package com.example.hw6.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface NodeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNode(node: Node)

    @Query("SELECT * FROM node ORDER BY id ASC")
    fun readAllData(): LiveData<List<Node>>

}