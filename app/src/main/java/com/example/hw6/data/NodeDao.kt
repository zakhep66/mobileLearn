package com.example.hw6.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


// интерфейс для работы с DAO
@Dao

interface NodeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addNode(node: Node)

    @Query("SELECT * FROM node_table ORDER BY id ASC")
    fun readAllData(): LiveData<MutableList<Node>>

    @Query("UPDATE node_table SET nodes=:nodes WHERE id=:id")
    suspend fun updateNode(id: Int, nodes: MutableList<Node>)

}
