package com.example.robot.model.enums.converters

import com.example.robot.model.enums.RobotState
import jakarta.persistence.AttributeConverter

class RobotStateConverter : AttributeConverter<RobotState, String> {
    override fun convertToDatabaseColumn(attribute: RobotState?): String? {
        return attribute?.code
    }

    override fun convertToEntityAttribute(databaseAttribute: String?): RobotState? {
        return RobotState.getEnum(databaseAttribute)
    }
}