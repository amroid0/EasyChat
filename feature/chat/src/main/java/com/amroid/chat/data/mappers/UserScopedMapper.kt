package com.amroid.chat.data.mappers

interface UserScopedMapper<I,O> {
    fun map(userId:String,input:I):O
}