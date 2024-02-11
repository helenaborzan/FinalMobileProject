package hr.ferit.helenaborzan.my_future_career.data

data class Question(
    var id : String = "",
    val questionText : String = "",
    val points : Int = 0,
    val answers : MutableList<Answer> = mutableListOf()
)
