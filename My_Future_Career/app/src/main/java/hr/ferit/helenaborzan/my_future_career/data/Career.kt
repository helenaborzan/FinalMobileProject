package hr.ferit.helenaborzan.my_future_career.data

data class Career(
    var id: String = "",
    val name : String = "",
    val description : String = "",
    val keywords : MutableList<String> = mutableListOf(),
    val image : String = "",
    var points : Int = 0
)
