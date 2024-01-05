package com.example.robot.repository

import com.example.robot.model.Medication
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MedicationRepository : CrudRepository<Medication, String>{
    @Query("SELECT SUM(M.WEIGHT) FROM MEDICATION M WHERE MEDICATION_NAME IN :medicationNames")
    fun findMedicationsWeight(medicationNames : List<String>) : Int
}