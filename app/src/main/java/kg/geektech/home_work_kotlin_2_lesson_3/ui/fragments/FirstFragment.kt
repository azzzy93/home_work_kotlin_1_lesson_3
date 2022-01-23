package kg.geektech.home_work_kotlin_2_lesson_3.ui.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import kg.geektech.home_work_kotlin_2_lesson_3.R
import kg.geektech.home_work_kotlin_2_lesson_3.databinding.FragmentFirstBinding

class FirstFragment : Fragment(), ImageAdapter.OnItemClickListener {

    private lateinit var binding: FragmentFirstBinding
    private var listImage: ArrayList<Uri> = arrayListOf()
    private var listImageForSend: ArrayList<Uri> = arrayListOf()
    private lateinit var adapter: ImageAdapter
    private val permReqLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                openGalleryForImages()
            } else {
                Toast.makeText(requireContext(), getString(R.string.error), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    private val openGalleryLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK && it.data != null) {
                listImageForSend.clear()
                listImage.clear()

                val size = it.data?.clipData?.itemCount

                if (size != null) {
                    for (i in 0 until size) {
                        it.data?.clipData?.getItemAt(i)?.uri?.let { it1 -> listImage.add(it1) }
                    }
                } else {
                    it.data?.data?.let { it1 -> listImage.add(it1) }
                }
                initAdapter()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        permissionReq()
        initListeners()
    }

    private fun permissionReq() {
        permReqLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private fun openGalleryForImages() {
        val intent = Intent()
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        openGalleryLauncher.launch(intent)
    }

    private fun initAdapter() {
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

    override fun onItemLongClick(position: Int) {
        Toast.makeText(context, "Удалена фотография с индексом: $position", Toast.LENGTH_SHORT)
            .show()
        adapter.removeImage(position)
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

    private fun initListeners() {
        binding.btnDone.setOnClickListener {
            val navHostFragment =
                activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            val navController = navHostFragment.navController


            val listForBundle: ArrayList<String> = arrayListOf()
            listImageForSend.forEach {
                listForBundle.add(it.toString())
            }

            val bundle = Bundle()
            bundle.putStringArrayList(FIRST_KEY, listForBundle)
            navController.navigate(R.id.secondFragment, bundle)
        }
    }

    companion object {
        const val FIRST_KEY = "FIRST_KEY"
    }
}