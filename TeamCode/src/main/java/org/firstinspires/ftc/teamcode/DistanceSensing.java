package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp

public class DistanceSensing extends LinearOpMode {

    ColorSensor sensorColor;
    DistanceSensor sensorDistance;

    public void runOpMode() {

        sensorColor = hardwareMap.get(ColorSensor.class, "csensor");
        sensorDistance = hardwareMap.get(DistanceSensor.class, "csensor");

        double distance;



        waitForStart();

        while (opModeIsActive()) {

            distance = sensorDistance.getDistance(DistanceUnit.MM);
            telemetry.addData("Distance", String.format("%.01f mm", distance));

            telemetry.update();


        }


    }


}