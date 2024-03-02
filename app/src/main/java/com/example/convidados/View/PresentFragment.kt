package com.example.convidados.View

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.convidados.View.adapter.GuestAdapter
import com.example.convidados.View.listener.OnGuestListener
import com.example.convidados.ViewModel.GuestViewModel
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.databinding.FragmentPresentBinding

class PresentFragment : Fragment() {

    private var _binding: FragmentPresentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GuestViewModel
    private val adapter = GuestAdapter()

    override fun onCreateView( inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle? ): View {
        viewModel = ViewModelProvider(this).get(GuestViewModel::class.java)
        _binding = FragmentPresentBinding.inflate(inflater, container, false)

        binding.recyclerGuests.layoutManager = LinearLayoutManager(context)
        binding.recyclerGuests.adapter = adapter

        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()

                bundle.putInt(DataBaseConstants.GUEST.ID, id)
                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getPresent()
            }
        }
        adapter.attachListener(listener)
        observer()

        return binding.root
    }
    override fun onResume() {
        super.onResume()
        viewModel.getPresent()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observer(){
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.UpdatedGuests(it)
        }
    }
}