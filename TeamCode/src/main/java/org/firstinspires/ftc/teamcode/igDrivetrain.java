package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp

public class igDrivetrain extends LinearOpMode {

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

            frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
            backRight.setDirection(DcMotorSimple.Direction.REVERSE);

            waitForStart();


            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            frontLeft.setPower(frontLeftPower);
            backLeft.setPower(backLeftPower);
            frontRight.setPower(frontRightPower);
            backRight.setPower(backRightPower);



            telemetry.update();
        }


    }


}
