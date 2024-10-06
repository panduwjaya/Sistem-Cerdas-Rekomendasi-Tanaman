package com.example.sistemcerdasrekomendasitanaman.ui.primary.other

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sistemcerdasrekomendasitanaman.databinding.FragmentChangePasswordBinding
import com.example.sistemcerdasrekomendasitanaman.ui.auth.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth


class ChangePasswordFragment : Fragment() {

    private var _binding: FragmentChangePasswordBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChangePasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnChangePassword.setOnClickListener {
            changePassword()
        }
    }

    private fun changePassword() {
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        binding.btnChangePassword.setOnClickListener {

            val newPass = binding.changeEdPassword.text.toString()
            val passConfirm = binding.confirmEdChange.text.toString()

            if (newPass.isEmpty()){
                binding.changeEdPassword.error = "Password Tidak Boleh Kosong"
                binding.changeEdPassword.requestFocus()
                return@setOnClickListener
            }

            if (passConfirm.isEmpty()){
                binding.changeEdPassword.error = "Ulangi Password Baru"
                binding.changeEdPassword.requestFocus()
                return@setOnClickListener
            }

            if (newPass.length < 6){
                binding.changeEdPassword.error = "Harus Lebih 6 karakter"
                binding.changeEdPassword.requestFocus()
                return@setOnClickListener
            }

            if (passConfirm.length < 6){
                binding.confirmEdChange.error = "Password Tidak Sama"
                binding.confirmEdChange.requestFocus()
                return@setOnClickListener
            }

            if (newPass != passConfirm){
                binding.confirmEdChange.error = "Password Tidak Sama"
                binding.confirmEdChange.requestFocus()
                return@setOnClickListener
            }

            user?.let{
                user.updatePassword(newPass).addOnCompleteListener{
                    if (it.isSuccessful){
                        Toast.makeText(activity,"Password Berhasil Update", Toast.LENGTH_SHORT).show()
                        logOut()
                    } else {
                        Toast.makeText(activity,"Password Gagal Update", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun logOut(){
        auth.signOut()
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
}