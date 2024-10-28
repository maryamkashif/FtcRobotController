package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class IGEncoderElevatorTesting extends LinearOpMode {

    //elevator motors
    DcMotor elevatorRight = hardwareMap.dcMotor.get("elevatorRight");
    DcMotor elevatorLeft = hardwareMap.dcMotor.get("elevatorLeft");

    RobotDrive bot;

    @Override
    public void runOpMode() throws InterruptedException {
        // Position of the arm when it's lifted
        int pos1 = 1500;
        int pos2 = 2300;
        int pos3 = 3500;
        // Position of the arm when it's down
        int downpos = 0;

        /*// Reset the motor encoder so that it reads zero ticks
        elevatorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevatorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Turn the motor back on, required if you use STOP_AND_RESET_ENCODER
        elevatorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        elevatorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);*/

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

        RobotDrive bot = new RobotDrive();

        waitForStart();

        while (opModeIsActive()) {
            // all the way down
            if (gamepad1.dpad_down) {
                elevator(downpos, 0.5);
            }

            else if (gamepad1.dpad_left) {
                elevator(pos1, 0.5);
            }

            else if (gamepad1.dpad_up) {
                elevator(pos2, 0.5);
            }

            else if (gamepad1.dpad_right) {
                elevator(pos3, 0.5);
            }

            double jx = -gamepad1.left_stick_y;
            double jy = -gamepad1.left_stick_x;
            double jw = -gamepad1.right_stick_x;

            bot.driveXYW(jx, jy, jw, 1);

            // Get the current position of the armMotor
            double elevatorRightPos = elevatorRight.getCurrentPosition();
            double elevatorLeftPos = elevatorLeft.getCurrentPosition();

            // Show the position of the armMotor on telemetry
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