package com.example.recyclerviewwithdiffutils.repository

import com.example.recyclerviewwithdiffutils.model.Todo
import com.example.recyclerviewwithdiffutils.network.RetrofitInterface
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class TodoRepository(baseUrl:String,isDebug:Boolean):Repository(baseUrl, isDebug){
    val retrofitInterface = retrofit.create(RetrofitInterface::class.java)
    inner class Result(val todoList:List<Todo>?=null,val errorMessage:String?= null){
        fun hasList():Boolean{
            return todoList != null && !todoList.isEmpty()
        }

        fun haseRROR():Boolean{
            return  errorMessage!=null
        }
    }

    fun getTodo():Single<Result> = retrofitInterface.getTodos()
        .map{ Result(todoList = it) }
        .onErrorReturn { Result(errorMessage = it.message) }
}
