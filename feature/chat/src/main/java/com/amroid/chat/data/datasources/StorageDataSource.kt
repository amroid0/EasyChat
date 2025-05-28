package com.amroid.chat.data.datasources

import java.io.File


interface StorageDataSource {

    suspend fun uploadFile(localPath: String, remotePath: String)
    suspend fun downloadFile(localPath: File, remotePath: String)
}