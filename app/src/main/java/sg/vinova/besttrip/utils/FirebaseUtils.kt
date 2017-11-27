package sg.vinova.besttrip.utils

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.reactivex.Single
import io.reactivex.rxkotlin.toSingle
import sg.vinova.besttrip.model.FirebaseAuthModel

/**
 * Created by Hanah on 11/23/2017.
 */
class FirebaseUtils {
    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    companion object {
        fun newInstance(context: Context): FirebaseUtils = FirebaseUtils()
    }

    fun loginWithToken(string: String): FirebaseAuthModel {
        var result: FirebaseAuthModel? = null
        mAuth.signInWithCustomToken(string).addOnCompleteListener({ task -> result = FirebaseAuthModel(task.result, task.exception) })
        return result!!
    }

    fun rxLoginWithCustomToken(token: String): Single<FirebaseUser> {
        var singleUser: Single<FirebaseUser>? = null
        mAuth.signInWithCustomToken(token)
                .addOnCompleteListener({ task ->
                    singleUser = task.result.user.toSingle()
                })
        return singleUser!!
    }
}