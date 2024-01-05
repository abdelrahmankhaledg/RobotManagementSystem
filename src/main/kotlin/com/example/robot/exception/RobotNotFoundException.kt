package com.example.robot.exception


class RobotNotFoundException(
        message : String = "Robot Not Found"
    ) : RuntimeException(message) {}