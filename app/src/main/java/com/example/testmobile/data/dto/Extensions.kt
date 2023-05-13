package com.example.testmobile.data.dto

// fun RepositoryDTO.asEntity() = com.example.testmobile.data.database.entities.Repository(id.toLong(), full_name, description, languages_url, stargazers_url, branches_url, contributors_url)
fun RepositoryDTO.asBody() = com.example.testmobile.data.network.bodies.results.Repository(id, full_name, description, languages_url, stargazers_url, branches_url, contributors_url)