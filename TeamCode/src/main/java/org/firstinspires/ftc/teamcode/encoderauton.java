package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous

public class encoderauton extends LinearOpMode {

    DcMotor frontLeft;
    double ticks = 435;
    double newTarget;



    public void runOpMode() {

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        int x = frontLeft.getTargetPosition();
        telemetry.addData("ticks", x);

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.a) {
                encoder();
            }


        }

    }

    public void encoder() {
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        newTarget = ticks / 2;
        frontLeft.setTargetPosition((int)(newTarget));
        frontLeft.setPower(0.3);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

}
