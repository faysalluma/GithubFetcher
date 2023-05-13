package com.example.testmobile.data.database.dao

import androidx.room.*
import com.example.testmobile.data.network.bodies.results.Repository

@Dao
interface RepositoryDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repository: com.example.testmobile.data.database.entities.Repository)

    @Query("SELECT * FROM repository")
    fun getAllRepositories(): List<Repository>
}