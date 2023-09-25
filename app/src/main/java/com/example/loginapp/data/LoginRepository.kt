package com.example.loginapp.data

import com.example.loginapp.model.network.AuthResponse
import com.example.loginapp.model.network.Credentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume

fun mapExceptionMessage(message: String): String {
    return when (message) {
        "The email address is badly formatted." -> "Invalid email or password"
        "An internal error has occurred. [ INVALID_LOGIN_CREDENTIALS ]" -> "Invalid email or password"
        else -> "Unknown Server Error. Please Contact Support on 0000 0000"
    }
}

class LoginRepository {
    suspend fun authenticateFirebase(credentials: Credentials): AuthResponse {

        return withContext(Dispatchers.IO) {suspendCancellableCoroutine { continuation ->
            FirebaseAuth.auth.signInWithEmailAndPassword(credentials.username, credentials.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resume(AuthResponse(success = true, message = "Success"))
                    } else {
                        continuation.resume(
                            AuthResponse(
                                success = false,
                                message = mapExceptionMessage(task.exception?.message.toString())
                            )
                        )
                    }
                }
            }
        }
    }
}