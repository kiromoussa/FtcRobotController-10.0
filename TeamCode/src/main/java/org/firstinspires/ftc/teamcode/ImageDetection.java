package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.List;
public class ImageDetection {

    public static boolean findBlue(com.qualcomm.robotcore.eventloop.opmode.LinearOpMode opMode, com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, org.firstinspires.ftc.robotcore.external.Telemetry telemetry, double searchTime, double confidence) {
        ElapsedTime elapsedTime = new ElapsedTime();


        TfodProcessor tfod = new TfodProcessor.Builder()
                .setModelFileName("/sdcard/FIRST/tflitemodels/Declan.tflite")
                .setModelLabels(new String[]{"BlueBox"})
                .build();

        tfod.setMinResultConfidence((float) confidence);

        VisionPortal.Builder builder = new VisionPortal.Builder();

        builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));
        builder.addProcessor(tfod);

        VisionPortal visionPortal = builder.build();

        while (elapsedTime.seconds() < searchTime && opMode.opModeIsActive()) {
            List<Recognition> currentRecognitions = tfod.getRecognitions();

            telemetry.addData("Time Elapsed", Functions.formatSeconds(elapsedTime.seconds()) + "/" + searchTime);
            telemetry.update();
            if (currentRecognitions.size() > 0){
                for (Recognition recognition : currentRecognitions) {
                    double x = (recognition.getLeft() + recognition.getRight()) / 2 ;
                    double y = (recognition.getTop()  + recognition.getBottom()) / 2 ;

                    telemetry.addData(""," ");
                    telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
                    telemetry.update();
                }
                Functions.pause(1);
                visionPortal.close();
                return true;
            }
        }
        /*
        while (elapsedTime.seconds() < searchTime && opMode.opModeIsActive()){
            List<Recognition> currentRecognitions = tfod.getRecognitions();
            telemetry.addData("# Objects Detected", currentRecognitions.size());

            // Step through the list of recognitions and display info for each one.
            for (Recognition recognition : currentRecognitions) {
                double x = (recognition.getLeft() + recognition.getRight()) / 2 ;
                double y = (recognition.getTop()  + recognition.getBottom()) / 2 ;

                telemetry.addData(""," ");
                telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
                telemetry.addData("- Position", "%.0f / %.0f", x, y);
                telemetry.addData("- Size", "%.0f x %.0f", recognition.getWidth(), recognition.getHeight());
                telemetry.update();

            }   // end for() loop
        }
        */
        visionPortal.close();
        return false;
    }
    public static boolean findRed(com.qualcomm.robotcore.eventloop.opmode.LinearOpMode opMode, com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, org.firstinspires.ftc.robotcore.external.Telemetry telemetry, double searchTime, double confidence) {
        ElapsedTime elapsedTime = new ElapsedTime();


        TfodProcessor tfod = new TfodProcessor.Builder()
                .setModelFileName("/sdcard/FIRST/tflitemodels/redbox.tflite")
                .setModelLabels(new String[]{"RedBox"})
                .build();

        tfod.setMinResultConfidence((float) confidence);

        VisionPortal.Builder builder = new VisionPortal.Builder();

        builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));
        builder.addProcessor(tfod);

        VisionPortal visionPortal = builder.build();

        while (elapsedTime.seconds() < searchTime && opMode.opModeIsActive()) {
            List<Recognition> currentRecognitions = tfod.getRecognitions();

            telemetry.addData("Time Elapsed", Functions.formatSeconds(elapsedTime.seconds()) + "/" + searchTime);
            telemetry.update();
            if (currentRecognitions.size() > 0){
                for (Recognition recognition : currentRecognitions) {
                    double x = (recognition.getLeft() + recognition.getRight()) / 2 ;
                    double y = (recognition.getTop()  + recognition.getBottom()) / 2 ;

                    telemetry.addData(""," ");
                    telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
                    telemetry.update();
                }
                Functions.pause(1);
                visionPortal.close();
                return true;
            }
        }
        /*
        while (elapsedTime.seconds() < searchTime && opMode.opModeIsActive()){
            List<Recognition> currentRecognitions = tfod.getRecognitions();
            telemetry.addData("# Objects Detected", currentRecognitions.size());

            // Step through the list of recognitions and display info for each one.
            for (Recognition recognition : currentRecognitions) {
                double x = (recognition.getLeft() + recognition.getRight()) / 2 ;
                double y = (recognition.getTop()  + recognition.getBottom()) / 2 ;

                telemetry.addData(""," ");
                telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
                telemetry.addData("- Position", "%.0f / %.0f", x, y);
                telemetry.addData("- Size", "%.0f x %.0f", recognition.getWidth(), recognition.getHeight());
                telemetry.update();

            }   // end for() loop
        }
        */
        visionPortal.close();
        return false;
    }

    public static boolean findPixel(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, org.firstinspires.ftc.robotcore.external.Telemetry telemetry, double searchTime) {
        try {

            ElapsedTime elapsedTime = new ElapsedTime();

            TfodProcessor tfod = new TfodProcessor.Builder()
                    .setModelFileName("/sdcard/FIRST/tflitemodels/CenterStage.tflite")
                    .setModelLabels(new String[]{"Pixel"})
                    .build();
            tfod.setMinResultConfidence((float) 0.6);

            VisionPortal.Builder builder = new VisionPortal.Builder();

            builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));
            builder.addProcessor(tfod);

            VisionPortal visionPortal = builder.build();

            while (elapsedTime.seconds() < searchTime) {
                List<Recognition> currentRecognitions = tfod.getRecognitions();

                if (currentRecognitions.size() > 0) {
                    //visionPortal.close();
                    return true;
                }
            }

            //visionPortal.close();
            return false;
        }
        catch (Exception ex) {
            return false;
        }
    }
}