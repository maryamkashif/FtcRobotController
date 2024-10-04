package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class IGEncoderElevatorTesting extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Position of the arm when it's lifted
        int pos1 = 1500;
        int pos2 = 2300;
        int pos3 = 3500;

        // Position of the arm when it's down
        int downpos = 0;

        // elevators
        DcMotor elevatorRight = hardwareMap.dcMotor.get("elevatorRight");
        DcMotor elevatorLeft = hardwareMap.dcMotor.get("elevatorLeft");


        // Reset the motor encoder so that it reads zero ticks
        elevatorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevatorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Turn the motor back on, required if you use STOP_AND_RESET_ENCODER
        elevatorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        elevatorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            // all the way down
            if (gamepad1.dpad_down) {
                elevatorRight.setTargetPosition(downpos);
                elevatorLeft.setTargetPosition(downpos);
                elevatorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevatorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevatorRight.setPower(0.5);
                elevatorLeft.setPower(0.5);
            }

            else if (gamepad1.dpad_left) {
                elevatorRight.setTargetPosition(pos1);
                elevatorLeft.setTargetPosition(pos1);
                elevatorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevatorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevatorRight.setPower(0.5);
                elevatorLeft.setPower(0.5);
            }

            else if (gamepad1.dpad_up) {
                elevatorRight.setTargetPosition(pos2);
                elevatorLeft.setTargetPosition(pos2);
                elevatorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevatorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevatorRight.setPower(0.5);
                elevatorLeft.setPower(0.5);
            }

            else if (gamepad1.dpad_right) {
                elevatorRight.setTargetPosition(pos3);
                elevatorLeft.setTargetPosition(pos3);
                elevatorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevatorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevatorRight.setPower(0.5);
                elevatorLeft.setPower(0.5);
            }

            // Get the current position of the armMotor
            double elevatorRightPos = elevatorRight.getCurrentPosition();
            double elevatorLeftPos = elevatorLeft.getCurrentPosition();

            // Get the target position of the armMotor
            //double desiredPosition = armMotor.getTargetPosition();

            // Show the position of the armMotor on telemetry
            telemetry.addData("Right elevator Encoder Position", elevatorRightPos);
            telemetry.addData("Left elevator Encoder Position", elevatorLeftPos);

            // Show the target position of the armMotor on telemetry
            //telemetry.addData("Desired Position", desiredPosition);

            telemetry.update();
        }
    }
}