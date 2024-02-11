package hr.ferit.helenaborzan.my_future_career.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import hr.ferit.helenaborzan.my_future_career.R
import hr.ferit.helenaborzan.my_future_career.data.Career
import hr.ferit.helenaborzan.my_future_career.data.CareerViewModel
import hr.ferit.helenaborzan.my_future_career.ui.theme.DarkBlue
import hr.ferit.helenaborzan.my_future_career.ui.theme.LightGray
import hr.ferit.helenaborzan.my_future_career.ui.theme.LightGreen
import hr.ferit.helenaborzan.my_future_career.ui.theme.Lilac

@Composable
fun ArticleDetailsScreen(careerId : Int, navigation : NavController, viewModel: CareerViewModel) {
    val career = viewModel.careerData[careerId]

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.radialGradient(0.0f to LightGreen, 1.0f to Lilac))
                .padding(20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            BackIcon(navigation)
            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                CareerDetailsCard(career)
            }
        }
}


@Composable
fun BackIcon(navigation : NavController) {
    Box (
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart

    ){
        Icon(
            painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
            contentDescription = stringResource(R.string.backIcon),
            modifier = Modifier.clickable { navigation.popBackStack() }
        )
    }
}

@Composable
fun CareerDetailsCard(
    career : Career
) {
    Column(
        modifier = Modifier
            .background(
                Brush.verticalGradient(0.0f to LightGray, 100.0f to Color.White),
                shape = RoundedCornerShape(12.dp)
            )
            .border(width = 1.dp, color = DarkBlue, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .height(450.dp)
            .width(260.dp)
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = career.name,
            style = TextStyle(color = DarkBlue, fontSize = 26.sp, fontFamily = FontFamily.SansSerif)
        )
        Image (
            painter = rememberAsyncImagePainter(model = career.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(170.dp)
                .padding(30.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Text(
            text = career.description,
            style = TextStyle(color = DarkBlue, fontSize = 18.sp, fontFamily = FontFamily.SansSerif),
            textAlign = TextAlign.Center
        )
    }
}
