package com.example.testmobile.data.network.adapters

import com.example.testmobile.data.dto.ContributorDTO
import com.example.testmobile.data.dto.RepositoryDTO
import com.example.testmobile.data.dtole.BranchDTO
import com.example.testmobile.data.network.bodies.results.Branch
import com.example.testmobile.data.network.bodies.results.Contributor
import com.example.testmobile.data.network.bodies.results.Repository

fun Repository.asDto() = RepositoryDTO(id, full_name, description,  languages_url, stargazers_url, branches_url, contributors_url)
fun Branch.asDto() = BranchDTO(name)
fun Contributor.asDto() = ContributorDTO(login, avatar_url)