package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.HashSet;

public class Distance {
    public static double GetDisance(LinearOpMode opMode, com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, org.firstinspires.ftc.robotcore.external.Telemetry telemetry, String SensorDirection)
    {
        // SensorDirection = rightDistanceSensor, leftDistanceSensor.

        HashSet<String> sensorNames = new HashSet<String>();
        sensorNames.add("rightDistanceSensor");
        sensorNames.add("leftDistanceSensor");

        DistanceSensor distanceSensor;

        if(sensorNames.contains(SensorDirection))
        {
            distanceSensor = hardwareMap.get(DistanceSensor.class, SensorDirection);
        } else {
            return 0;
        }


        return distanceSensor.getDistance(DistanceUnit.CM);
    }


}