package com.ber.exam2android

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ber.exam2android.databinding.FragmentInfoBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class InfoFragment: Fragment(R.layout.fragment_info) {

    private val api get() = Injector.characterApi
    private var _binding: FragmentInfoBinding?= null
    private val binding get() = _binding!!
    private lateinit var listener: Navigation

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as Navigation
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentInfoBinding.bind(view)
        val id = arguments?.getLong("id") ?: 1L

        binding.apply {
            api.getCharacterById(id)
                .subscribeOn(Schedulers.io())
                .map { it.first() }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess{
                    name.text = "Name ${it.name}"
                    id.text = "ID: ${it.character_id}"
                    status.text = "Status: ${it.status}"
                    species.text = "Species: ${it.species}"
                    gender.text = "Gender: ${it.gender}"
                    location.text = "Location: ${it.location}"
                }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    Toast.makeText(requireContext(),"Error", Toast.LENGTH_SHORT).show()
                }
                .subscribe()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}