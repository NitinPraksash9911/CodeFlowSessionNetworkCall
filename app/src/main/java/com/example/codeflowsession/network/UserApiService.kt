package com.example.codeflowsession.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiService {

    @GET("api/users")
    suspend fun fetchUsers(@Query("page") page:Int):Response<UserData>
}