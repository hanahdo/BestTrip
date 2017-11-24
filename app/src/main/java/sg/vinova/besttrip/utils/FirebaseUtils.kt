package sg.vinova.besttrip.utils

import android.content.Context
import android.text.TextUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

/**
 * Created by Hanah on 11/23/2017.
 */
class FirebaseUtils {
    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    companion object {
        fun newInstance(context: Context): FirebaseUtils = FirebaseUtils()
    }

    fun loginWithToken(string: String): String {
        var result = ""
        mAuth.signInWithCustomToken(string).addOnCompleteListener({ task -> if (task.isComplete) result = task.result.user.uid })
        return result
    }
}