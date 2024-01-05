package com.example.robot.model.enums

enum class RobotModel(val code : String) {
    LIGHTWEIGHT("LIGHT_WEIGHT"),
    MIDDLEWEIGHT("MIDDLE_WEIGHT"),
    CRUISERWEIGHT("CRUISER_WEIGHT"),
    HEAVYWEIGHT("HEAVY_WEIGHT");


    override fun toString(): String {
        return code
    }


    companion object {
        fun getEnum(value: String?): RobotModel? {
            if (value == null) {
                return null
            }
            for (v in entries) {
                if (value.equals(v.code, ignoreCase = true)) {
                    return v
                }
            }
            return null
        }
    }
}