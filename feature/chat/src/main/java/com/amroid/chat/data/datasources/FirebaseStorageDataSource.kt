package com.amroid.chat.data.datasources

import androidx.core.net.toUri
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject

class FirebaseStorageDataSource @Inject constructor(private val firebaseStorage: FirebaseStorage) :
    StorageDataSource {
    override suspend fun uploadFile(localPath: String, remotePath: String) {
        val storageRef = firebaseStorage.reference.child(remotePath)
        storageRef.putFile(localPath.toUri()).await()
    }

    override suspend fun downloadFile(localPath: File, remotePath: String) {
        val storageRef = firebaseStorage.reference.child(remotePath)
        storageRef.getFile(localPath).await()
    }
}