package com.goomer.ps.feature.menu.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.goomer.data.models.Menu
import com.goomer.ps.R
import com.goomer.ps.databinding.ItemMenuBinding

class MenuAdapter(
    private val list: List<Menu>,
    private val onClick: (Menu) -> Unit
): RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemMenuBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(menu: Menu) {
            binding.tvName.text = menu.name
            binding.tvDescription.text = menu.description
            binding.tvPrice.text = binding.root.context.getString(R.string.price, menu.price)

            binding.root.setOnClickListener {
                onClick(menu)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemMenuBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val getItem = list[position]
        holder.bind(getItem)
    }

    override fun getItemCount(): Int = list.size
}