package com.amroid.home.ui.model

import androidx.annotation.StringRes
import com.amroid.home.R

data class TabModel(@StringRes val label :Int, )
fun generateTabList() =  listOf(TabModel(R.string.Home),TabModel(R.string.story),TabModel(R.string.call))
