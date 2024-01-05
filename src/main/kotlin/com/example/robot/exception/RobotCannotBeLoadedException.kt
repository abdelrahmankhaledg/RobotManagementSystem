package com.example.robot.exception

class RobotCannotBeLoadedException(
        message : String = "Robot is not idle or needs charging"
    ) : RuntimeException(message) {}