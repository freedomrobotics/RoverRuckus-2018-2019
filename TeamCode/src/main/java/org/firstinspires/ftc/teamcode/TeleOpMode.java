package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@TeleOp(name="TeleOpMode", group="FHSROBOTICS")
public class TeleOpMode extends OpMode {

    Hardware hardware = new Hardware(this);

    // Code to run ONCE when the driver hits INIT //
    @Override
    public void init() {

        hardware.leftMotor = hardwareMap.dcMotor.get("leftMotor");
        hardware.rightMotor = hardwareMap.dcMotor.get("rightMotor");

    }

    // Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY //
    @Override
    public void init_loop(){



    }

    // Code to run ONCE when the driver hits PLAY //
    @Override
    public void start() {



    }

    // Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP //
    @Override
    public void loop() {

        hardware.leftMotor.setPower(gamepad1.left_stick_y);
        hardware.rightMotor.setPower(gamepad1.right_stick_y);

    }

    // Code to run ONCE after the driver hits STOP //
    @Override
    public void stop() {

        hardware.leftMotor.setPower(0);
        hardware.rightMotor.setPower(0);

    }

}