package pl.kcieslar.statusosp.model.service.impl

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import pl.kcieslar.statusosp.model.service.RealtimeDatabaseService
import javax.inject.Inject

class RealtimeDatabaseServiceImpl @Inject constructor(
    private val auth: FirebaseAuth
) : RealtimeDatabaseService {
    private val actionRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child(auth.uid!!)

    override suspend fun savePushNotificationAccept(isAccepted: Boolean) {
        actionRef.child("settings").child("pushEnabled").setValue(isAccepted).await()
    }

    override suspend fun saveUsername(username: String) {
        actionRef.child("settings").child("username").setValue(username).await()
    }
}
