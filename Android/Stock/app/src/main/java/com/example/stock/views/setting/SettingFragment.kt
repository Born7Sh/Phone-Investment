package com.example.stock.views.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.stock.R
import com.example.stock.databinding.FragmentSettingBinding
import com.example.stock.model.MainViewModel


class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding

    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)
        binding.user = mainViewModel.userIam.value
        return binding.root
    }


}