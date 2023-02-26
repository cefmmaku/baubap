package com.carlosFrias.baubapTest.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.carlosFrias.baubapTest.R
import com.carlosFrias.baubapTest.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val loginViewModel by viewModels<LoginViewModel>()

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        setListeners()
    }

    private fun initObservers() {
        loginViewModel.userInfo.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { userInfo ->
                if (userInfo.isRegistered) {
                    Toast.makeText(
                        context,
                        getString(R.string.login_accepted_parameters, userInfo.name),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        R.string.login_not_accepted_parameters,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun setListeners() {
        binding.apply {
            forgottenPasswordLabel.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_forgottenPasswordFragment)
            }

            loginButton.setOnClickListener {
                if (areFieldsEmpty()) {
                    Toast.makeText(context, R.string.login_empty_parameters, Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }

                loginViewModel.validateCredentials(
                    userName = binding.loginUsername.text.toString(),
                    password = binding.loginPassword.text.toString()
                )
            }
        }
    }

    private fun areFieldsEmpty(): Boolean {
        return binding.loginUsername.text.isEmpty() || binding.loginPassword.text.isEmpty()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}