package kg.geektech.home_work_kotlin_2_lesson_3.ui.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import kg.geektech.home_work_kotlin_2_lesson_3.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initListeners()
    }

    private fun initAdapter() {
        val listString = arguments?.getStringArrayList(FirstFragment.FIRST_KEY)
        val list: ArrayList<Uri> = arrayListOf()
        listString?.forEach {
            list.add(it.toUri())
        }
        val adapter = ImageAdapter(list, null)
        binding.rvSecond.adapter = adapter
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }
}