package com.ber.exam2android

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ber.exam2android.databinding.FragmentMainBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainFragment: Fragment(R.layout.fragment_main) {
    private val characterApi get() = Injector.characterApi
    private val binding get() = _binding!!
    private var _binding : FragmentMainBinding? = null
    private lateinit var adapter: Adapter
    private lateinit var listener: Navigation

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as Navigation
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMainBinding.bind(view)

        binding.apply {
            val layoutManager = LinearLayoutManager(requireContext())
            recycler.layoutManager = layoutManager
            adapter = Adapter {
                listener.onItemClicked(it)
            }
            recycler.adapter = adapter
            recycler.addItemDecoration(DividerItemDecoration(requireContext(),
                RecyclerView.VERTICAL))
        }
        getAll()
        refreshApp()
    }
    private fun getAll() {
    characterApi.getRepositories()
            .subscribeOn(Schedulers.io())
        .map {
            val listChar = mutableListOf<Character>()
            it.results.forEach{
                val character = Character(
                    character_id = it.character_id,
                    name = it.name,
                    status = it.status,
                    species = it.species,
                    gender = it.gender,
                    image = it.image,
                    date_created = it.date_created
                )
                listChar.add(character)
            }
            listChar.toList()
        }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                adapter.setData(it)
            }
            .doOnError {
                Toast.makeText(requireContext(), "error", Toast.LENGTH_LONG).show()
            }
        .doFinally{
            binding.swipeToRefresh.isRefreshing = false
            Log.e("TAG", "refresh")
        }
            .subscribe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun refreshApp() {
        getAll()
        binding.swipeToRefresh.setOnRefreshListener {
            Toast.makeText(requireContext(), "Page is refreshed", Toast.LENGTH_LONG).show()
            binding.swipeToRefresh.isRefreshing = false
        }
    }
}
