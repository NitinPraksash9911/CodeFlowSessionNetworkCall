package com.example.codeflowsession.localdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="user_table" )
data class LocalUser(

    @PrimaryKey(autoGenerate = true)
    val id:Int=0,

    @ColumnInfo(name="name")
    val name:String,

    @ColumnInfo(name="email")
    val email:String
)
