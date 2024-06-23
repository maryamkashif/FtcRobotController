package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp

public class teleOpMotorsdpad extends LinearOpMode {

    public void runOpMode() {

        DcMotor frontRight;
        DcMotor backRight;
        DcMotor frontLeft;
        DcMotor backLeft;


        waitForStart();

        while(opModeIsActive()) {

            frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
            backLeft = hardwareMap.get(DcMotor.class, "backLeft");
            frontRight = hardwareMap.get(DcMotor.class, "frontRight");
            backRight = hardwareMap.get(DcMotor.class, "backRight");


            waitForStart();
            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);




            if(gamepad1.dpad_up) {
                telemetry.addLine("Up key pressed");
                frontLeft.setPower(-0.5);
                frontRight.setPower(-0.5);
                backLeft.setPower(-0.5);
                backRight.setPower(-0.5);

            } else if(gamepad1.dpad_down) {
                telemetry.addLine("Down key pressed");
                frontLeft.setPower(0.5);
                frontRight.setPower(0.5);
                backLeft.setPower(0.5);
                backRight.setPower(0.5);

            } else if(gamepad1.dpad_right) {
                telemetry.addLine("Right key pressed");
                frontLeft.setPower(-0.5);
                frontRight.setPower(0.5);
                backLeft.setPower(0.5);
                backRight.setPower(-0.5);

            } else if(gamepad1.dpad_left) {
                telemetry.addLine("Left key pressed");
                frontLeft.setPower(0.5);
                frontRight.setPower(-0.5);
                backLeft.setPower(-0.5);
                backRight.setPower(0.5);

            } else {
                frontLeft.setPower(0);
                frontRight.setPower(0);
                backLeft.setPower(0);
                backRight.setPower(0);
            }


            telemetry.update();
        }


    }


}
