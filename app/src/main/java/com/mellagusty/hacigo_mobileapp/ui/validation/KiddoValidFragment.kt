package com.mellagusty.hacigo_mobileapp.ui.validation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.data.auth.emailauth.UserEmail
import com.mellagusty.hacigo_mobileapp.databinding.FragmentKiddoValidBinding
import com.mellagusty.hacigo_mobileapp.databinding.FragmentMommyValidBinding
import com.mellagusty.hacigo_mobileapp.ui.MainActivity
import com.mellagusty.hacigo_mobileapp.ui.MainJournalActivity
import com.mellagusty.hacigo_mobileapp.utils.Constant


class KiddoValidFragment : Fragment() {

    private lateinit var _binding : FragmentKiddoValidBinding
    private val binding get() = _binding
    lateinit var userDetail: UserEmail

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentKiddoValidBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bundle = this.arguments
        val firstName = bundle?.getString(Constant.EXTRA_USER_DETAIL, " ")
        binding.tvQuest.text = "Berapa usia anak Ibu $firstName sekarang?"

        userDetail = UserEmail()

        binding.cvKidUndersixMonth.setOnClickListener {
            val intent = Intent(requireContext(),MainJournalActivity::class.java)
            intent.putExtra(Constant.PRIORITY,Constant.UNDER_SIX_MONTH)
            startActivity(intent)

        }
        binding.cvKidUpsixMonth.setOnClickListener {
            val intent = Intent(requireContext(),MainJournalActivity::class.java)
            intent.putExtra(Constant.PRIORITY,Constant.UP_SIX_MONTH)
            startActivity(intent)

        }
        super.onViewCreated(view, savedInstanceState)


    }

}