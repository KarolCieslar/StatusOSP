package pl.kcieslar.statusosp.model.service.impl

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import pl.kcieslar.statusosp.model.objects.Group
import pl.kcieslar.statusosp.model.objects.GroupUser
import pl.kcieslar.statusosp.model.objects.GroupUserStatus
import pl.kcieslar.statusosp.model.service.RealtimeDatabaseService
import pl.kcieslar.statusosp.model.service.responses.UserGroupsResponse
import pl.kcieslar.statusosp.screens.group_list.GroupListUiState
import timber.log.Timber
import java.lang.IllegalArgumentException
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
    }

    override suspend fun getUserGroups(): List<Group> {
//        groupsRef.child("test").child("test").setValue(
//            Group(
//                "code", "nazwa", "descr", "uidadmin",
//                users = mapOf("dasasd" to GroupUser("user", "opis", GroupUserStatus.BUSY))
//            )
//        ).await()
        val groupList = mutableListOf<Group>()
        val userGroupKeys = userRef.child("groups").get().await().children.map { it.key }
        userGroupKeys.forEach { groupKey ->
            val group = groupsRef.child(groupKey!!).get().await().getValue(Group::class.java)
            group?.let {
                groupList.add(group)
            }
        }
        return groupList
    }

//    override fun getGroupData(groupKeys: List<String>): Flow<UserGroupsResponse?> {
//        val response = UserGroupsResponse()
//        val groupList = mutableListOf<Group>()
//        val listeners = mutableListOf<ValueEventListener>()
//        return callbackFlow {
//            groupKeys.forEach {
//                Timber.d("dsasdsss $it")
//                val singleListener = groupsRef.child(it).addValueEventListener(object : ValueEventListener {
//                    override fun onDataChange(dataSnapshot: DataSnapshot) {
//                        Timber.d("dsasdsss ${dataSnapshot}")
//                        try {
//                            groupList.add(dataSnapshot.getValue(Group::class.java)!!)
//                            response.list = groupList
//                        } catch (exception: Exception) {
//                            response.exception = exception
//                            Timber.d("dsasd $exception")
//                        }
//                    }
//
//                    override fun onCancelled(error: DatabaseError) {
//                        response.exception = Exception(error.message)
//                        cancel()
//                    }
//                })
//                listeners.add(singleListener)
//            }
//            trySend(response)
//            listeners.forEach {
//                awaitClose { groupsRef.removeEventListener(it) }
//            }
//        }
//    }
}
