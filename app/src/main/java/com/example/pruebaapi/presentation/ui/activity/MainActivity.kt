package com.example.pruebaapi.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebaapi.R
import com.example.pruebaapi.data.network.APIService
import com.example.pruebaapi.databinding.ActivityMainBinding
import com.example.pruebaapi.presentation.ui.adapter.DogAdapter
import com.example.pruebaapi.presentation.ui.fragment.dialog.DialogImgBreedFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DogAdapter
    private val dogList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        getBreedsList()

    }

    private fun initRecyclerView() {

        adapter = DogAdapter(dogList) { dogList, viewId: Int -> breedsClicked(dogList, viewId) }
        binding.rvDogs.layoutManager = LinearLayoutManager(this)
        binding.rvDogs.adapter = adapter


    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getBreedsList() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getDogsByBreeds("breeds/list")
            val puppies = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val message: List<String> = puppies?.image ?: emptyList()
                    dogList.clear()
                    dogList.addAll(message)
                    adapter.notifyDataSetChanged()
                } else {
                    showError()
                }

            }
        }
    }

    private fun getImageBreed(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call =
                getRetrofit().create(APIService::class.java)
                    .getBreedImg("breed/$query/images/random")
            val imgbreed = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val img: String = imgbreed?.image ?: ""
                    // todo se llama al dialog fragment enviando la imagen que se recupera desde la api
                    showDialog(img)
                } else {
                    showError()
                }
            }
        }
    }


    private fun showError() {
        Toast.makeText(this, "ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    private fun showDialog(img: String) {
        val dialog = DialogImgBreedFragment.newInstance(img)
        dialog.show(supportFragmentManager, "showImageBreed")
    }

    private fun breedsClicked(breed: String, viewId: Int) {
        when (viewId) {
            R.id.tvDogs -> {
                getImageBreed(breed)
            }

            R.id.cardbreed -> {
                getImageBreed(breed)
            }
        }
    }

}