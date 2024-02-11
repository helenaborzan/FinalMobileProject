package hr.ferit.helenaborzan.my_future_career.data

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class CareerViewModel: ViewModel() {
    private val db = Firebase.firestore
    val careerData = mutableStateListOf<Career>()
    val questionData = mutableStateListOf<Question>()

    init{
        fetchDatabaseData()
    }

    private fun fetchDatabaseData(){
        db.collection("careers")
            .get()
            .addOnSuccessListener { result ->
                for (data in result.documents){
                    val career = data.toObject(Career::class.java)
                    if (career != null){
                        career.id = data.id
                        careerData.add(career)
                    }
                }
            }
        db.collection("questions")
            .get()
            .addOnSuccessListener {result ->
                for (data in result.documents){
                    val question = data.toObject(Question::class.java)
                    if (question != null){
                        question.id = data.id
                        questionData.add(question)
                }
            }
            }
    }
}