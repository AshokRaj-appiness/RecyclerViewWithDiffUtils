package com.example.recyclerviewwithdiffutils.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recyclerviewwithdiffutils.BuildConfig
import com.example.recyclerviewwithdiffutils.model.Todo
import com.example.recyclerviewwithdiffutils.repository.TodoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel:BaseViewModel() {
    val todoRepository = TodoRepository("https://jsonplaceholder.typicode.com/",BuildConfig.DEBUG)
    var myList = MutableLiveData<String>()
    var myData = MutableLiveData<List<Todo>>()
    fun getTodos(){
        todoRepository.getTodo()
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { compositeDisposable.add(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                result -> when{
                    result.hasList() -> result.todoList?.let { myData.value = it }
                    result.haseRROR() -> myList.value = result.errorMessage
                    else -> myList.value = "Null pointer error"
                }
            }
    }
    fun getTodoList():LiveData<List<Todo>>{
        return myData
    }
}