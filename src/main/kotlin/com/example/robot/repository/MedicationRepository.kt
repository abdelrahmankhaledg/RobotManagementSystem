package com.example.robot.repository

import com.example.robot.model.Medication
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MedicationRepository : CrudRepository<Medication, String>{
    @Query("SELECT SUM(M.weight) FROM Medication M WHERE M.name IN :medicationNames")
    fun findMedicationsWeight(medicationNames : List<String>) : Int
}