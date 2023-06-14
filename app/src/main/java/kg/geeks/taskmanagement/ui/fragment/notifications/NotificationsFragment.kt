package kg.geeks.taskmanagement.ui.fragment.notifications

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kg.geeks.taskmanagement.data.model.car.Car
import kg.geeks.taskmanagement.ui.fragment.notifications.adapter.CarAdapter
import kg.geeks.taskmanagement.utils.ext.showToast
import kg.geeks.taskmanagment.R
import kg.geeks.taskmanagment.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private lateinit var binding: FragmentNotificationsBinding
    private val adapter = CarAdapter()
    private lateinit var db : FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = FirebaseFirestore.getInstance()
        binding.recyclerViewNotifications.adapter = adapter
        db.collection(FirebaseAuth.getInstance().currentUser?.uid.toString()).get()
            .addOnSuccessListener {
                val cars : List<Car> = it.toObjects(Car::class.java)
                adapter.addCars(cars)
            }.addOnFailureListener {
                showToast(it.message.toString())
            }

    }
}