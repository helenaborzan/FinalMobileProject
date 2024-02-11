package hr.ferit.helenaborzan.my_future_career.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import hr.ferit.helenaborzan.my_future_career.R
import hr.ferit.helenaborzan.my_future_career.Routes
import hr.ferit.helenaborzan.my_future_career.data.Career
import hr.ferit.helenaborzan.my_future_career.data.CareerViewModel
import hr.ferit.helenaborzan.my_future_career.ui.theme.DarkBlue
import hr.ferit.helenaborzan.my_future_career.ui.theme.LightGreen
import hr.ferit.helenaborzan.my_future_career.ui.theme.Lilac

@Composable
fun ArticleScreen(
    navigation : NavController,
    title : String,
    viewModel: CareerViewModel,
    filter : String
    ){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.radialGradient(0.0f to LightGreen, 1.0f to Lilac))
            .padding(10.dp)
    ) {
        Title(title = title){ navigation.popBackStack() }
            Spacer(modifier = Modifier.size(40.dp))
        CareerList(navigation = navigation, viewModel = viewModel, filter = filter)
    }
}

@Composable
fun Title(title : String, onClick : () -> Unit) {
    Row (
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ){

        Icon(
            painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
            contentDescription = stringResource(R.string.backIcon),
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 0.dp)
                .weight(0.25f)
                .clickable { onClick() }
        )
        Text(
            text = title,
            style = TextStyle(
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(2f)
        )
        Spacer(modifier = Modifier.weight(0.25f))

    }
}

@Composable
fun CareerItem(title : String, onClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(color = Color.White, shape = RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 0.dp)

    ) {
        Text(
            text = title,
            style = TextStyle(color = DarkBlue, fontSize = 18.sp, fontFamily = FontFamily.SansSerif),
            modifier = Modifier.padding(10.dp)
        )
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            Icon(
                painter = painterResource(R.drawable.baseline_navigate_next_24),
                contentDescription = null,
                modifier = Modifier
                    .size(18.dp)
                    .clickable { onClick() }
            )
        }
    }
}

@Composable
fun CareerList(viewModel : CareerViewModel, filter : String, navigation: NavController) {
    val filteredCareers = mutableListOf<Career>()
    val careerData = viewModel.careerData
    for (career in careerData){
        if (career.keywords.contains(filter)){
            filteredCareers.add(career)
        }
    }
    LazyColumn(){
        items(filteredCareers.size){
            CareerItem(title = filteredCareers[it].name)
                {
                    for (i in 0 until careerData.size) {
                        if (filteredCareers[it].id == careerData[i].id)
                            navigation.navigate(Routes.getArticleDetailsPath(i))
                    }
                }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}





