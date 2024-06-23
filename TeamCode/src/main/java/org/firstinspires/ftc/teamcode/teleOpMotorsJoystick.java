package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp

public class teleOpMotorsJoystick extends LinearOpMode {

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

            // left side
            if (gamepad1.left_stick_y > 0.1 || gamepad1.left_stick_y < -0.1 || gamepad1.right_stick_y > 0.1 || gamepad1.right_stick_y < -0.1) {
                double leftWheels = gamepad1.left_stick_y;
                double rightWheels = gamepad1.right_stick_y;
                telemetry.addData("Left joystick: ", leftWheels);
                telemetry.addData("Right joystick: ", rightWheels);
                frontLeft.setPower(leftWheels);
                backLeft.setPower(leftWheels);
                frontRight.setPower(rightWheels);
                frontRight.setPower(rightWheels);

            } else if(gamepad1.right_trigger > 0.1) {
                telemetry.addLine("Right trigger pressed");
                frontLeft.setPower(-0.5);
                frontRight.setPower(0.5);
                backLeft.setPower(0.5);
                backRight.setPower(-0.5);

            } else if(gamepad1.left_trigger > 0.1) {
                telemetry.addLine("Left trigger pressed");
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
