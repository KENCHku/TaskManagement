package kg.geeks.taskmanagement.ui.fragment.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kg.geeks.taskmanagement.data.model.car.Car
import kg.geeks.taskmanagement.utils.ext.showToast
import kg.geeks.taskmanagment.R
import kg.geeks.taskmanagment.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {



    

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = FirebaseFirestore.getInstance()

        binding.fabSave.setOnClickListener {
            onSave()
        }
    }

    private fun onSave() = with(binding) {
        val car = Car(
            name = etName.text.toString(),
            model = etModel.text.toString()
        )
        db.collection(
            FirebaseAuth.getInstance()
                .currentUser?.uid.toString()
        )
            .document()
            .set(car).addOnSuccessListener {
                etName.text?.clear()
                etModel.text?.clear()
                showToast("Success")
            }.addOnFailureListener {
                showToast("Error")
            }
    }
}