package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import android.graphics.Color;

@TeleOp

public class ColorSensing extends LinearOpMode {

    ColorSensor sensorColor;
    DistanceSensor sensorDistance;

    public void runOpMode() {

        sensorColor = hardwareMap.get(ColorSensor.class, "csensor");
        sensorDistance = hardwareMap.get(DistanceSensor.class, "csensor");

        float hsvValues[] = {0F, 0F, 0F};

        // values is a reference to the hsvValues array.
        final float values[] = hsvValues;

        final double SCALE_FACTOR = 255;

        String color = "None";
        String pixelColor = "None";

        waitForStart();

        while (opModeIsActive()) {

            Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR), (int) (sensorColor.green() * SCALE_FACTOR), (int) (sensorColor.blue() * SCALE_FACTOR), hsvValues);

            double red = sensorColor.red();
            double green = sensorColor.green();
            double blue = sensorColor.blue();
            double hue = hsvValues[0];

            if (red>blue && red>green) {
                color = "Red";
            } else if (green>blue && green>red) {
                color = "Green";
            } else if (blue>green && blue>red) {
                color = "Blue";
            }

            if (hue>205 && hue<215) {
                pixelColor = "Purple";
            } else if (hue>160 && hue<170) {
                pixelColor = "White";
            } else if (hue>75 && hue<90) {
                pixelColor = "Yellow";
            } else if (hue>120 && hue<140) {
                pixelColor = "Green";
            } else {
                pixelColor = "No pixel";
            }



            telemetry.addData("Red", red);
            telemetry.addData("Green", green);
            telemetry.addData("Blue", blue);
            telemetry.addData("Alpha", sensorColor.alpha());
            telemetry.addData("Hue", hsvValues[0]);
            telemetry.addData("Highest Color", color);
            telemetry.addData("Pixel Color", pixelColor);

            telemetry.update();


        }


    }


}