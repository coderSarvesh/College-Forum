package com.psp.collegeforum.ui
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.psp.collegeforum.R
import com.psp.collegeforum.Retrofit.QuestionAdapter
import com.psp.collegeforum.Retrofit.RetrofitInstance
import com.psp.collegeforum.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.rvInMainFrag
import kotlinx.android.synthetic.main.fragment_main.view.*
import retrofit2.HttpException
import java.io.IOException

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private lateinit var questionAdapter: QuestionAdapter




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            //Navigation
        view.fabAddQuestion.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_questionFragment)
        }


        binding = FragmentMainBinding.inflate(layoutInflater)
        setupRV()

        lifecycleScope.launchWhenCreated {

            val response = try {
                RetrofitInstance.api.getQuestion()
            } catch (e: IOException) {
                Log.e(ContentValues.TAG, "ERROR")
                //binding.progressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(ContentValues.TAG, "HTTP ERROR")
                //binding.progressBar.isVisible = false
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null) {
                questionAdapter.question = response.body()!!
            } else {
                Log.e(ContentValues.TAG, "NO")
            }

        }
    }

    private fun setupRV()=binding.rvInMainFrag.apply {
        rvInMainFrag.layoutManager = LinearLayoutManager(activity)
        questionAdapter = QuestionAdapter()
        adapter = questionAdapter
    }
}

