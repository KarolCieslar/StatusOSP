package pl.kcieslar.statusosp.model.service.impl

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject
import kotlinx.coroutines.tasks.await
import pl.kcieslar.statusosp.model.service.AccountService

class AccountServiceImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AccountService {

    private val userRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child(auth.uid!!)

    override suspend fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun isUserDoneFirstSetup(): Boolean {
        return userRef.child("settings").child("username").get().await().exists()
    }

    override suspend fun sendRecoveryEmail(email: String) {
        auth.sendPasswordResetEmail(email).await()
    }

    override suspend fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).await()
    }

    override suspend fun deleteAccount() {
        auth.currentUser!!.delete().await()
    }

    override suspend fun signOut() {
        if (auth.currentUser!!.isAnonymous) {
            auth.currentUser!!.delete()
        }
        auth.signOut()
    }
}
