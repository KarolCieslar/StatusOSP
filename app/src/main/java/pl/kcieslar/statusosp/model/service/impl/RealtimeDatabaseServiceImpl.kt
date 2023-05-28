package pl.kcieslar.statusosp.model.service.impl

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.tasks.await
import pl.kcieslar.statusosp.model.objects.Group
import pl.kcieslar.statusosp.model.service.RealtimeDatabaseService
import javax.inject.Inject

class RealtimeDatabaseServiceImpl @Inject constructor(
    private val auth: FirebaseAuth
) : RealtimeDatabaseService {
    private val userRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child(auth.uid!!)
    private val groupsRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child("groups")

    override suspend fun savePushNotificationAccept(isAccepted: Boolean) {
        userRef.child("settings").child("pushEnabled").setValue(isAccepted).await()
    }

    override suspend fun saveUsername(username: String) {
        userRef.child("settings").child("username").setValue(username).await()
    }

    override suspend fun createNewGroup(group: Group) {
        groupsRef.push().setValue(group).await()
    }

    override suspend fun isGroupExist(code: String): Boolean {
        return groupsRef.orderByChild("code").equalTo(code).get().await().exists()

//        var isExist = false
//        groupsRef.orderByChild("code").equalTo(code).addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                isExist = snapshot.exists()
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                isExist = false
//            }
//
//        })
//        return isExist
    }
}
