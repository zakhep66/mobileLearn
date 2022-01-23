package com.example.hw6.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NodeViewModel(application: Application): AndroidViewModel(application) { // ссылка на приложение

    private val readAllData: LiveData<List<Node>>
    private val repository: NodeRepository

    init {
        val nodeDao = NodeDataBase.getDataBase(application).nodeDao()
        repository = NodeRepository(nodeDao)
        readAllData = repository.readAllData
    }

    fun addNode(node: Node){
        viewModelScope.launch(Dispatchers.IO) { // эта функция будет работать в фоне
            repository.addNode(node)
        }
    }


}