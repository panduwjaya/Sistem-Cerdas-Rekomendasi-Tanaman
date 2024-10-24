package com.example.sistemcerdasrekomendasitanaman.ui.primary.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.sistemcerdasrekomendasitanaman.R
import com.example.sistemcerdasrekomendasitanaman.databinding.FragmentProfileBinding
import com.example.sistemcerdasrekomendasitanaman.ui.auth.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var imgUri: Uri

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        binding.tvChangeProfile.setOnClickListener {
            goToCamera()
        }

        binding.btnChangePassword.setOnClickListener {
            view.findNavController().navigate(R.id.action_navigation_profile_to_changePasswordFragment)
        }

        binding.btnHelp.setOnClickListener {
            view.findNavController().navigate(R.id.action_navigation_profile_to_helpFragment)
        }

        binding.btnAppinfo.setOnClickListener {
            view.findNavController().navigate(R.id.action_navigation_profile_to_appInfoFragment)
        }

        binding.btnLogout.setOnClickListener {
            showLogoutConfirmationDialog()
        }
    }

    private fun goToCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {intent ->
            activity?.packageManager?.let {
                intent?.resolveActivity(it).also {
                    startActivityForResult(intent,REQ_CAM)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CAM && resultCode == RESULT_OK){
            val imgBitmap = data?.extras?.get("data") as Bitmap
            uploadImgToFirebase(imgBitmap)
        }
    }

    private fun uploadImgToFirebase(imgBitMap: Bitmap) {
        val baos = ByteArrayOutputStream()
        val ref = FirebaseStorage.getInstance().reference.child("img_user/${FirebaseAuth.getInstance().currentUser?.email}")
        imgBitMap.compress(Bitmap.CompressFormat.JPEG,100,baos)

        val img = baos.toByteArray()
        ref.putBytes(img)
            .addOnCompleteListener{
                if (it.isSuccessful){
                    ref.downloadUrl.addOnCompleteListener { Task ->
                        Task.result.let { Uri ->
                            imgUri = Uri
                            binding.ivProfile.setImageBitmap(imgBitMap)
                        }
                    }
                }
            }
    }

    private fun showLogoutConfirmationDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_logout, null)
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setCancelable(false)

        val alertDialog = alertDialogBuilder.create()
        alertDialog.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        alertDialog.window?.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.custom_dialog_bg))
        alertDialog.show()


        val tvLogoutMessage = dialogView.findViewById<TextView>(R.id.tvLogoutMessage)
        val btnCancel = dialogView.findViewById<Button>(R.id.btnCancel)
        val btnConfirmLogout = dialogView.findViewById<Button>(R.id.btnConfirmLogout)

        btnCancel.setOnClickListener {
            alertDialog.dismiss()
        }

        btnConfirmLogout.setOnClickListener {
            performLogout()
            alertDialog.dismiss()
        }
    }

    private fun performLogout() {
        auth.signOut()
        val intent = Intent(context,LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        const val REQ_CAM = 100
    }
}
