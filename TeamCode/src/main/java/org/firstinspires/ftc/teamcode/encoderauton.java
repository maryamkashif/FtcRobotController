package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous

public class encoderauton extends LinearOpMode {

    // Find a motor in the hardware map named "Arm Motor"
    DcMotor fr = hardwareMap.dcMotor.get("fr");
    DcMotor fl = hardwareMap.dcMotor.get("fl");
    DcMotor br = hardwareMap.dcMotor.get("br");
    DcMotor bl = hardwareMap.dcMotor.get("bl");

    @Override
    public void runOpMode() throws InterruptedException {



        // Reset the motor encoder so that it reads zero ticks
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Turn the motor back on, required if you use STOP_AND_RESET_ENCODER
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        fr.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
            // Get the current position of the motor

            goTo(800, 1);

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

        fr.setPower(power);
        fl.setPower(power);
        br.setPower(power);
        bl.setPower(power);

    }
}