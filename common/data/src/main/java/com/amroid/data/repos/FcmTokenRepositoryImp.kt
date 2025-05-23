package com.amroid.data.repos

import com.amroid.data.FCMTokenDataSource
import com.amroid.domain.repos.IFcmTokenRepository

class FcmTokenRepositoryImp(val dataSource: FCMTokenDataSource) : IFcmTokenRepository {
    override fun getToken(): String {
        return dataSource.getToken()
    }
}