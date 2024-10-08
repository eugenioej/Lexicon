package com.example.buffetec.network

import retrofit2.Call  // Asegúrate de tener solo este import
import retrofit2.http.Body
import retrofit2.http.POST

// Define los datos del usuario que se enviarán a la API
data class User(
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

// Interfaz para la API con Retrofit
interface ApiService {
    @POST("/register")
    fun registerUser(@Body user: User): Call<UserResponse>
}

// Clase para manejar la respuesta de la API
data class UserResponse(
    val message: String,
    val newUser: User
)
