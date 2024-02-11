package hr.ferit.helenaborzan.my_future_career

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import hr.ferit.helenaborzan.my_future_career.data.CareerViewModel
import hr.ferit.helenaborzan.my_future_career.ui.ArticleDetailsScreen
import hr.ferit.helenaborzan.my_future_career.ui.ArticleScreen
import hr.ferit.helenaborzan.my_future_career.ui.HomeScreen
import hr.ferit.helenaborzan.my_future_career.ui.QuizResultsScreen
import hr.ferit.helenaborzan.my_future_career.ui.QuizScreen

object Routes {
    const val HOME_SCREEN = "homeScreen"
    const val ARTICLE_SCREEN = "articleScreen"
    const val ARTICLE_DETAILS_SCREEN = "articleDetailsScreen/{careerId}"
    const val QUIZ_SCREEN = "quizScreen"
    const val QUIZ_RESULT_SCREEN = "quizResultScreen/{careerId}"

    fun getArticleDetailsPath(careerId : Int?) : String{
        if (careerId != null && careerId != -1){
            return "articleDetailsScreen/$careerId"
        }
        return "articleDetailsScreen/0"
    }

    fun getQuizResultPath(careerId: Int?) : String{
        if (careerId != null && careerId != -1){
            return "quizResultScreen/$careerId"
        }
        return "quizResultScreen/0"
    }

}
@Composable
fun NavigationController(viewModel : CareerViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =
    Routes.HOME_SCREEN) {
        composable(Routes.HOME_SCREEN) {
            HomeScreen(navigation = navController, viewModel = viewModel)
        }
        composable(
            route = "${Routes.ARTICLE_SCREEN}/{title}/{filter}",
            arguments = listOf(
            navArgument("title") { type = NavType.StringType },
            navArgument("filter") { type = NavType.StringType }
            )
        ) { entry ->
            ArticleScreen(
                navigation = navController,
                title = entry.arguments?.getString("title") ?: "",
                viewModel = viewModel,
                filter = entry.arguments?.getString("filter") ?: ""
            )
        }
        composable(
            Routes.ARTICLE_DETAILS_SCREEN,
            arguments = listOf(
                navArgument("careerId") {type = NavType.IntType},
            )
        ){ backStackEntry ->
            backStackEntry.arguments?.getInt("careerId")?.let {
                ArticleDetailsScreen(
                    careerId = it,
                    navigation = navController,
                    viewModel = viewModel
                )
            }
        }

        composable(
            route = "${Routes.QUIZ_SCREEN}/{numberOfQuestion}",
            arguments = listOf(
                navArgument("numberOfQuestion") { type = NavType.IntType}
            )
        ){ entry ->
            QuizScreen(
                navigation = navController,
                viewModel = viewModel,
                numberOfQuestion = entry.arguments?.getInt("numberOfQuestion") ?: 0
            )
        }

        composable(
            Routes.QUIZ_RESULT_SCREEN,
            arguments = listOf(
                navArgument("careerId"){ type = NavType.IntType }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt("careerId")?.let {
                QuizResultsScreen(
                    navigation = navController,
                    viewModel = viewModel,
                    careerId = it
                )
            }
        }


    }
}