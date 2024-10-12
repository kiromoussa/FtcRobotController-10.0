package org.firstinspires.ftc.teamcode;

//Importing

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ServoController;

import org.firstinspires.ftc.robotcore.external.Func;

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
@Autonomous(name = "AutoBlueBackDONOTUSE")
public class AutoBlueBack extends LinearOpMode {

    private String action;
    private final int waitTime = 5;

    // Declare OpMode members.
    private ColorSensor backColorSensor;
    private ColorSensor leftColorSensor;
    private ColorSensor rightColorSensor;
    //private DistanceSensor distanceSensor;
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeftWheel  = null;
    private DcMotor frontRightWheel = null;
    private DcMotor backLeftWheel  = null;
    private DcMotor backRightWheel = null;
    private DcMotor Lightning = null;
    public ServoController ControlHub_ServoController;
    public ServoController ExpansionHub2_ServoController;
        /*
        private CRServo Drop1 = null;
        private CRServo Drop2 = null;
        private CRServo Drop3 = null;
        private CRServo Drop4 = null;
        */

    // Initialize directions
    private final robotDirection goForward = new robotDirection(1, 1, 1, 1, "Forward");
    private final robotDirection halfForward = new robotDirection(0.5, 0.5, 0.5, 0.5, "Forward/2");
    private final robotDirection slowForward = new robotDirection(0.25, 0.25, 0.25, 0.25, "ForwardSl");
    private final robotDirection goBackward = new robotDirection(-1, -1, -1, -1, "Back");
    private final robotDirection halfBackward = new robotDirection(-0.5, -0.5, -0.5, -0.5, "Back/2");
    private final robotDirection slowBackward = new robotDirection(-0.25, -0.25, -0.25, -0.25, "BackSl");
    private final robotDirection fullStop = new robotDirection(0, 0, 0, 0, "Pause");
    private final robotDirection strafeLeft = new robotDirection(-0.5, 0.5, 0.5, -0.5, "SLeft");
    private final robotDirection strafeRight = new robotDirection(0.5, -0.5, -0.5, 0.5, "SRight");

    private final robotDirection turnRight = new robotDirection(0.5, -0.5, 0.5, -0.5, "TLeft");
    private final robotDirection turnLeft = new robotDirection(-0.5, 0.5, -0.5, 0.5, "TRight");

    //Set color thresholds
    private final int blueThreshold = 2000;
    private final int redThreshold = 1700;
    private final int greenThreshold = 2000;

    // Store the current direction
    private robotDirection currentDirection = fullStop;


    @Override
    public void runOpMode() {
        // Report that op mode has been initialized
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        Boolean testMode = true;

        // Initialize the hardware variables

        //***VERY IMPORTANT**
        //Replace the device name (ex frontLeft) with the NAME OF THE
        //MOTORS DEFINED IN THE DRIVER HUB
        frontLeftWheel = hardwareMap.get(DcMotor.class, "FrontLeft");
        frontRightWheel = hardwareMap.get(DcMotor.class, "FrontRight");
        backLeftWheel  = hardwareMap.get(DcMotor.class, "BackLeft");
        backRightWheel = hardwareMap.get(DcMotor.class, "BackRight");
        Lightning = hardwareMap.get(DcMotor.class, "Lightning");
        ControlHub_ServoController = hardwareMap.get(ServoController.class, "Control Hub");
        ExpansionHub2_ServoController = hardwareMap.get(ServoController.class, "Expansion Hub 2");
            /*
            Drop1 = hardwareMap.get(CRServo.class, "Drop1");
            Drop2 = hardwareMap.get(CRServo.class, "Drop2");
            Drop3 = hardwareMap.get(CRServo.class, "Drop3");
            Drop4 = hardwareMap.get(CRServo.class, "Drop4");
            */

        // Set the wheel directions
        frontLeftWheel.setDirection(DcMotor.Direction.REVERSE);
        frontRightWheel.setDirection(DcMotor.Direction.FORWARD);
        backLeftWheel.setDirection(DcMotor.Direction.REVERSE);
        backRightWheel.setDirection(DcMotor.Direction.FORWARD);
            /*
            Drop1.setDirection(CRServo.Direction.FORWARD);
            Drop2.setDirection(CRServo.Direction.FORWARD);
            */
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////// Driving starts here//////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Define Var
        boolean isFound;
        // 0 = Normal, 1 = Right, 2 = Center, 3 = Left.
        int typeOfRun = 0;
        String whereFound = "Right";

        Functions.drive(this, hardwareMap, telemetry, 25, 25, 0.5, 25, 25, testMode);

        Functions.drive(this, hardwareMap, telemetry, -430, 430, 0.5, 430, -430, testMode);


        if(typeOfRun == 0)
        {
            isFound = ImageDetection.findBlue(this, hardwareMap, telemetry, 3.5, 0.7);
            telemetry.addData("Center Results: ", String.valueOf(isFound));
            telemetry.update();
        }
        else
        {
            isFound = typeOfRun == 1;
            Functions.pause(3.5);
        }

        // If pixel found, set it to have been in on the Right
        if (isFound == true) {
            whereFound = "Right";

            telemetry.addData("Found in: ", whereFound);
            telemetry.update();

            //if (true){return;}

            // Drive to pixel
            Functions.drive(this, hardwareMap, telemetry, 1800, 1800, 0.5, 1800, 1800, testMode);

            Functions.drive(this, hardwareMap, telemetry, -200, -200, 0.5, -200, -200, testMode);

            // Drop Purple Pixel
            Lightning.setPower(0.25);

            // Wait
            //Functions.slideUp(this, hardwareMap, telemetry, ControlHub_ServoController, ExpansionHub2_ServoController);
            Functions.pause(2);

            // Back uoi
            Functions.drive(this, hardwareMap, telemetry, -400, -400, 0.5, -400, -400, testMode);

            // Stop motor
            Lightning.setPower(0);
/*
            // Turn Left
            Functions.turn(this, hardwareMap, telemetry, "Left", 0.5, testMode);

            // Go to Backdrop
            Functions.drive(this, hardwareMap, telemetry, -1800, -1800, 0.75, -1800, -1800, testMode);

            // Go a bit backward
            Functions.drive(this, hardwareMap, telemetry, -250, -250, 0.2, -250, -250, testMode);

            // Drop Pixel
            Functions.dropYellow(this, hardwareMap, telemetry, "Up", 0.2, 1.5, ControlHub_ServoController, ExpansionHub2_ServoController);
            Functions.pause(0.2);
            Functions.slideStop(this, hardwareMap, telemetry, ControlHub_ServoController, ExpansionHub2_ServoController);

            // Go a bit forward
            Functions.drive(this, hardwareMap, telemetry, 125, 125, 0.7, 125, 125, testMode);

            //Strafe left to parking
            Functions.drive(this, hardwareMap, telemetry, 1000, -1000, 0.5, -1000, 1000, testMode);

            // Wait
            Functions.pause(0.1);

            //Park
            Functions.drive(this, hardwareMap, telemetry, -400, -400, 0.2, -400, -400, testMode);
*/
            if (true){return;}
        }

        // If pixel found on right, skip looking on center
        if (isFound == false) {

            // Go tforward
            Functions.drive(this, hardwareMap, telemetry, 300, 300, 0.5, 300, 300, testMode);

            // Strafe to center
            Functions.drive(this, hardwareMap, telemetry, 600, -600, 0.5, -600, 600, testMode);

            // Check for pixel
            if (typeOfRun == 0) {
                isFound = ImageDetection.findBlue(this, hardwareMap, telemetry, 3.5, 0.7);
                telemetry.addData("Right Results: ", String.valueOf(isFound));
                telemetry.update();
            }
            else
            {
                isFound = typeOfRun == 2;
                Functions.pause(3.5);
            }

            // If pixel found, set it to have been in the center
            if (isFound == true) {
                whereFound = "Center";

                // Drive to prop
                Functions.drive(this, hardwareMap, telemetry, 1800, 1800, 0.5, 1800, 1800, testMode);

                Functions.drive(this, hardwareMap, telemetry, -300, -300, 0.5, -300, -300, testMode);

                // Drop Purple Pixel
                Lightning.setPower(0.25);

                // Wait
                //Functions.slideUp(this, hardwareMap, telemetry, ControlHub_ServoController, ExpansionHub2_ServoController);
                Functions.pause(2);

                // Back uoi
                Functions.drive(this, hardwareMap, telemetry, -400, -400, 0.5, -400, -400, testMode);

                // Stop motor
                Lightning.setPower(0);
/*
                // Turn Right
                Functions.turn(this, hardwareMap, telemetry, "Left", 0.5, testMode);

                // Go to Backdrop
                Functions.drive(this, hardwareMap, telemetry, -2000, -2000, 0.5, -2000, -2000, testMode);

                // Strafe to center backdrop
                Functions.drive(this, hardwareMap, telemetry, -350, 350, 0.5, 350, -350, testMode);


                // Back uoi
                Functions.drive(this, hardwareMap, telemetry, -450, -450, 0.3, -450, -450, testMode);

                // Drop Pixel
                Functions.dropYellow(this, hardwareMap, telemetry, "Down", 0.2,1.5, ControlHub_ServoController, ExpansionHub2_ServoController);
                Functions.slideStop(this, hardwareMap, telemetry, ControlHub_ServoController, ExpansionHub2_ServoController);

                // Go a bit forward
                Functions.drive(this, hardwareMap, telemetry, 225, 225, 0.7, 225, 225, testMode);

                //Strafe left to parking
                Functions.drive(this, hardwareMap, telemetry, 1700, -1700, 0.5, -1700, 1700, testMode);

                // Wait
                Functions.pause(0.1);

                //Park
                Functions.drive(this, hardwareMap, telemetry, -400, -400, 0.2, -400, -400, testMode);
*/
                if (true){return;}
            }
        }

        if(typeOfRun == 3)
        {
            isFound = false;
        }

        // If pixel is NOT found, show that it is not, and default to Left
        if (isFound == false) {
            whereFound = "Left";
            telemetry.addData("***PIXEL FOUND == ", String.valueOf(isFound), ", DEFAULTING TO LEFT***");
            telemetry.update();

            Functions.pause(0);

            // Drive to Left tape
            Functions.drive(this, hardwareMap, telemetry, 1250, 1250, 0.5, 1250, 1250, testMode);

            // Turn left
            Functions.turn(this, hardwareMap, telemetry, "Left", 0.5, testMode);

            // Drive to prop
            Functions.drive(this, hardwareMap, telemetry, 285, 285, 0.5, 285, 285, testMode);

            // Drive to prop
            Functions.drive(this, hardwareMap, telemetry, -200, -200, 0.5, -200, -200, testMode);

            // Drop Purple Pixel
            Lightning.setPower(0.25);

            // Wait
            //Functions.slideUp(this, hardwareMap, telemetry, ControlHub_ServoController, ExpansionHub2_ServoController);
            Functions.pause(2);

            // Stop motor
            Lightning.setPower(0);

            // Go back a bit
            Functions.drive(this, hardwareMap, telemetry, -200, -200, 0.5, -200, -200, testMode);

            /* Go to Backdrop
            Functions.drive(this, hardwareMap, telemetry, -2500, -2500, 0.5, -2500, -2500, testMode);

            // Strafe to drop pixel on the backdrop
            Functions.drive(this, hardwareMap, telemetry, -725, 725, 0.5, 725, -725, testMode);

            // Back uoi
            Functions.drive(this, hardwareMap, telemetry, -300, -300, 0.5, -300, -300, testMode);

            // Drop Pixel
            Functions.dropYellow(this, hardwareMap, telemetry, "Down", 0.2, 1.5,ControlHub_ServoController, ExpansionHub2_ServoController);
            Functions.slideStop(this, hardwareMap, telemetry, ControlHub_ServoController, ExpansionHub2_ServoController);

            // Go a bit forward
            Functions.drive(this, hardwareMap, telemetry, 150, 150, 0.05, 150, 150, testMode);

            //Strafe left to parking
            Functions.drive(this, hardwareMap, telemetry, 2000, -2000, 0.5, -2000, 2000, testMode);

            // Wait
            Functions.pause(0.1);

            //Park
            Functions.drive(this, hardwareMap, telemetry, -400, -400, 0.2, -400, -400, testMode);
*/
        }

        telemetry.addData("Finish", "Fish ish");
        telemetry.update();

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////// Driving stop here////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }


    // Drive A specific direction for the number of seconds
    private void driveSeconds(robotDirection newDirection, double seconds) {

        // Set the current direction
        currentDirection = newDirection;

        // Set the drive time
        ElapsedTime driveTime = new ElapsedTime();

        while (opModeIsActive() && driveTime.seconds() < seconds) {
            //telemetry.addData("Current Direction", "fl (%.2f), fr (%.2f), bl (%.2f), br (%.2f)", currentDirection.frontLeftPower, currentDirection.frontRightPower, currentDirection.backLeftPower, currentDirection.backRightPower);
            telemetry.addData("Direction", currentDirection.direction);
            telemetry.addData("Direction Time", formatSeconds(driveTime.seconds()) + "/" + seconds);
            telemetry.update();
            setPower();
        }


        // Stop the robot
        currentDirection = fullStop;

        setPower();
    }

    private boolean driveUntilColor(robotDirection newDirection, String colorType, double searchTime, ColorSensor colorSensorType) {
        // Will keep going until is true
        boolean colorFound = false;
        int threshold = 0;

        // Set the current direction
        currentDirection = newDirection;

        // Set the drive time
        ElapsedTime driveTime = new ElapsedTime();

        while (opModeIsActive() && driveTime.seconds() < searchTime && colorFound == false) {
            switch (colorType){
                case "blue":
                    colorFound = colorSensorType.blue() > blueThreshold;
                    break;
                case "red":
                    colorFound = colorSensorType.red() > redThreshold;
                    break;
                case "green":
                    colorFound = colorSensorType.green() > greenThreshold;
                    break;
            }
            if (colorFound) {
                telemetry.addData("Found ", colorType);
            }
            else {
                telemetry.addData("Searching for:", colorType);
                telemetry.addData("In Direction:", currentDirection.direction);
                telemetry.addData("Time remaining until return", formatSeconds(driveTime.seconds()) + "/" + searchTime);
                telemetry.update();
                setPower();
            }
        }

        // Stop the robot
        currentDirection = fullStop;

        setPower();

        return colorFound;
    }
        /*
        private boolean driveUntilDistanceAway(robotDirection newDirection, double distance, double searchTime) {
            // Will keep going until is true
            boolean distanceReached = false;

            // Set the current direction
            currentDirection = newDirection;

            // Set the drive time
            ElapsedTime driveTime = new ElapsedTime();

            while (opModeIsActive() && driveTime.seconds() < searchTime && distanceReached == false) {

                // Get current distance
                double currentDistance = distanceSensor.getDistance(DistanceUnit.CM);

                if (distance > currentDistance) {
                    distanceReached = true;
                }

                if (distanceReached) {
                    telemetry.addData("Got to ", distance);
                } else {
                    telemetry.addData("Distance:", currentDistance + "/" + distance);
                    telemetry.addData("In Direction:", currentDirection.direction);
                    telemetry.addData("Time remaining until return", formatSeconds(driveTime.seconds()) + "/" + searchTime);
                    telemetry.update();
                    setPower();
                }
            }

            // Stop the robot
            currentDirection = fullStop;

            setPower();

            return distanceReached;
        }
        */

    private void setArmPower() {
        frontLeftWheel.setPower(currentDirection.frontLeftPower);
        frontRightWheel.setPower(currentDirection.frontRightPower);
        backLeftWheel.setPower(currentDirection.backLeftPower);
        backRightWheel.setPower(currentDirection.backRightPower);
    }

    //This is used in defining directions (goForward) so you can just
    //replace the numbers with the power you want it to go at (will
    //vary in speed depending on the battery level)
    private void setPower() {
        double batteryModifyer = 1;
        frontLeftWheel.setPower(currentDirection.frontLeftPower * batteryModifyer);
        frontRightWheel.setPower(currentDirection.frontRightPower * batteryModifyer);
        backLeftWheel.setPower(currentDirection.backLeftPower * batteryModifyer);
        backRightWheel.setPower(currentDirection.backRightPower * batteryModifyer);
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
//Blue Back