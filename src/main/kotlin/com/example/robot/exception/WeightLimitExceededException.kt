package com.example.robot.exception

class WeightLimitExceededException(
        message : String = """The total weight of the medications exceeds 
            |the maximum weight that the robot can carry.""".trimMargin()
    ) : RuntimeException(message) {
}