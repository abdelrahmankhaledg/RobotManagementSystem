package com.example.robot.model

import com.example.robot.model.enums.RobotModel
import com.example.robot.model.enums.converters.RobotModelConverter
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate

@Entity
@Table(name = "ROBOT")
@DynamicUpdate(value = true)
data class Robot(
    @Id
    @Column(name = "SERIAL_NUMBER")
    val serialNumber : String,

    @Column(name = "ROBOT_MODEL")
    @Convert(converter = RobotModelConverter::class)
    val robotModel : RobotModel,

    @Column(name = "WEIGHT_LIMIT")
    val weightLimit : Int,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "SERIAL_NUMBER", referencedColumnName = "SERIAL_NUMBER")
    val robotDynamicState : RobotDynamicState
)
{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Robot

        return serialNumber == other.serialNumber
    }

    override fun hashCode(): Int {
        return serialNumber.hashCode()
    }

    override fun toString(): String {
        return "Robot(serialNumber='$serialNumber', robotModel=$robotModel, weightLimit=$weightLimit, robotDynamicState=$robotDynamicState)"
    }

}