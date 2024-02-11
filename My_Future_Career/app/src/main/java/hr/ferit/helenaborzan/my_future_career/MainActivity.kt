package hr.ferit.helenaborzan.my_future_career

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import hr.ferit.helenaborzan.my_future_career.data.CareerViewModel
import hr.ferit.helenaborzan.my_future_career.ui.ArticleDetailsScreen
import hr.ferit.helenaborzan.my_future_career.ui.HomeScreen
import hr.ferit.helenaborzan.my_future_career.ui.QuizResultsScreen
import hr.ferit.helenaborzan.my_future_career.ui.QuizScreen
import hr.ferit.helenaborzan.my_future_career.ui.theme.My_Future_CareerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<CareerViewModel>()
        setContent {
            My_Future_CareerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationController(viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    My_Future_CareerTheme {
        Greeting("Android")
    }
}