package hr.ferit.helenaborzan.my_future_career.ui

import android.content.Intent
import android.content.Intent.ShortcutIconResource
import android.graphics.drawable.shapes.OvalShape
import android.net.Uri
import android.text.style.BackgroundColorSpan
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import hr.ferit.helenaborzan.my_future_career.R
import hr.ferit.helenaborzan.my_future_career.ui.theme.Lilac
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import hr.ferit.helenaborzan.my_future_career.NavigationController
import hr.ferit.helenaborzan.my_future_career.Routes
import hr.ferit.helenaborzan.my_future_career.data.Article
import hr.ferit.helenaborzan.my_future_career.data.CareerViewModel
import hr.ferit.helenaborzan.my_future_career.data.articles
import hr.ferit.helenaborzan.my_future_career.data.linkedArticles
import hr.ferit.helenaborzan.my_future_career.ui.theme.DarkBlue
import hr.ferit.helenaborzan.my_future_career.ui.theme.LightBlue
import hr.ferit.helenaborzan.my_future_career.ui.theme.LightGreen



@Composable
fun HomeScreen(navigation: NavController, viewModel: CareerViewModel){
    LazyColumn (modifier = Modifier
        .fillMaxSize()
        .background(Brush.radialGradient(0.0f to LightGreen, 1.0f to Lilac))
    ) {
        item {
            TitleBar()
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.size(100.dp))
                ArticleList(navigation)
                Spacer(modifier = Modifier.size(32.dp))
                FindCareerButton(navigation, viewModel)
                Spacer(modifier = Modifier.size(48.dp))
                LinkedArticlesList()
            }
        }
    }
}

@Composable
fun TitleBar() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(160.dp), contentAlignment = Alignment.Center){
        Image(
            painter = painterResource(R.drawable.title_bar_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(0.dp, 0.dp, 120.dp, 120.dp))
        )
        Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
            Icon(
                painterResource(R.drawable.baseline_business_center_24),
                null,
                Modifier
                    .padding(16.dp)
                    .size(32.dp),
                Color.White,
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = stringResource(R.string.title),
                style = TextStyle(color = Color.White, fontSize = 28.sp, fontFamily = FontFamily.SansSerif),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun ArticleCard(
    article : Article,
    onClick: () -> Unit
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(112.dp)
            .height(160.dp)
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .border(width = 1.dp, color = DarkBlue, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }

    ) {
        Text(
            text = article.title,
            style = TextStyle(color = DarkBlue, fontSize = 14.sp, fontFamily = FontFamily.SansSerif),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start=10.dp, top=10.dp, end = 10.dp, bottom=0.dp)
        )
        Icon(
            painter = painterResource(article.iconResource),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 4.dp, bottom = 16.dp)
                .size(52.dp))
    }

}

@Composable
fun ArticleList(navigation : NavController){
    LazyRow(modifier = Modifier.padding(start = 10.dp), horizontalArrangement = Arrangement.SpaceEvenly){
        items(articles.size){
            ArticleCard(article = articles[it]) {
                navigation.navigate("${Routes.ARTICLE_SCREEN}/${articles[it].title}/${articles[it].filter}")
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun FindCareerButton(navigation: NavController, viewModel: CareerViewModel) {
    Box (modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = LightBlue),
            onClick = { navigation.navigate("${Routes.QUIZ_SCREEN}/${0}") },
            modifier = Modifier.height(54.dp)
        ) {
            Text(
                text = stringResource(R.string.findYourCareerButtonText),
                style = TextStyle(color = Color.White, fontFamily = FontFamily.SansSerif)
            )
        }
    }
}

@Composable
fun LinkedArticledCard(title : String, url : String) {
    val context = LocalContext.current
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .background(color = Color.White, shape = RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 4.dp)

    ){
        Text(
            text = title,
            style = TextStyle(color = DarkBlue, fontFamily = FontFamily.SansSerif)
        )
        Box (modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd){
            Icon(
                painter = painterResource(R.drawable.baseline_navigate_next_24),
                contentDescription = null,
                modifier = Modifier.clickable {
                    val uri = Uri.parse(url)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    context.startActivity(intent)
                }
            )
        }
    }
}

@Composable
fun LinkedArticlesList() {
    Column (modifier = Modifier.padding(10.dp)){
        Text(
            text = stringResource(R.string.articlesTitle),
            style = TextStyle(color = Color.White, fontSize = 24.sp, fontFamily = FontFamily.SansSerif)
        )
        Spacer(modifier = Modifier.size(30.dp))
        for (linkedArticle in linkedArticles){
            LinkedArticledCard(title = linkedArticle.title, url = linkedArticle.url)
            Spacer(modifier = Modifier.size(4.dp))
        }
    }
}

