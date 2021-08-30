package uz.androdev.zikr.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.androdev.zikr.databinding.ItemZikrBinding
import uz.androdev.zikr.model.Zikr

class ZikrAdapter: ListAdapter<Zikr, ZikrAdapter.ViewHolder>(Util()) {

    var onComplete: ((Zikr) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemZikrBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(val binding: ItemZikrBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(zikr: Zikr) {
            binding.zikrArabic.text = zikr.zikrArabic
            binding.zikrUzbek.text = zikr.zikrUzbek

            binding.counter.text = "${zikr.count}"

            binding.root.setOnClickListener {
                if(zikr.count == 1){
                    onComplete?.invoke(zikr)
                } else {
                    zikr.count--
                    notifyItemChanged(adapterPosition)
                }
            }
        }
    }

    class Util: DiffUtil.ItemCallback<Zikr>(){
        override fun areItemsTheSame(oldItem: Zikr, newItem: Zikr): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Zikr, newItem: Zikr): Boolean {
            return oldItem == newItem
        }
    }
}