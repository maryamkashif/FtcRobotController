package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class IGElevator extends LinearOpMode {
    public DcMotorEx elevatorRight;
    public DcMotorEx elevatorLeft;

    int pos1 = 1500;
    int pos2 = 2300;
    int pos3 = 3500;

    // Position of the arm when it's down
    int downpos = 0;
    //elevator motors
    //  DcMotor elevatorRight = hardwareMap.dcMotor.get("elevatorRight");
    //  DcMotor elevatorLeft = hardwareMap.dcMotor.get("elevatorLeft");

    @Override
    public void runOpMode() throws InterruptedException {
        // Position of the arm when it's lifted

        DcMotor elevatorRight = hardwareMap.dcMotor.get("elevatorRight");
        DcMotor elevatorLeft = hardwareMap.dcMotor.get("elevatorLeft");

        elevatorLeft.setTargetPosition(elevatorLeft.getCurrentPosition());
        elevatorRight.setTargetPosition(elevatorRight.getCurrentPosition());
        elevatorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elevatorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elevatorRight.setPower(0.5);
        elevatorLeft.setPower(0.5);


        elevatorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elevatorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        elevatorLeft.setDirection(DcMotor.Direction.REVERSE);
        elevatorRight.setDirection(DcMotor.Direction.REVERSE);


        waitForStart();

        while (opModeIsActive()) {
            // all the way down
            if (gamepad1.dpad_down) {
                /*elevatorRight.setTargetPosition(0);
                elevatorLeft.setTargetPosition(0);
                elevatorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevatorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevatorRight.setPower(0.5);
                elevatorLeft.setPower(0.5);*/

                elevator(0, 0.5);
            }

            else if (gamepad1.dpad_left) {
                elevatorRight.setTargetPosition(2300);
                elevatorLeft.setTargetPosition(2300);
                elevatorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevatorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevatorRight.setPower(0.5);
                elevatorLeft.setPower(0.5);
            }

            else if (gamepad1.dpad_right) {
                elevatorRight.setTargetPosition(3500);
                elevatorLeft.setTargetPosition(3500);
                elevatorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevatorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevatorRight.setPower(0.5);
                elevatorLeft.setPower(0.5);
            }

            // Get the current position of the elevator
            double elevatorRightPos = elevatorRight.getCurrentPosition();
            double elevatorLeftPos = elevatorLeft.getCurrentPosition();

            // Show the position of the elevator on telemetry
            telemetry.addData("Right elevator Encoder Position", elevatorRightPos);
            telemetry.addData("Left elevator Encoder Position", elevatorLeftPos);

            telemetry.update();
        }
    }

    public void elevator(int pos, double power) {
        elevatorRight.setTargetPosition(pos);
        elevatorLeft.setTargetPosition(pos);
        elevatorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elevatorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elevatorRight.setPower(power);
        elevatorLeft.setPower(power);
    }
}