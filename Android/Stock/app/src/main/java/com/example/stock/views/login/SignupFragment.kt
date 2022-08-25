package com.example.stock.views.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.stock.R
import com.example.stock.util.EventObserver
import com.example.stock.databinding.FragmentSignupBinding
import com.example.stock.model.SignupViewModel

class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private lateinit var viewModel: SignupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
        binding.viewModel = viewModel
        binding.signupPwdCheck
        viewModel.id.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (binding.signupId.text.isNullOrEmpty()
                &&
                it.toString().isNotBlank()
            ) {
                binding.signupId.setText(it.toString())
            }
        })
        viewModel.pwd.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (binding.signupPwd.text.isNullOrEmpty()
                &&
                it.toString().isNotBlank()
            ) {
                binding.signupPwd.setText(it.toString())
            }
        })
        viewModel.pwdCk.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (binding.signupPwdCheck.text.isNullOrEmpty()
                &&
                it.toString().isNotBlank()
            ) {
                binding.signupPwdCheck.setText(it.toString())
            }
        })
        viewModel.name.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (binding.signupName.text.isNullOrEmpty()
                &&
                it.toString().isNotBlank()
            ) {
                binding.signupName.setText(it.toString())
            }
        })
        viewModel.birth.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (binding.signupBirth.text.isNullOrEmpty()
                &&
                it.toString().isNotBlank()
            ) {
                binding.signupBirth.setText(it.toString())
            }
        })

        viewModel.loginBtnText.observe(viewLifecycleOwner, EventObserver {
            when (it) {
                "1" -> Toast.makeText(activity, "비밀번호 - check 불일치!", Toast.LENGTH_SHORT).show()
                "200" -> signUpSuccess()
                else -> Toast.makeText(activity, "실패", Toast.LENGTH_SHORT).show()
            }

        })


    }
    private fun signUpSuccess() {
        Toast.makeText(activity, "로그인 성공!", Toast.LENGTH_SHORT).show()
        findNavController().navigateUp()
    }


}