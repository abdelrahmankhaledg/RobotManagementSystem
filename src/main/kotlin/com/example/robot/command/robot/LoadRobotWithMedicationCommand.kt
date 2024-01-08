package com.example.robot.command.robot

import com.example.robot.resource.robot.LoadRobotWithMedicationResource

data class LoadRobotWithMedicationCommand(
    val serialNumber: String,
    val medicationNames : List<String>
)
{
    constructor(loadRobotWithMedicationResource : LoadRobotWithMedicationResource) :
            this(loadRobotWithMedicationResource.serialNumber!!,
                loadRobotWithMedicationResource.medicationNames!!)
}