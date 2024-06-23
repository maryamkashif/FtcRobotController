package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class telemetyTesting extends OpMode {

    DcMotor motor1;

    @Override
    public void init() {

        telemetry.addData("Initialization:", "Successful");
        telemetry.update();


    }

    @Override
    public void loop() {

        double value = gamepad1.left_stick_y;
        telemetry.addData("Gamepad 1 y: ", value);

    }




}
