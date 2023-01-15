package com.purnendu.timetable.ui


import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.purnendu.timetable.R
import com.purnendu.timetable.databinding.FragmentHomeScreenBinding
import com.purnendu.timetable.ui.adapter.ViewPagerAdapter


class HomeScreen : Fragment() {

    private val days = arrayOf(
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday"
    )

    private var _binding:FragmentHomeScreenBinding?=null
    private val binding get() = _binding!!
    private lateinit var viewPagerAdapter: ViewPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window: Window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.argb(255,77,0,52)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         _binding= FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.create.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreen_to_createScreen)
        }
        viewPagerAdapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        binding.viewPager.adapter=viewPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = days[position]
        }.attach()







    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}