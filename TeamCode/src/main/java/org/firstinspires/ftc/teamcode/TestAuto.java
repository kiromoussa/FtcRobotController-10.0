package org.firstinspires.ftc.teamcode;

//Importing

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.ElapsedTime;

//##################################
//#                                #
//# Made by Coder Ricky Adams (14) #
//#      for the 2023-2024         #
//#       Centerstage FTC,         #
//#     with teammates Michael     #
//#    Shenback and Max Corum      #
//# (and a little help from Ethan) #
//#  as engineers. Use this as a   #
//#  basis for any code you need   #
//#     for future events.         #
//#                                #
//#  -Team Dinomite8472 10/7/2023  #
//#                                #
//##################################


//Note to used Android studio to show errors in code, and the robot
//control console (ip address to connect directly to control hub,
//in my case http://192.168.43.1:8080/?page=connection.html&pop=true)
//to export the code into the mini i-pad (Driver hub)


//Replace ' name = "OpMode3" ' with the name you want
//to display on control hub, and ' class OpMode3 ' with
//the name of the file.
@TeleOp(name = "TestAuto00")
public class TestAuto extends LinearOpMode {

    private String action;
    private final int waitTime = 5;

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor Drone = null;
    private CRServo Drop1 = null;
    private CRServo Drop2 = null;
    private CRServo Drop3 = null;
    private CRServo Drop4 = null;
    private DcMotor Lightning = null;
    public ServoController ControlHub_ServoController;
    public ServoController ExpansionHub2_ServoController;


    @Override
    public void runOpMode() {
        boolean Running = true;
        // Report that op mode has been initialized
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables

        //***VERY IMPORTANT**
        //Replace the device name (ex frontLeft) with the NAME OF THE
        //MOTORS DEFINED IN THE DRIVER HUB
        Drone = hardwareMap.get(DcMotor.class, "Drone");
        Lightning = hardwareMap.get(DcMotor.class, "Lightning");
        ControlHub_ServoController = hardwareMap.get(ServoController.class, "Control Hub");
        ExpansionHub2_ServoController = hardwareMap.get(ServoController.class, "Expansion Hub 2");

        // Set the wheel directions
        Drone.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        boolean Start = false;
        boolean DriveTest = false;
        boolean CameraTest = false;
        boolean DistanceTest = false;
        boolean testMode = true;
        boolean armTest = true;
        boolean turnTest = false;

        while (Start = false) {
            if (gamepad1.dpad_down)
            {
                Start = true;
            }

            if (gamepad1.x)
            {
                DriveTest = true;
            }

            if (gamepad1.b)
            {
                CameraTest = true;
            }

            if (gamepad1.y)
            {
                DistanceTest = true;
            }

            if (gamepad1.a)
            {
                armTest = true;
            }

            telemetry.addData("Running == ", Running);
            telemetry.addData("Drive Test == ", DriveTest);
            telemetry.addData("Camera Test == ", CameraTest);
            telemetry.addData("Distance Test == ", DistanceTest);
            telemetry.addData("Arm Test == ", armTest);
            telemetry.addData("Turn Test == ", turnTest);

            telemetry.update();
        }
        waitForStart();
        runtime.reset();

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////// Driving starts here//////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        double LeftSensorValue;
        double RightSensorValue;
        if(DistanceTest == true) {
            while (opModeIsActive()) {
                LeftSensorValue = Distance.GetDisance(this, hardwareMap, telemetry, "leftDistanceSensor");
                RightSensorValue = Distance.GetDisance(this, hardwareMap, telemetry, "rightDistanceSensor");

                telemetry.addData("Left Distance: ", LeftSensorValue);
                telemetry.addData("Right Distance: ", RightSensorValue);
                telemetry.update();
            }
        }

        if(DriveTest == true) {
            // 16in = 1000


            // Drive to pixel
            Functions.drive(this, hardwareMap, telemetry, 25, 25, 0.5, 25, 25, testMode);

            Functions.drive(this, hardwareMap, telemetry, -430, 430, 0.5, 430, -430, testMode);

            /*
            Functions.drive(this, hardwareMap, telemetry, 2000, 2000, 0.5, 2000, 2000, testMode);

            Functions.turn(this, hardwareMap, telemetry, "Right", 0.5, testMode);
            Functions.drive(this, hardwareMap, telemetry, 700, 700, 0.5, 700, 700, testMode);
            Functions.turn(this, hardwareMap, telemetry, "Left", 0.5, testMode);
            Functions.drive(this, hardwareMap, telemetry, 700, 700, 0.5, 700, 700, testMode);

            Lightning.setPower(0.25);
            Functions.pause(2);
            Lightning.setPower(0);

            Functions.drive(this, hardwareMap, telemetry, -700, -700, 0.5, -700, -700, testMode);
            Functions.turn(this, hardwareMap, telemetry, "Right", 0.5, testMode);
            Functions.drive(this, hardwareMap, telemetry, -700, -700, 0.5, -700, -700, testMode);
            Functions.turn(this, hardwareMap, telemetry, "Left", 0.5, testMode);

            Functions.drive(this, hardwareMap, telemetry, -2000, -2000, 0.5, -2000, -2000, testMode);
        */
        }


        if(armTest == true)
        {
            //Functions.slideUp(this, hardwareMap, telemetry, ControlHub_ServoController, ExpansionHub2_ServoController);
            //Functions.drive(this, hardwareMap, telemetry, 3000, 3000, 0.1, 3000, 3000, testMode);
            //Functions.pause(4);
            //Functions.slideStop(this, hardwareMap, telemetry, ControlHub_ServoController, ExpansionHub2_ServoController);
            Functions.dropYellow(this, hardwareMap, telemetry, "Up", 0.2, 1.5, ControlHub_ServoController, ExpansionHub2_ServoController);
            Functions.pause(1);
        }

        if (turnTest == true)
        {
            Functions.turn(this, hardwareMap, telemetry, "Left", 0.5, testMode);
            Functions.pause(2);
            Functions.turn(this, hardwareMap, telemetry, "Left", 0.5, testMode);
            Functions.pause(2);
            Functions.turn(this, hardwareMap, telemetry, "Right", 0.5, testMode);
            Functions.pause(2);
            Functions.turn(this, hardwareMap, telemetry, "Left", 0.5, testMode);
            Functions.pause(2);

        }

        /*
        Drone.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Drone.setTargetPosition(6000);
        telemetry.addData("Drone Start", Drone.getCurrentPosition());
        telemetry.update();

        Drone.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        Drone.setPower(0.01);

        while (opModeIsActive() && Drone.isBusy())
        {
            telemetry.addData("Drone ", Drone.getCurrentPosition());
            telemetry.addData("Drone-Busy ", Drone.isBusy());
            telemetry.update();
            idle();
        }

        Drone.setPower(0);
        */


    }



    //Very complicated code to make it so that when it is showing
    //seconds it has gone (directionTime) will be a one decimal number,
    //ex 0.2. Add 0's to both tens to increase the number of decimal
    //places shown.
    private String formatSeconds(double inputSeconds){
        double fixedValue = Math.floor(inputSeconds * 10) / 10;
        return String.valueOf(fixedValue);
    }
}