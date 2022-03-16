package com.psp.collegeforum.ui
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.psp.collegeforum.R
import com.psp.collegeforum.Retrofit.Mainvm
import com.psp.collegeforum.Retrofit.QuestionAdapter
import com.psp.collegeforum.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.view.*


class MainFragment : Fragment(R.layout.fragment_main) {

    val viewModel = Mainvm()
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
        viewModel.getQuestion()
        /*
        lifecycleScope.launchWhenCreated {

            val response = try {
                RetrofitInstance.api.getQuestion()
            } catch (e: IOException) {
                Log.e(ContentValues.TAG, "ERROR")
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(ContentValues.TAG, "HTTP ERROR")
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null) {
                questionAdapter.question = response.body()!!
            } else {
                Log.e(ContentValues.TAG, "NO")
            }

        }
        */
    }

    private fun setupRV()=binding.rvInMainFrag.apply {
         questionAdapter = QuestionAdapter()
        val myRecycler = view?.findViewById<RecyclerView>(R.id.rvInMainFrag)
        myRecycler?.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        myRecycler?.adapter = questionAdapter


    }
}