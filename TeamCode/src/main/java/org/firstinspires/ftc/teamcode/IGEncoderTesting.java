package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class IGEncoderTesting extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Find a motor in the hardware map named "Arm Motor"
        DcMotor fr = hardwareMap.dcMotor.get("fr");
        DcMotor fl = hardwareMap.dcMotor.get("fl");
        DcMotor br = hardwareMap.dcMotor.get("br");
        DcMotor bl = hardwareMap.dcMotor.get("bl");


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

        waitForStart();

        while (opModeIsActive()) {
            // Get the current position of the motor
            int frpos = fr.getCurrentPosition();
            int flpos = fl.getCurrentPosition();
            int brpos = br.getCurrentPosition();
            int blpos = bl.getCurrentPosition();

            // Show the position of the motor on telemetry
            telemetry.addData("Right elevator Position", fr);
            telemetry.addData("Left elevator Position", fl);
            telemetry.addData("Right elevator Position", br);
            telemetry.addData("Left elevator Position", bl);
            telemetry.update();
        }
    }
}