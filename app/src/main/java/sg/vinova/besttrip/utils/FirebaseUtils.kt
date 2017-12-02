package sg.vinova.besttrip.utils

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

/**
 * Created by Hanah on 11/23/2017.
 */
object FirebaseUtils {
    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun loginWithCustomToken(token: String): Task<AuthResult> {
        return mAuth.signInWithCustomToken(token)
    }

    fun loginWithEmail(email: String, password: String): Task<AuthResult> {
        return mAuth.signInWithEmailAndPassword(email, password)
    }

    fun signUpWithEmail(email: String, password: String): Task<AuthResult> {
        return mAuth.createUserWithEmailAndPassword(email, password)
    }

    fun forgotPassword(email: String): Task<Void> {
        return mAuth.sendPasswordResetEmail(email)
    }

    fun logout() {
        return mAuth.signOut()
    }

    fun getCurrentUser(): FirebaseUser? {
        return mAuth.currentUser
    }
}
