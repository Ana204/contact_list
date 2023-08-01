package com.picpay.desafio.android.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainActivityViewModel>()
    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerView
        val progressBar = binding.userListProgressBar

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        progressBar.visibility = View.VISIBLE

        Log.e("MainActivity ", "OnCreate")

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.listUser
                    .flowWithLifecycle(lifecycle).collect{
                        Log.i("MAIN ACTIVITY ", "State -> $it")

                        progressBar.visibility = View.GONE
                        adapter.users = it.listUser
                    }
            }

            //val listUser = viewModel.listUser()

        }

       //val userLocal =  viewModel.listUserLocal()

    }

    override fun onStart() {
        super.onStart()
        Log.e("MainActivity ", "onStart")
    }
    override fun onRestart() {
        super.onRestart()
        Log.e("MainActivity ", "onRestart")
    }
    override fun onPause() {
        super.onPause()
        Log.e("MainActivity ", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("MainActivity ", "onPause")
    }
    override fun onDestroy() {
        super.onDestroy()

        Log.e("MainActivity ", "onDestroy")
    }
}
