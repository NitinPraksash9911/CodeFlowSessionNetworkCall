package com.example.codeflowsession.network

class UserRepository(private val userApiService: UserApiService) {

    suspend fun fetchUsers(pageNo: Int) = userApiService.fetchUsers(pageNo)

}