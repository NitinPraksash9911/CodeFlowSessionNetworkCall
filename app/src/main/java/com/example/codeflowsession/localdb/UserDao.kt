package com.example.codeflowsession.localdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: LocalUser)

    @Delete
    fun delete(user: LocalUser)

    @Update
    fun update(user: LocalUser)

    @Query("Select * from user_table")
    fun read(): List<LocalUser>

    @Query("Select * from user_table where id=:id")
    fun getUser(id: Int): LocalUser

}