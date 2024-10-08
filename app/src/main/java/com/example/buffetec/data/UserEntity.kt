package com.example.buffetec.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val surname: String,
    val email: String,
    val pwd: String,
    val address: String,
    val neighborhood: String,
    val city: String,
    val state: String,
    val cp: String
)
