package com.example.intercomtest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.intercomtest.databinding.ItemListBinding
import com.example.intercomtest.model.UniversityListResponse

class UniversityListAdapter(
    private var list: List<UniversityListResponse>,
    private var onClicked: (UniversityListResponse) -> Unit
) : Adapter<ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemBinding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ListViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bindView(list[position], onClicked)
    }

    fun update(list: List<UniversityListResponse>) {
        this.list = list
        notifyDataSetChanged()
    }

}

class ListViewHolder(
    private val binding: ItemListBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bindView(
        item: UniversityListResponse,
        onClicked: (UniversityListResponse) -> Unit,
    ) {
        with(binding) {
            uniName.text = item.name
            uniCountry.text = item.country
            uniState.text = item.stateProvince

            itemView.setOnClickListener { onClicked(item) }
        }
    }
}
