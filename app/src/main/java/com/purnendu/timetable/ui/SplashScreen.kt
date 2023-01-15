package com.purnendu.timetable.ui


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.purnendu.timetable.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashScreen : Fragment() {





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        lifecycleScope.launch {
            delay(2000)
            findNavController().navigate(R.id.action_splashScreen_to_homeScreen)
        }
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

}