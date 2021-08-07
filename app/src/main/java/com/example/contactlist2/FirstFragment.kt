package com.example.contactlist2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.navigation.fragment.findNavController
import com.example.contactlist2.MainActivity.Companion.contactList
import com.example.contactlist2.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textviewFirst.text = contactList.size.toString() + " data recorded"
        val strArray = arrayOf("Item 1","Item 2")
        //val adapter = Adaptor<String>(this, android.R.layout.simple_list_item_1, strArray)
       // val listViewContact: ListView = findViewById(R.id.listViewContact)
        //listViewContact.adapter = adapter
        //listViewContact.onItemClickListener = AdapterView.OnItemClickListener{name, phone}
        binding.listViewContact
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}