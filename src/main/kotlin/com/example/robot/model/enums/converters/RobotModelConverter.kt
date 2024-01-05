package com.example.robot.model.enums.converters

import com.example.robot.model.enums.RobotModel
import jakarta.persistence.AttributeConverter

class RobotModelConverter : AttributeConverter<RobotModel, String> {
    override fun convertToDatabaseColumn(attribute: RobotModel?): String? {
        return attribute?.code
    }

    override fun convertToEntityAttribute(databaseAttribute: String?): RobotModel? {
        return RobotModel.getEnum(databaseAttribute)
    }
}