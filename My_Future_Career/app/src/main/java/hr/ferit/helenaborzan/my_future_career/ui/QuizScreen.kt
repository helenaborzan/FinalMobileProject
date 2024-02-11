package hr.ferit.helenaborzan.my_future_career.ui

import android.widget.RadioButton
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import hr.ferit.helenaborzan.my_future_career.Routes.QUIZ_SCREEN
import hr.ferit.helenaborzan.my_future_career.data.Answer
import hr.ferit.helenaborzan.my_future_career.data.Career
import hr.ferit.helenaborzan.my_future_career.data.CareerViewModel
import hr.ferit.helenaborzan.my_future_career.data.Question
import hr.ferit.helenaborzan.my_future_career.ui.theme.DarkBlue
import hr.ferit.helenaborzan.my_future_career.ui.theme.LightBlue
import hr.ferit.helenaborzan.my_future_career.ui.theme.LightGreen
import hr.ferit.helenaborzan.my_future_career.ui.theme.Lilac

@Composable
fun QuizScreen(
    navigation: NavController,
    viewModel : CareerViewModel,
    numberOfQuestion : Int
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.radialGradient(0.0f to LightGreen, 1.0f to Lilac))
            .padding(20.dp)
        ){
        HomeIcon(navigation = navigation)
        Spacer(modifier = Modifier.size(60.dp))
        QuestionCircle(viewModel.questionData[numberOfQuestion])
        Spacer(modifier = Modifier.size(20.dp))
        AnswerList(viewModel = viewModel,question = viewModel.questionData[numberOfQuestion], numberOfQuestion = numberOfQuestion, navigation = navigation)
    }
}

@Composable
fun HomeIcon(navigation : NavController) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart

    ) {
        Icon(
            painter = painterResource(R.drawable.baseline_home_24),
            contentDescription = stringResource(R.string.backIcon),
            modifier = Modifier.clickable { navigation.navigate(Routes.HOME_SCREEN) }
        )
    }
}

private fun getResultCareerIndex(viewModel : CareerViewModel) : Int{
    val careers = viewModel.careerData
    var maxPoints = 0
    var resultCareerIndex = 0
    for(i in 0 until careers.size){
        if (careers[i].points > maxPoints){
            maxPoints = careers[i].points
            resultCareerIndex = i
        }
    }
    return resultCareerIndex
}


@Composable
fun QuestionCircle(question : Question) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(26.dp)
            .background(color = LightBlue, shape = CircleShape)
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = question.questionText,
            style = TextStyle(color = Color.White, fontSize = 20.sp, fontFamily = FontFamily.SansSerif),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(6.dp)
        )
    }
}

@Composable
fun AnswerCheckbox(answer : String, isSelected : Boolean, onCheckedChange : (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
            .border(width = 1.dp, color = DarkBlue),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = answer,
            style = TextStyle(color = DarkBlue, fontSize = 16.sp, fontFamily = FontFamily.SansSerif),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            contentAlignment = Alignment.CenterEnd
        ){
            AnswerRadioButton(isSelected, onCheckedChange)
        }
    }
}

private fun navigate(navigation: NavController, numberOfQuestion: Int, viewModel: CareerViewModel){
    if (numberOfQuestion <= viewModel.questionData.size-2) {
        navigation.navigate("${Routes.QUIZ_SCREEN}/${numberOfQuestion + 1}")
    }
    else{
        val resultCareerIndex = getResultCareerIndex(viewModel)
        navigation.navigate(Routes.getQuizResultPath(resultCareerIndex))
    }
}

@Composable
fun AnswerList(viewModel: CareerViewModel, question: Question, numberOfQuestion: Int, navigation: NavController) {
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var previousSelectedAnswer by remember { mutableStateOf<String?>(null) }
    LazyColumn {
        items(question.answers) { answer ->
            AnswerCheckbox(
                answer = answer.text,
                isSelected = selectedAnswer == answer.text,
                onCheckedChange = {
                    selectedAnswer = if (it) answer.text else null
                    addPointsToCareer(viewModel = viewModel, question = question, selectedAnswer = answer)
                    navigate(navigation, numberOfQuestion, viewModel)
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

fun addPointsToCareer(viewModel: CareerViewModel, question: Question, selectedAnswer : Answer){
    val careers = viewModel.careerData
    for (career in careers){
        if (career.keywords.contains(selectedAnswer.keyword)){
            career.points += question.points
        }
    }
}

@Composable
fun AnswerRadioButton(isSelected : Boolean, onCheckedChange : (Boolean) -> Unit) {
    RadioButton(
        selected = isSelected,
        onClick = { onCheckedChange(!isSelected) },
        colors = RadioButtonDefaults.colors(
            selectedColor = DarkBlue,
            unselectedColor = DarkBlue
        ),
        modifier = Modifier.size(12.dp)
    )
}