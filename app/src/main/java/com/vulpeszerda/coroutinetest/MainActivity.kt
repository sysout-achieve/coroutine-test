package com.vulpeszerda.coroutinetest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

const val TAG = "TEST111"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        asyncExceptionOnSupervisorScopeTest3()
    }
}
