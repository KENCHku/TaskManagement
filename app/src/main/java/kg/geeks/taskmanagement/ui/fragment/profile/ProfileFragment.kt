package kg.geeks.taskmanagement.ui.fragment.profile

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import kg.geeks.taskmanagement.data.local.pref.Pref
import kg.geeks.taskmanagement.ui.fragment.auth.AuthFragment
import kg.geeks.taskmanagement.utils.ext.loadImage
import kg.geeks.taskmanagement.utils.ext.showToast
import kg.geeks.taskmanagment.R
import kg.geeks.taskmanagment.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var pref: Pref
    //private val auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = Pref(requireContext())

        setListeners()

        binding.etName.setText(pref.getUserName())
        binding.ivUserImage.loadImage(pref.getUserImage().toString())
    }

    private fun setListeners() {
        binding.btnSave.setOnClickListener {
            pref.saveUserName(binding.etName.text.toString().trim())
            showToast("Hello ${binding.etName.text.toString()}")
        }

        binding.ivSetImage.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Add photo")
                .setMessage("Are you sure you want to add photo?")
                .setPositiveButton("Add") { _, _ ->
                    openGallery()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        binding.ivLogout.setOnClickListener {

            AlertDialog.Builder(requireContext())
                .setTitle("Sign Out")
                .setMessage("Are you sure you want to sign out?")
                .setPositiveButton("Exit") { _, _ ->
                    FirebaseAuth.getInstance().signOut()
                    findNavController().navigate(R.id.action_navigation_profile_to_authFragment)
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }

    private fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.putExtra(
            Intent.EXTRA_MIME_TYPES,
            arrayOf("image/png", "image/jpeg", "image/gif", "video/mp4")
        )
        intent.action = Intent.ACTION_GET_CONTENT
        launcher.launch(intent)

    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val selectedUri = result.data?.data
                binding.ivUserImage.loadImage(selectedUri.toString())
                pref.saveUserImage(selectedUri.toString())
            }
        }
}