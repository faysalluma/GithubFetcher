package com.example.testmobile.data.network.adapters

import com.example.testmobile.data.dto.RepositoryDTO
import com.example.testmobile.data.network.bodies.results.Repository


fun Repository.asDto() = RepositoryDTO(id, full_name, description,  languages_url, stargazers_url, branches_url, contributors_url)