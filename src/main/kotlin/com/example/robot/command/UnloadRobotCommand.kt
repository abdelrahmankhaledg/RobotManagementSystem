package com.example.robot.command

import com.example.robot.resource.UnloadRobotResource

class UnloadRobotCommand(
    val serialNumber: String
) {
    constructor(unloadRobotResource: UnloadRobotResource) : this(
        serialNumber = unloadRobotResource.serialNumber!!
    )
}