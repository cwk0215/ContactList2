package com.example.contactlist2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.contactlist2.databinding.FragmentFirstBinding
import com.example.contactlist2.databinding.FragmentProfileBinding
import java.net.URI


class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
        binding.ivPicture.setImageURI(it)
    }
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.action_profile).isVisible = false
        menu.findItem(R.id.action_settings).isVisible = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivPicture.setOnClickListener {
            getContent.launch("image/*")
        }
        preferences = view.context.getSharedPreferences(PROFILE_PREFERENCES, Context.MODE_PRIVATE)
        val name = preferences.getString(PROFILE_NAME, "")
        binding.editTextName.setText(name)
        binding.btnIG.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_ig)))
            if (intent.resolveActivity(context?.packageManager!!)!= null){
                startActivity(intent)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        with(preferences.edit()){
            val name = binding.editTextName.text.toString()
            putString(PROFILE_NAME, name)
            apply()
        }
    }

    companion object {
        const val PROFILE_NAME = "Name"
        const val PROFILE_PREFERENCES = "Preferences"
    }

}