package com.example.buffetec.Components.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.buffetec.Components.ButtonComponent
import com.example.buffetec.network.LoginRequest
import com.example.buffetec.network.LoginResponse
import com.example.buffetec.network.RetrofitClient
import com.example.buffetec.ui.theme.lexendFontFamily
import com.example.lazycolumnexample.navigation.Screen
import com.example.tareaimc.InputField
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun Login(navController: NavHostController) {
    val user = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFF622CFF))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Text(
                    text = "Buen Día",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center),
                    fontFamily = lexendFontFamily,
                    style = TextStyle(
                        fontWeight = FontWeight.ExtraLight,
                        fontSize = 50.sp
                    )
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .padding(35.dp, 15.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .height(200.dp)
                    .background(Color.White, shape = RoundedCornerShape(20.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 80.dp)
                ) {
                    InputField(
                        label = "Usuario",
                        value = user.value,
                        onValueChange = { user.value = it },
                        lexendFontFam = lexendFontFamily,
                        visualTrans = false
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    InputField(
                        label = "Contraseña",
                        value = password.value,
                        onValueChange = { password.value = it },
                        lexendFontFam = lexendFontFamily,
                        visualTrans = true
                    )
                    Spacer(modifier = Modifier.height(30.dp))

                    ButtonComponent(label = "Log In", onClick = {
                        val apiService = RetrofitClient.apiService  // Ya no se pasa 'context'
                        val loginRequest = LoginRequest(email = user.value, password = password.value)

                        apiService.loginUser(loginRequest).enqueue(object : Callback<LoginResponse> {
                            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                                if (response.isSuccessful) {
                                    val loginResponse = response.body()
                                    val token = loginResponse?.token

                                    // Guardar el token en SharedPreferences
                                    val sharedPref = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                                    sharedPref.edit().putString("auth_token", token).apply()

                                    Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                                    navController.navigate(Screen.Profile.route) {
                                        popUpTo(Screen.Login.route) { inclusive = true }
                                    }
                                } else {
                                    Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                                Toast.makeText(context, "Error en la conexión", Toast.LENGTH_SHORT).show()
                            }
                        })
                    })

                    Text(
                        text = "Registrarme",
                        fontFamily = lexendFontFamily,
                        fontWeight = FontWeight.ExtraLight,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(onClick = {
                                navController.navigate(Screen.Signup.route)
                            })
                    )
                }
            }
        }
    }
}