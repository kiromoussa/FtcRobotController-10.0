package org.firstinspires.ftc.teamcode;
public class robotDirection {
    double frontLeftPower;
    double frontRightPower;
    double backLeftPower;
    double backRightPower;
    String direction;

    robotDirection(double frontLeft, double frontRight, double backLeft, double backRight, String directionName) {
        this.frontLeftPower = frontLeft;
        this.frontRightPower = frontRight;
        this.backLeftPower = backLeft;
        this.backRightPower = backRight;
        this.direction = directionName;
    }

}
