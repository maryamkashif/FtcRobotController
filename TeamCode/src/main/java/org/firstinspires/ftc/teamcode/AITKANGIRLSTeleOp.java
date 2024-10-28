package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.State;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.AnalogInput;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import android.graphics.Color;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorControllerEx;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;

import android.app.Activity;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import android.graphics.Color;
import android.view.View;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;


@TeleOp()
public class AITKANGIRLSTeleOp extends LinearOpMode {

    //motor and servos
    DcMotorEx intake, Lextend, Rextend, arm, fl, fr, bl, br;
    // NormalizedColorSensor colorSensor;
    AnalogInput ranger;
    Servo wrist, hooks;
    PIDFController armPIDF = new PIDFController(0.002, 0, 0, 0);

    double strafemulti;
    double forwardmulti;
    double turnmulti;
    //targets for motors
    int armTarget = 0;
    int extendTarget = 0;
    double wristTarget = 0;

    boolean holdingTarget = false;
    boolean resetEncoder = false;
    boolean gameElement = false;
    boolean clipIntake = false;
    boolean hookOutAfterRelease = false;

    double intakePower = 0;


    int intakeOverCurrentCycles = 0;


    @Override
    public void runOpMode() {

        intake = hardwareMap.get(DcMotorEx.class, "intake");
        Lextend = hardwareMap.get(DcMotorEx.class, "EL");
        Rextend = hardwareMap.get(DcMotorEx.class, "ER");
        arm = hardwareMap.get(DcMotorEx.class, "arm");
        fl = hardwareMap.get(DcMotorEx.class, "fl");
        fr = hardwareMap.get(DcMotorEx.class, "fr");
        bl = hardwareMap.get(DcMotorEx.class, "bl");
        br = hardwareMap.get(DcMotorEx.class, "br");
        // colorSensor = hardwareMap.get(NormalizedColorSensor.class, "color");
        wrist = hardwareMap.servo.get("wrist");

        Lextend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Rextend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intake.setPower(0);

        //reverse drivetrainmotors
        fr.setDirection(DcMotorEx.Direction.REVERSE);
        br.setDirection(DcMotorEx.Direction.REVERSE);

        Rextend.setDirection(DcMotorEx.Direction.REVERSE);

        Lextend.setTargetPosition(Lextend.getCurrentPosition()+5);
        Lextend.setPower(1.0);
        Lextend.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        Lextend.setTargetPosition(0);

        Rextend.setTargetPosition(Rextend.getCurrentPosition()+5);
        Rextend.setPower(1.0);
        Rextend.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        Rextend.setTargetPosition(0);



        waitForStart();


        while(opModeIsActive()) {

            // NormalizedRGBA colors = colorSensor.getNormalizedColors();

            gameElement = false;

            double intakePower = 0;

            //game element detection
            // if(((DistanceSensor) colorSensor).getDistance(DistanceUnit.CM) < 2.0)
            // {
            //     gameElement = true;
            // }

            holdingTarget = false;


            if(gamepad1.a || gamepad2.a) { //HOME
                armTarget=0;
                intakePower=0;
                extendTarget = 0;
                wristTarget=0;
            }
            if(gamepad1.x || gamepad2.x) { //EXTENDS RESET
                extendTarget = 0;
            }
            if(gamepad1.dpad_up || gamepad2.dpad_up) { //HIGHBASKET
                extendTarget = 2200;
                armTarget=300;
                wristTarget=1;
            }
            if(gamepad1.b || gamepad2.b) { //LOWBASKET
                extendTarget = 1200;
                wristTarget=0.9;
            }
            if(gamepad1.y || gamepad2.y) { //LOWBASKET
                wrist.setPosition(0);
            }

            if(gamepad1.right_bumper || gamepad2.right_bumper) { //PRE-INTAKE
                armTarget=600;
                wristTarget=0.5;
            }


            if (extendTarget < 0) extendTarget = 0;

            // //Intake Code
            // if(colors.blue > 0.1)
            // {
            //     intakePower = -0.6;
            // }

            if((gamepad1.left_trigger > 0.05) || (gamepad2.left_trigger > 0.05)) //OUTTAKE
            {
                intakePower = -0.6;
            }

            if((gamepad1.right_trigger > 0.05) || (gamepad2.right_trigger > 0.05) || clipIntake) //INTAKE
            {
                armTarget=800;
                wristTarget=0.9;
                intakePower = 1;
            }

            intake.setPower(intakePower);

            if(!holdingTarget)
            {
                armMotionLimits();

                // telemetry.addData("ArmPIDF", Math.max(-1, Math.min(1, armPIDF.calculate(arm.getCurrentPosition(), armTarget))));
                Rextend.setTargetPosition(extendTarget);
                Lextend.setTargetPosition(extendTarget);
                intake.setPower(intakePower);
                wrist.setPosition(wristTarget);
            }


            strafemulti = 1;
            forwardmulti = 1;
            turnmulti = 1;

            //Drivetrain slower
            if (extendTarget > 2000){
                strafemulti = 0.5;
                forwardmulti = 0.5;
                turnmulti = 0.5;
            }


            //DRIVE CODE
            double leftx = -strafemulti*(gamepad1.left_stick_x); //strafe left and right
            double lefty = -forwardmulti*(gamepad1.left_stick_y + gamepad1.right_stick_y); //forward and backward
            double rightx = -turnmulti*(gamepad1.right_stick_x); //turn left and right

            fl.setPower(lefty - leftx - rightx);
            fr.setPower(lefty + leftx + rightx);
            bl.setPower(lefty + leftx - rightx);
            br.setPower(lefty - leftx + rightx);

            //Reset Encoders
            if(gamepad1.back)
            {
                Lextend.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
                Rextend.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
                arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);

                Lextend.setPower(0.3);
                Rextend.setPower(-0.3);
                arm.setPower(0.2);
                resetEncoder = true;
            }

            if(resetEncoder && !gamepad1.back)
            {
                Lextend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                Rextend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                Lextend.setTargetPosition(0);
                Rextend.setTargetPosition(0);
                extendTarget = 0;
                Lextend.setPower(1.0);
                Rextend.setPower(1.0);
                Lextend.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                Rextend.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

                arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                arm.setTargetPosition(0);
                armTarget = 0;
                arm.setPower(1.0);
                arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                resetEncoder = false;
            }


            if(true) {

                //telemetry.addData("Climbstate: ", climbState);
                telemetry.addData("Lextend Pos: ", Lextend.getCurrentPosition());
                telemetry.addData("Rextend Pos: ", Rextend.getCurrentPosition());
                //telemetry.addData("Climb Target: ", climbl.getTargetPosition());

                telemetry.addData("Arm PIDF: ", armPIDF.calculate(arm.getCurrentPosition(), armTarget));
                telemetry.addData("Motor Current (Amps):", intake.getCurrent(CurrentUnit.AMPS));
                telemetry.addData("Wrist Pos", wrist.getPosition());

                telemetry.update();
            }
        }
    }

    public void armMotionLimits() {

        if (Math.abs(armTarget-arm.getCurrentPosition()) < 5) {
            arm.setPower(0.2);
        } else {
            if (armTarget >= 400){

                if (arm.getCurrentPosition() <= 400) {
                    arm.setPower(Math.max(-0.7, Math.min(0.7, armPIDF.calculate(arm.getCurrentPosition(), armTarget))));
                } else {
                    arm.setPower(Math.max(-0.2, Math.min(0.2, armPIDF.calculate(arm.getCurrentPosition(), armTarget))));
                }

            }
            if (armTarget < 400) {
                if (arm.getCurrentPosition() >= 450) {
                    arm.setPower(Math.max(-1, Math.min(1, armPIDF.calculate(arm.getCurrentPosition(), armTarget))));
                } else {
                    arm.setPower(Math.max(-0.3, Math.min(0.3, armPIDF.calculate(arm.getCurrentPosition(), armTarget))));
                }
            }
        }
    }
}