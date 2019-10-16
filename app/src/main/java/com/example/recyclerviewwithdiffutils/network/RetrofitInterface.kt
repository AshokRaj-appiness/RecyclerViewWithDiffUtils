package com.example.recyclerviewwithdiffutils.network

import com.example.recyclerviewwithdiffutils.model.Todo
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {

    @GET("/todos")
    fun getTodos(): Single<List<Todo>>
}