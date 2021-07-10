package com.mellagusty.hacigo_mobileapp.ui._kiddojournal.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mellagusty.hacigo_mobileapp.R


class JournalBottomFragment : BottomSheetDialogFragment() {

    var selectedColor = "#B40752"


    companion object{
        var noteId = -1
        fun newInstance(id:Int): JournalBottomFragment{
            val args = Bundle()
            val fragment = JournalBottomFragment()
            fragment.arguments = args
            noteId = id
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_journal_bottom, container, false)
    }


}