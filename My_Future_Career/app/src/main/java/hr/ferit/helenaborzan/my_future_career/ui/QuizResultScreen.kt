package hr.ferit.helenaborzan.my_future_career.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import hr.ferit.helenaborzan.my_future_career.R
import hr.ferit.helenaborzan.my_future_career.data.Career
import hr.ferit.helenaborzan.my_future_career.data.CareerViewModel
import hr.ferit.helenaborzan.my_future_career.ui.theme.LightGreen
import hr.ferit.helenaborzan.my_future_career.ui.theme.Lilac

@Composable
fun QuizResultsScreen(navigation : NavController, viewModel : CareerViewModel, careerId : Int) {
    val career = viewModel.careerData[careerId]
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.radialGradient(0.0f to LightGreen, 1.0f to Lilac))
            .padding(20.dp)
    ) {
       HomeIcon(navigation = navigation)

        Column (
            modifier = Modifier
                .fillMaxHeight()
                .padding(30.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
        Text(
            text = stringResource(R.string.finishedQuiz),
            style = TextStyle(color = Color.White, fontSize=28.sp, fontFamily = FontFamily.SansSerif),
            textAlign = TextAlign.Center
        )

            Spacer(modifier = Modifier.size(40.dp))
            CareerDetailsCard(career)
        }
    }
}