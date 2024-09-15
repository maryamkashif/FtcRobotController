package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class ServoTeleOp extends LinearOpMode {

    Servo bobby;

    public void runOpMode() {

        bobby = hardwareMap.get(Servo.class, "servo");

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.dpad_left) {
                bobby.setPosition(0);
            } else if (gamepad1.dpad_up) {
                bobby.setPosition(0.5);
            } else if (gamepad1.dpad_right) {
                bobby.setPosition(1);
            }

        }
    }
}