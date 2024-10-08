package com.example.buffetec.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val surname: String,
    val email: String,
    val pwd: String,
    val address: String,
    val neighborhood: String,
    val city: String,
    val state: String,
    val cp: Int,
    val tipo_usuario: String = "usuario"
)
