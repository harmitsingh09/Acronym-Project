package com.acronym.app.modules.acronym

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.acronym.app.R
import com.acronym.app.databinding.ItemAbbreviationBinding
import com.acronym.app.model.LfsItem

class AcronymAdapter (private val context: Context) :
    RecyclerView.Adapter<AcronymAdapter.ZipCodeViewHolder>() {


    private val differCallback = object : DiffUtil.ItemCallback<LfsItem>() {
        override fun areItemsTheSame(
            oldItem: LfsItem,
            newItem: LfsItem
        ): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: LfsItem,
            newItem: LfsItem
        ): Boolean {
            return oldItem == newItem
        }
    }


    val differ = AsyncListDiffer(this, differCallback)

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ZipCodeViewHolder, position: Int) {
        val binding = holder.binding
        val listItem: LfsItem = differ.currentList[position]

        binding.abbreviation = listItem.lf
        binding.since = "since " + listItem.since.toString()

        binding.separator = position != differ.currentList.size - 1

        binding.parentLayout.setOnClickListener {

        }
    }

    class ZipCodeViewHolder internal constructor(view: ItemAbbreviationBinding) :
        RecyclerView.ViewHolder(view.root) {
        val binding: ItemAbbreviationBinding = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZipCodeViewHolder {
        val binding: ItemAbbreviationBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_abbreviation,
            parent,
            false
        )
        return ZipCodeViewHolder(binding)
    }

}