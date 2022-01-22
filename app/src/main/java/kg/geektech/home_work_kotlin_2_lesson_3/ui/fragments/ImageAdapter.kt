package kg.geektech.home_work_kotlin_2_lesson_3.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.home_work_kotlin_2_lesson_3.databinding.ListImageBinding

class ImageAdapter(
    private val images: ArrayList<Int>,
    private val listener: OnItemClickListener?
) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListImageBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(images[position])
    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun removeImage(position: Int) {
        images.removeAt(position)
        notifyItemRemoved(position)
    }

    fun getImage(position: Int): Int {
        return images[position]
    }

    inner class ViewHolder(private val binding: ListImageBinding) :
        RecyclerView.ViewHolder(binding.root),
        View.OnClickListener, View.OnLongClickListener {

        init {
            binding.root.setOnClickListener(this)
            binding.root.setOnLongClickListener(this)
        }

        fun onBind(image: Int) {
            binding.ivImage.setImageResource(image)
        }

        override fun onClick(v: View?) {
            if (binding.isChose.visibility == View.GONE) {
                binding.isChose.visibility = View.VISIBLE
                listener?.onItemClick(adapterPosition)
            } else {
                binding.isChose.visibility = View.GONE
                listener?.onItemClickRemove(adapterPosition)
            }
        }

        override fun onLongClick(v: View?): Boolean {
            listener?.onItemLongClick(adapterPosition)
            return true
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onItemClickRemove(position: Int)
        fun onItemLongClick(position: Int)
    }
}