package com.example.robot.command

import com.example.robot.resource.LoadRobotWithMedicationResource

data class LoadRobotWithMedicationCommand(
    val serialNumber: String,
    val medicationNames : List<String>
)
{
    constructor(loadRobotWithMedicationResource : LoadRobotWithMedicationResource) :
            this(loadRobotWithMedicationResource.serialNumber!!,
                loadRobotWithMedicationResource.medicationNames!!)
}