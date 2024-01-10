package com.example.robot.model.enums

enum class RobotModel(val code : String) {

    LIGHTWEIGHT("LIGHTWEIGHT"),
    MIDDLEWEIGHT("MIDDLEWEIGHT"),
    CRUISERWEIGHT("CRUISERWEIGHT"),
    HEAVYWEIGHT("HEAVYWEIGHT");

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