package com.example.testmobile.data.database.adapters

import com.example.testmobile.data.dto.RepositoryDTO

interface RepositoryAdapterListener {
    fun onRepositorySelected(repository: RepositoryDTO)
}