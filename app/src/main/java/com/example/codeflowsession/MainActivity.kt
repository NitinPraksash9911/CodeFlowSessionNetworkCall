package com.example.codeflowsession

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.codeflowsession.databinding.ActivityMainBinding
import com.example.codeflowsession.localdb.LocalUser
import com.example.codeflowsession.localdb.UserDatabase
import com.example.codeflowsession.network.RetrofitFactory
import com.example.codeflowsession.network.UserAdapter
import com.example.codeflowsession.network.UserData.User
import com.example.codeflowsession.network.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val TAG = "NetworkCall"
    private lateinit var binding: ActivityMainBinding
    private lateinit var userRepository: UserRepository

    lateinit var db: UserDatabase
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        networkCall()
//        fetchUserData()

        /************************/


        db = getRoomDbInstance(this)

        //insert
        binding.saveBtn.setOnClickListener {

            val name = binding.nameEt.text.toString()
            val email = binding.emailEt.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty()) {
                saveData(name, email)
            }

        }

        //read
        binding.readBtn.setOnClickListener {

            getUser(1)

        }

    }

    private fun saveData(name: String, email: String) {

        val user = LocalUser(name = name, email = email)

        lifecycleScope.launch(Dispatchers.IO) {

            db.getUserDao().insert(user = user)
        }

    }

    private fun getUser(id: Int) {

        lifecycleScope.launch(Dispatchers.IO) {

            val user = db.getUserDao().getUser(id)

            withContext(Dispatchers.Main) {
                binding.nameTv.text = user.name
                binding.emailTv.text = user.email
            }
        }

    }

    private fun getRoomDbInstance(context: Context) = UserDatabase.getInstance(context)

    /****************************Below is Network call functions*/

    fun networkCall() {
        userRepository = UserRepository(RetrofitFactory.userApiService)

        userAdapter = UserAdapter()

        binding.userRv.adapter = userAdapter
    }

    private fun fetchUserData() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = userRepository.fetchUsers(1)
                if (response.isSuccessful) {

                    Log.d(TAG, "fetchUserData success: ${response.body()}")
                    withContext(Dispatchers.Main) {
                        setUserData(response.body()!!.user)
                    }

                } else {
                    Log.d(TAG, "fetchUserData fail: ${response.message()}")
                }

            } catch (e: Exception) {
                Log.d(TAG, "fetchUserData error: ${e.localizedMessage ?: "Unexpected error"}")
            }
        }
    }

    private fun setUserData(users: List<User>) {
        userAdapter.submitList(users)
    }

}

