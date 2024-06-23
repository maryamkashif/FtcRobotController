package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp

public class teleOpMotorsArcade extends LinearOpMode {

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

            float power;

            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

            waitForStart();


            // back and forth
            if (gamepad1.left_stick_y > 0.1 || gamepad1.left_stick_y < -0.1) {
                power = gamepad1.left_stick_y;
                telemetry.addData("Left joystick y: ", power);
                frontLeft.setPower(power);
                backLeft.setPower(power);
                frontRight.setPower(power);
                frontRight.setPower(power);

            // right strafe
            } else if(gamepad1.left_stick_x < -0.1) {
                power = gamepad1.left_stick_y;
                telemetry.addData("Left joystick x: ", power);
                frontLeft.setPower(-power);
                frontRight.setPower(power);
                backLeft.setPower(power);
                backRight.setPower(-power);

            // left strafe
            } else if(gamepad1.left_trigger > 0.1) {
                power = gamepad1.left_stick_y;
                telemetry.addData("Left joystick x: ", power);
                frontLeft.setPower(power);
                frontRight.setPower(-power);
                backLeft.setPower(-power);
                backRight.setPower(power);

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
