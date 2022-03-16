package com.psp.collegeforum.Retrofit

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class Mainvm() : ViewModel() {


    fun getQuestion()
    {
        var questionAdapter :QuestionAdapter
        viewModelScope.launch {

            var questionAdapter : QuestionAdapter = QuestionAdapter()
            val response = try {
                RetrofitInstance.api.getQuestion()
            } catch (e: IOException) {
                Log.e(ContentValues.TAG, "ERROR")
                return@launch
            } catch (e: HttpException) {
                Log.e(ContentValues.TAG, "HTTP ERROR")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                questionAdapter.question = response.body()!!
                Log.d("Response",response.body().toString())
            } else {
                Log.e(ContentValues.TAG, "NO")
            }

        }
    }
}