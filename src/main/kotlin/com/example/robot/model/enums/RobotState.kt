package com.example.robot.model.enums

enum class RobotState(val code : String ) {
    IDLE("IDLE"),
    LOADING("LOADING"),
    LOADED("LOADED"),
    DELIVERING("DELIVERING"),
    DELIVERED("DELIVERED"),
    RETURNING("RETURNING");

    override fun toString(): String {
        return code
    }

    companion object{
        fun getEnum(value: String?): RobotState? {
            if (value == null) {
                return null
            }
            for (v in RobotState.entries) {
                if (value.equals(v.code, ignoreCase = true)) {
                    return v
                }
            }
            return null
        }
    }

}