package com.programmer270487.filamentapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.programmer270487.filamentapp.data.RetrofitClient
import com.programmer270487.filamentapp.data.UserRepository
import com.programmer270487.filamentapp.databinding.FragmentBBinding
import com.programmer270487.filamentapp.views.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentB : Fragment() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var b: FragmentBBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentBBinding.inflate(layoutInflater, container, false)

        val userRepository = UserRepository(RetrofitClient.apiService)
        val viewModelFactory = UserViewModelFactory(userRepository)
        userViewModel = ViewModelProvider(this, viewModelFactory)[UserViewModel::class.java]

        userViewModel.user.observe(viewLifecycleOwner) {
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.Main) {
                    Glide.with(this@FragmentB)
                        .load(it.avatar)
                        .apply(RequestOptions.circleCropTransform())
                        .into(b.profileImage)
                    b.tvUsername.text = it.username
                    b.tvEmail.text = it.email
                    b.tvPhoneNumber.text = it.phoneNumber
                    b.tvDoB.text = it.dateOfBirth

                    b.tvCountry.text = it.address.country
                    b.tvCity.text = it.address.city
                    b.tvStreetName.text = it.address.streetName
                    b.tvStreetAddress.text = it.address.streetAddress
                    b.tvZipcode.text = it.address.zipCode

                    b.tvPlan.text = it.subscription.plan
                    b.tvStatus.text = it.subscription.status
                    b.tvPaymentMethod.text = it.subscription.paymentMethod
                    b.tvTerm.text = it.subscription.term
                }
            }
        }

        // Fetch users
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            userViewModel.fetchUser()
        }

        return b.root
    }
}