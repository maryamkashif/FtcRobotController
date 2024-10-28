package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous

public class auton extends LinearOpMode {

    // Find a motor in the hardware map named "Arm Motor"

    DcMotor fr;
    DcMotor fl;
    DcMotor br;
    DcMotor bl;
    Arm arm;
    Intake intake;

    @Override
    public void runOpMode() throws InterruptedException {

        arm = new Arm();
        arm.init(hardwareMap);

        intake = new Intake();
        intake.init(hardwareMap);

        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        br = hardwareMap.dcMotor.get("br");
        bl = hardwareMap.dcMotor.get("bl");


        // Reset the motor encoder so that it reads zero ticks
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        fr.setTargetPosition(0);
        fl.setTargetPosition(0);
        br.setTargetPosition(0);
        bl.setTargetPosition(0);

        double power = 0.3;

        fr.setPower(power);
        fl.setPower(power);
        br.setPower(power);
        bl.setPower(power);

        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);

        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        while (opModeIsActive()) {
            // Get the current position of the motor

            // drive up
            while (fl.getCurrentPosition() < 700) {
                goTo(700, 0.3);
            }
            // wrist up
            arm.pivot.setPosition(0.4);
            // go forward
            while (fl.getCurrentPosition() < 800) {
                goTo(800, 0.3);
            }
            // go back a bit
            while (fl.getCurrentPosition() > 700) {
                goTo(700, 0.3);
            }
            // take out specimen
            intake.outtake();
            // go back to wall
            goTo(0, 0.3);


            // Show the position of the motor on telemetry
            telemetry.addData("front right", fr.getCurrentPosition());
            telemetry.addData("front left", fl.getCurrentPosition());
            telemetry.addData("back right", br.getCurrentPosition());
            telemetry.addData("back left", bl.getCurrentPosition());
            telemetry.update();
        }
    }

    public void goTo(int pos, double power) {
        fr.setTargetPosition(pos);
        fl.setTargetPosition(pos);
        br.setTargetPosition(pos);
        bl.setTargetPosition(pos);

    }
}