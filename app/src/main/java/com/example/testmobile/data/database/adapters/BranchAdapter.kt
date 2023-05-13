package com.example.testmobile.data.database.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testmobile.data.dtole.BranchDTO
import com.example.testmobile.databinding.FragmentBranchItemBinding

class BranchAdapter() : RecyclerView.Adapter<BranchAdapter.MainViewHolder>() {
    private var branchList = mutableListOf<BranchDTO>()

    fun setBranches(branchList: List<BranchDTO>) {
        this.branchList = branchList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentBranchItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val branch = branchList[position]
        holder.binding.name.text = branch.name
    }

    override fun getItemCount(): Int {
        return branchList.size
    }

    class MainViewHolder(val binding: FragmentBranchItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}