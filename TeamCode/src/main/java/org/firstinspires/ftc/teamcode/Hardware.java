package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Hardware{

    public DcMotor leftMotor;
    public DcMotor rightMotor;

    public HardwareMap hardwareMap;

    public Hardware(OpMode opMode){

        leftMotor = opMode.hardwareMap.dcMotor.get("leftMotor");
        rightMotor = opMode.hardwareMap.dcMotor.get("rightMotor");

    }

}
