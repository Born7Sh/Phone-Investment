package com.example.stock.views.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.stock.R
import com.example.stock.util.EventObserver
import com.example.stock.databinding.FragmentLoginBinding
import com.example.stock.model.LoginViewModel

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        viewModel.id.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (binding.loginId.text.isNullOrEmpty()
                &&
                it.toString().isNotBlank()
            ) {
                binding.loginId.setText(it.toString())
            }
        })

        viewModel.pwd.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (binding.loginPwd.text.isNullOrEmpty()
                &&
                it.toString().isNotBlank()
            ) {
                binding.loginPwd.setText(it.toString())
            }
        })

        viewModel.loginBtnClick.observe(viewLifecycleOwner, EventObserver {
            when (it) {
                "200" -> loginSuccess()
                else -> Toast.makeText(activity, "실패", Toast.LENGTH_SHORT).show()
            }

        })

        viewModel.signUpBtnClick.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        })



    }

    private fun loginSuccess() {
        Toast.makeText(activity, "로그인 성공!", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_loginFragment_to_HomeFragment)
    }


}