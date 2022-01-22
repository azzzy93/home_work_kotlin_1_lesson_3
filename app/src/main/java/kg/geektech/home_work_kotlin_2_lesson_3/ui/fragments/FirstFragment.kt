package kg.geektech.home_work_kotlin_2_lesson_3.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import kg.geektech.home_work_kotlin_2_lesson_3.R
import kg.geektech.home_work_kotlin_2_lesson_3.databinding.FragmentFirstBinding

class FirstFragment : Fragment(), ImageAdapter.OnItemClickListener {

    private lateinit var binding: FragmentFirstBinding
    private lateinit var listImage: ArrayList<Int>
    private lateinit var listImageForSend: ArrayList<Int>
    private lateinit var adapter: ImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initListeners()
    }

    private fun initAdapter() {
        listImageForSend = arrayListOf()
        listImage = arrayListOf()
        listImage.add(R.drawable.img)
        listImage.add(R.drawable.img)
        listImage.add(R.drawable.img)
        listImage.add(R.drawable.img)
        listImage.add(R.drawable.img)
        listImage.add(R.drawable.img)
        listImage.add(R.drawable.img)
        listImage.add(R.drawable.img)
        listImage.add(R.drawable.img)
        listImage.add(R.drawable.img)
        listImage.add(R.drawable.img)
        listImage.add(R.drawable.img)
        listImage.add(R.drawable.img)
        listImage.add(R.drawable.img)
        listImage.add(R.drawable.img)
        listImage.add(R.drawable.img)
        listImage.add(R.drawable.img)
        listImage.add(R.drawable.img)
        listImage.add(R.drawable.img)
        listImage.add(R.drawable.img)
        listImage.add(R.drawable.img)
        listImage.add(R.drawable.img)
        listImage.add(R.drawable.img)
        listImage.add(R.drawable.img)
        listImage.add(R.drawable.img)
        adapter = ImageAdapter(listImage, this)
        binding.rvFirst.adapter = adapter
    }

    override fun onItemClick(position: Int) {
        listImageForSend.add(adapter.getImage(position))
        showOrHideView()
    }

    override fun onItemClickRemove(position: Int) {
        listImageForSend.remove(adapter.getImage(position))
        showOrHideView()
    }

    private fun showOrHideView() {
        val size = listImageForSend.size

        if (size > 0) {
            binding.containerVisibility.visibility = View.VISIBLE
            val text = when (size) {
                1 -> "Выбрана $size фотография"
                else -> "Выбрана $size фотографии"
            }
            binding.tvChoseImage2.text = text
        } else {
            binding.containerVisibility.visibility = View.GONE
        }
    }

    override fun onItemLongClick(position: Int) {
        Toast.makeText(context, "Удалена фотография с индексом: $position", Toast.LENGTH_SHORT).show()
        adapter.removeImage(position)
    }

    private fun initListeners() {
        binding.btnDone.setOnClickListener {
            val navHostFragment =
                activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            val navController = navHostFragment.navController

            val bundle = Bundle()
            bundle.putIntegerArrayList(FIRST_KEY, listImageForSend)
            navController.navigate(R.id.secondFragment, bundle)
        }
    }

    companion object {
        const val FIRST_KEY = "FIRST_KEY"
    }
}