package com.amroid.home.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.amroid.home.R
import com.amroid.home.ui.model.Conversation
import com.amroid.home.ui.model.generateTabList

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(newChatClick: () -> Unit,onConversationListItemClicked :(String)->Unit) {
    var tabIndex by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 })
    Scaffold(topBar = { TopAppBar(title = { Text(text = stringResource(R.string.home_title)) }, colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary

    )
    ) },
        floatingActionButton = {
            FloatingActionButton(onClick = newChatClick, shape = CircleShape) {
                Icon(Icons.Filled.Add, "floating action Button")
            }
        },

        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            TabRow(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                indicator = {
                    TabRowDefaults.Indicator(
                      modifier =   Modifier.tabIndicatorOffset(it[tabIndex]),
                        color = Color.White
                    )
                },

                selectedTabIndex = tabIndex) {
                val tabList = generateTabList()
                tabList.forEachIndexed { index, item ->
                    Tab(text = { Text(text = stringResource(item.label)) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index })
                }


            }
        }

    ) { conntentPadding ->
        Column(modifier = Modifier.padding(conntentPadding)) {
          HorizontalPager(
              state = pagerState
          ) {
              when (it) {
                  0 -> {
                     ConversationList(generateConversitionList(),onConversationListItemClicked)
                  }
                  1 -> {}
                  2 -> {}

              }
            LaunchedEffect(tabIndex) {
                pagerState.animateScrollToPage(tabIndex)

            }

          }
        }
    }

}
fun generateConversitionList()= listOf(
    Conversation("1","https://i.pravatar.cc/150?u=5","Amr Sayed","Hello bro","1/1/2025",2),
    Conversation("2","https://i.pravatar.cc/150?u=2","Omar Sayed","Hello ","1/1/2025",0)

)
@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen({ }, {})
}