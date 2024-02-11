package hr.ferit.helenaborzan.my_future_career.data

import androidx.compose.ui.res.stringResource
import hr.ferit.helenaborzan.my_future_career.R

val articles = listOf<Article>(
    Article(title = "CAREERS FOR CREATIVE MINDS", iconResource = R.drawable.baseline_brush_24, filter = "creative"),
    Article(title = "CAREERS FOR PROBLEM SOLVERS", iconResource = R.drawable.baseline_psychology_alt_24, filter = "problem solving"),
    Article(title = "CAREERS IN PUBLIC SERVICE", iconResource = R.drawable.baseline_local_police_24, filter = "public service"),
    Article(title = "CAREERS FOR PEOPLE LOVERS", iconResource = R.drawable.baseline_people_24, filter = "peoplePerson"),
)