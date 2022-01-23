package com.example.hw6.data

import androidx.lifecycle.LiveData


// в моём случае он не обязателен, но его добавление считается лучшей практикой - этот класс, который абстрагирует доступ к нескольким источникам данных
class NodeRepository(private val nodeDao: NodeDao) { // объект доступа

    val readAllData: LiveData<List<Node>> = nodeDao.readAllData()

    suspend fun addNode(node: Node){ // в модели представления будем использовать сопрограммы
        nodeDao.addNode(node)
    }

}