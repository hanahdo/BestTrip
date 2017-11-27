package sg.vinova.besttrip.model

import com.google.firebase.auth.AuthResult
import java.lang.Exception

/**
 * Created by hanah on 11/24/17.
 */
data class FirebaseAuthModel(var authResult: AuthResult, var exception: Exception?)