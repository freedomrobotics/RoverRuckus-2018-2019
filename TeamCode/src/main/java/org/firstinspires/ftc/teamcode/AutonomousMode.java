package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="AutonomousMode", group="FHSROBOTICS")
public class AutonomousMode extends OpMode{

    Hardware hardware;
    MineralDetector mineralDetector;

    @Override
    public void init() {

        hardware = new Hardware(this);

        mineralDetector = new MineralDetector();
        mineralDetector.init(hardware.hardwareMap);

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



    }

    // Code to run ONCE after the driver hits STOP //
    @Override
    public void stop() {



    }

}
