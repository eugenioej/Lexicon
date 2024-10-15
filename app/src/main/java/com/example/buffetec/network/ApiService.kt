package com.example.buffetec.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// Data class para la solicitud de login
data class LoginRequest(
    val email: String,
    val password: String // Asegúrate de que este campo sea "password" en lugar de "pwd" si el backend lo requiere
)

// Data class para manejar la respuesta del login (contiene el token)
data class LoginResponse(
    val message: String,
    val token: String
)

// Define la interfaz de la API con Retrofit
interface ApiService {
    @POST("/api/login")  // Asegúrate de que el endpoint sea correcto
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>
}