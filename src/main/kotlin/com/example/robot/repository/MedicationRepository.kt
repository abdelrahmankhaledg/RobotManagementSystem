package com.example.robot.repository

import com.example.robot.model.Medication
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MedicationRepository : CrudRepository<Medication, String>{
}