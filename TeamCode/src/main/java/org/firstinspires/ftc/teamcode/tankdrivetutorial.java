package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp

public class tankdrivetutorial extends LinearOpMode {

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


            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

            frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            waitForStart();

            float right = gamepad1.right_stick_y;
            float left = gamepad1.left_stick_y;
            float rightS = gamepad1.right_trigger;
            float leftS = gamepad1.left_trigger;

            if (right > .1 || right < -.1 || left > .1 || left < -.1) {
                backRight.setPower(right);
                frontRight.setPower(right);
                backLeft.setPower(left);
                frontLeft.setPower(left);

            } else if(rightS > .1) {
                telemetry.addLine("Right trigger pressed");
                frontLeft.setPower(-rightS);
                frontRight.setPower(rightS);
                backLeft.setPower(rightS);
                backRight.setPower(-rightS);

            } else if(leftS > .1) {
                telemetry.addLine("Left trigger pressed");
                frontLeft.setPower(leftS);
                frontRight.setPower(-leftS);
                backLeft.setPower(-leftS);
                backRight.setPower(leftS);

            } else {
                telemetry.addLine("Idle");
                frontLeft.setPower(0);
                backLeft.setPower(0);
                frontRight.setPower(0);
                backRight.setPower(0);
            }

            telemetry.update();

        }


    }


}
