package com.example.robot.query
import com.example.robot.resource.GetRobotLoadedMedicationsResource

class GetRobotLoadedMedicationsQuery(
    val serialNumber: String
) {
    constructor(getRobotLoadedMedicationsResource: GetRobotLoadedMedicationsResource) : this(
        serialNumber = getRobotLoadedMedicationsResource.serialNumber!!
    )
}