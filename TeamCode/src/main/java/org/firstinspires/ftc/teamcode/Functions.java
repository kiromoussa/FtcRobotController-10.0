    package org.firstinspires.ftc.teamcode;

    import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
    import com.qualcomm.robotcore.hardware.DcMotor;
    import com.qualcomm.robotcore.hardware.CRServo;
    import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
    import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
    import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
    import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
    import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
    import org.firstinspires.ftc.vision.VisionPortal;
    import org.firstinspires.ftc.vision.tfod.TfodProcessor;

    import com.qualcomm.robotcore.hardware.IMU;
    import com.qualcomm.robotcore.util.ElapsedTime;

    import java.util.List;
    public class Functions {

        public static void pause(double waitTime) {
            ElapsedTime elapsedTime = new ElapsedTime();

            while (elapsedTime.seconds() < waitTime) {
                //STOP, wait a minute
            }
        }

        public static String formatSeconds(double inputSeconds){
            double fixedValue = Math.floor(inputSeconds * 10) / 10;
            return String.valueOf(fixedValue);
        }

        public static void drive(com.qualcomm.robotcore.eventloop.opmode.LinearOpMode opMode, com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, org.firstinspires.ftc.robotcore.external.Telemetry telemetry, int BackLeft_target, int BackRight_target, double Speed, int FrontLeft_target, int FrontRight_target, boolean testMode) {
            DcMotor BackLeft;
            DcMotor BackRight;
            DcMotor FrontLeft;
            DcMotor FrontRight;

            BackLeft = hardwareMap.get(DcMotor.class, "BackLeft");
            BackRight = hardwareMap.get(DcMotor.class, "BackRight");
            FrontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
            FrontRight = hardwareMap.get(DcMotor.class, "FrontRight");

            BackLeft.setDirection(DcMotor.Direction.REVERSE);
            BackRight.setDirection(DcMotor.Direction.FORWARD);
            FrontLeft.setDirection(DcMotor.Direction.REVERSE);
            FrontRight.setDirection(DcMotor.Direction.FORWARD);

            BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            BackLeft.setTargetPosition(BackLeft_target);
            BackRight.setTargetPosition(BackRight_target);
            FrontLeft.setTargetPosition(FrontLeft_target);
            FrontRight.setTargetPosition(FrontRight_target);

            BackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            BackLeft.setPower(Speed);
            BackRight.setPower(Speed);
            FrontLeft.setPower(Speed);
            FrontRight.setPower(Speed);

            DcMotor[] WHEELS = new DcMotor[4];
            WHEELS[0] = BackLeft;
            WHEELS[1] = BackRight;
            WHEELS[2] = FrontLeft;
            WHEELS[3] = FrontRight;

            while (opMode.opModeIsActive() && BackLeft.isBusy() && BackRight.isBusy() && FrontLeft.isBusy() && FrontRight.isBusy())
            {

                telemetry.addData("bk-left-end", BackLeft.getCurrentPosition() + "," + BackLeft.getPower());
                telemetry.addData("bk-right-end", BackRight.getCurrentPosition() + "," + BackRight.getPower());
                telemetry.addData("fwd-left-end", FrontLeft.getCurrentPosition() + "," + FrontLeft.getPower());
                telemetry.addData("fwd-right-end", FrontRight.getCurrentPosition() + "," + FrontRight.getPower());
                telemetry.addData("bk-left-endBusy", BackLeft.isBusy());
                telemetry.addData("bk-right-endBusy", BackRight.isBusy());
                telemetry.addData("fwd-left-endBusy", FrontLeft.isBusy());
                telemetry.addData("fwd-right-endBusy", FrontRight.isBusy());
                telemetry.update();

                opMode.idle();
            }

            BackLeft.setPower(0);
            BackRight.setPower(0);
            FrontLeft.setPower(0);
            FrontRight.setPower(0);

        }


        public static void turn(com.qualcomm.robotcore.eventloop.opmode.LinearOpMode opMode, com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, org.firstinspires.ftc.robotcore.external.Telemetry telemetry, String direction, double Speed, boolean testMode) {
            DcMotor BackLeft;
            DcMotor FrontLeft;
            DcMotor FrontRight;
            DcMotor BackRight;

            BackLeft = hardwareMap.get(DcMotor.class, "BackLeft");
            FrontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
            FrontRight = hardwareMap.get(DcMotor.class, "FrontRight");
            BackRight = hardwareMap.get(DcMotor.class, "BackRight");

            FrontLeft.setDirection(DcMotor.Direction.REVERSE);
            FrontRight.setDirection(DcMotor.Direction.FORWARD);
            BackLeft.setDirection(DcMotor.Direction.REVERSE);
            BackRight.setDirection(DcMotor.Direction.FORWARD);

            BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            int distance = 1300;

            if (direction == "Right") {
                BackLeft.setTargetPosition(distance);
                BackRight.setTargetPosition(-distance);
                FrontLeft.setTargetPosition(distance);
                FrontRight.setTargetPosition(-distance);
            } else if (direction == "Left") {
                BackLeft.setTargetPosition(-distance);
                BackRight.setTargetPosition(distance);
                FrontLeft.setTargetPosition(-distance);
                FrontRight.setTargetPosition(distance);
            }

            FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            BackLeft.setPower(Speed);
            BackRight.setPower(Speed);
            FrontLeft.setPower(Speed);
            FrontRight.setPower(Speed);

            while (opMode.opModeIsActive() && BackLeft.isBusy() && BackRight.isBusy() && FrontLeft.isBusy() && FrontRight.isBusy())
            {

                telemetry.addData("bk-left-end", BackLeft.getCurrentPosition() + "," + BackLeft.getPower());
                telemetry.addData("bk-right-end", BackRight.getCurrentPosition() + "," + BackRight.getPower());
                telemetry.addData("fwd-left-end", FrontLeft.getCurrentPosition() + "," + FrontLeft.getPower());
                telemetry.addData("fwd-right-end", FrontRight.getCurrentPosition() + "," + FrontRight.getPower());
                telemetry.addData("bk-left-endBusy", BackLeft.isBusy());
                telemetry.addData("bk-right-endBusy", BackRight.isBusy());
                telemetry.addData("fwd-left-endBusy", FrontLeft.isBusy());
                telemetry.addData("fwd-right-endBusy", FrontRight.isBusy());
                telemetry.update();



                opMode.idle();
            }

            BackLeft.setPower(0);
            BackRight.setPower(0);
            FrontLeft.setPower(0);
            FrontRight.setPower(0);

        }

        public static void dropYellow(com.qualcomm.robotcore.eventloop.opmode.LinearOpMode opMode, com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, org.firstinspires.ftc.robotcore.external.Telemetry telemetry, String Direction, double speed, double time, com.qualcomm.robotcore.hardware.ServoController ControlHub_ServoController, com.qualcomm.robotcore.hardware.ServoController ExpansionHub2_ServoController) {

            //Define CRServos
            CRServo BackDropControl = null;
            //CRServo ExpandControl = null;

            //Set servos
            BackDropControl = hardwareMap.get(CRServo.class, "BackDropControl");
            //ExpandControl = hardwareMap.get(CRServo.class, "ExpandControl");

            //Disable pwm
            ControlHub_ServoController.pwmDisable();
            //ExpansionHub2_ServoController.pwmDisable();

            //Slide to the Up
            //ExpandControl.setPower(0.7);
            //Functions.pause(3.5);

            // Drop Pixel

            telemetry.addData("Currently going: ", "Up");
            telemetry.update();

            BackDropControl.setPower(0.7);
            Functions.pause(1.5);
            BackDropControl.setPower(0);

            // Wait
            Functions.pause(0.6);


            telemetry.addData("Currently going: ", "Down");
            telemetry.update();

            // Bring down Dropigimigigy6
            BackDropControl.setPower(-0.7);
            Functions.pause(1);
            BackDropControl.setPower(0);

            //Slide to the down
            //ExpandControl.setPower(0);

            telemetry.addData("Currently going: ", "to");
            telemetry.update();
        }

        public static void slideUp(com.qualcomm.robotcore.eventloop.opmode.LinearOpMode opMode, com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, org.firstinspires.ftc.robotcore.external.Telemetry telemetry, com.qualcomm.robotcore.hardware.ServoController ControlHub_ServoController, com.qualcomm.robotcore.hardware.ServoController ExpansionHub2_ServoController) {
/*

            //Define CRServos
            CRServo ExpandControl = null;

            //Set servos
            ExpandControl = hardwareMap.get(CRServo.class, "ExpandControl");

            //Disable pwm
            ControlHub_ServoController.pwmDisable();
            ExpansionHub2_ServoController.pwmDisable();

            //Slide to the Up
            ExpandControl.setPower(1);

            telemetry.addData("ExpandControl power: ", ExpandControl.getPower());
            telemetry.update();
*/
        }

        public static void slideStop(com.qualcomm.robotcore.eventloop.opmode.LinearOpMode opMode, com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, org.firstinspires.ftc.robotcore.external.Telemetry telemetry, com.qualcomm.robotcore.hardware.ServoController ControlHub_ServoController, com.qualcomm.robotcore.hardware.ServoController ExpansionHub2_ServoController) {
/*
            //Define CRServos
            CRServo ExpandControl = null;

            //Set servos
            ExpandControl = hardwareMap.get(CRServo.class, "ExpandControl");

            //Disable pwm
            ControlHub_ServoController.pwmDisable();
            ExpansionHub2_ServoController.pwmDisable();

            //Slide to the Up
            //ExpandControl.setPower(0);

            telemetry.addData("ExpandControl power: ", ExpandControl.getPower());
            telemetry.update();
            
 */
        }

        public static void straighten(com.qualcomm.robotcore.eventloop.opmode.LinearOpMode opMode, com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, org.firstinspires.ftc.robotcore.external.Telemetry telemetry, double baseLine) {

            DcMotor BackLeft = hardwareMap.get(DcMotor.class, "BackLeft");
            DcMotor FrontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
            DcMotor FrontRight = hardwareMap.get(DcMotor.class, "FrontRight");
            DcMotor BackRight = hardwareMap.get(DcMotor.class, "BackRight");


            FrontLeft.setDirection(DcMotor.Direction.REVERSE);
            FrontRight.setDirection(DcMotor.Direction.FORWARD);
            BackLeft.setDirection(DcMotor.Direction.REVERSE);
            BackRight.setDirection(DcMotor.Direction.FORWARD);

            BackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            FrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            FrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            BackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            IMU imu = hardwareMap.get(IMU.class, "imu");

            YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();

            double newangle = orientation.getYaw(AngleUnit.DEGREES);

            double difference = baseLine - newangle;
            difference = difference;

            if(difference > 0) {
                FrontLeft.setPower(-0.3);
                FrontRight.setPower(0.3);
                BackLeft.setPower(-0.3);
                BackRight.setPower(0.3);
            } else { //if(difference < 0)
                FrontLeft.setPower(0.3);
                FrontRight.setPower(-0.3);
                BackLeft.setPower(0.3);
                BackRight.setPower(-0.3);
            }

            telemetry.addData("Difference", difference);
            telemetry.update();


            while (opMode.opModeIsActive() && Math.abs(difference) > 1) {

                orientation = imu.getRobotYawPitchRollAngles();

                newangle = orientation.getYaw(AngleUnit.DEGREES);

                difference = baseLine - newangle;

                if(difference > 90)
                {
                    difference = difference - 90;
                }
                else if(difference < -90)
                {
                    difference = difference + 90;
                }


                telemetry.addData("Baseline", baseLine);
                telemetry.addData("Difference", difference);
                telemetry.addData("Newangle", newangle);
                telemetry.update();
            }

            telemetry.addData("Finish", "Fish ish");
            telemetry.update();

            // turn the motors off.
            FrontLeft.setPower(0);
            FrontRight.setPower(0);
            BackLeft.setPower(0);
            BackRight.setPower(0);
        }

        public static double getBaseLine(com.qualcomm.robotcore.eventloop.opmode.LinearOpMode opMode, com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, org.firstinspires.ftc.robotcore.external.Telemetry telemetry) {

            IMU imu = hardwareMap.get(IMU.class, "imu");

            YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();

            return orientation.getYaw(AngleUnit.DEGREES);
        }
    }