package com.amroid.easychat.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.amroid.chat.ui.ChatScreen
import com.amroid.chatlist.ui.NewChatScreen
import com.amroid.home.ui.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home") {
        addHome(navController)
        addNewConversation(navController)
        addChat(navController)
    }
}

fun NavGraphBuilder.addHome(navController: NavController) {
    composable(route = "Home") {
        HomeScreen(newChatClick = {
            navController.navigateToNewChat()
        }, onConversationListItemClicked = { chatId ->
            navController.navigateToChatDetail(chatId)
        })
    }
}

fun NavGraphBuilder.addChat(navController: NavController) {
    composable(
        "chat/{id}", arguments = listOf(navArgument("id") {
            type = NavType.StringType
        })
    ) { navBack ->
        val chatId = navBack.arguments?.getString("id")
        ChatScreen(chatId, onBack = {
            navController.navigateUp()
        })
    }

}

fun NavGraphBuilder.addNewConversation(navController: NavController) {
    composable(
        "new_chat",
    ) {
      NewChatScreen {
          navController.navigateUp()
      }
    }
}

fun NavController.navigateToNewChat() {
    navigate("new_chat")
}

fun NavController.navigateToChatDetail(id: String) {
    navigate("chat/{id}".replace("{chatId}", id))
}