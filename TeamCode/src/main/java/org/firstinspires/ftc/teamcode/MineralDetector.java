package org.firstinspires.ftc.teamcode;

// Class for detecting relative position of silver and gold minerals during Autonomous Mode

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import java.util.List;

import static org.firstinspires.ftc.robotcore.external.tfod.TfodRoverRuckus.LABEL_GOLD_MINERAL;
import static org.firstinspires.ftc.robotcore.external.tfod.TfodRoverRuckus.LABEL_SILVER_MINERAL;
import static org.firstinspires.ftc.robotcore.external.tfod.TfodRoverRuckus.TFOD_MODEL_ASSET;

public class MineralDetector{

    private VuforiaLocalizer vuforiaLocalizer;
    private TFObjectDetector tensorFlowObjectDetector;

    private List<Recognition> recognitions;
    private String goldMineralPosition;              // LEFT, RIGHT, or CENTER

    public void init(HardwareMap hardwareMap){

        initVuforia();
        initTFOD(hardwareMap);

    }

    private void initVuforia(){

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = "AR/UiTP/////AAABmXegpH+VOkqwmcJHE6qjcNEeVNJdDFIsTB98PfQE" +
                "mYdlAq9/3kHOjI3B2oR8LG6T7p5CDPPQ/gPwFxqyIXwC/uINy8M1kFHKd2CbhIyzIBIftGcAruUARt/" +
                "46BzDYorqGCGxQuctYqmacwsQOWZib0OFXRah791I9dX42M3lcgGegNpJ1/B2b0NeDNpjDFl4uE/nb5" +
                "P6X4O3OSgeZruDEaa/M0j/rIYxaMoUKJ3PS9f7YLLe4v8IV9vM641utp/1hr9d16NL25mSfEVWLcxsA" +
                "NkUFjmTupD4WsPVavaVfmk/VEgzJHKCmbuCFccYBHBXEBZhD2c5pHa21iw4YarPH7smIeDZUaHeeoV1" +
                "3/Tiiinf";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        vuforiaLocalizer = ClassFactory.getInstance().createVuforia(parameters);

    }

    private void initTFOD(HardwareMap hardwareMap){

        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId",
                "id",
                hardwareMap.appContext.getPackageName()
        );

        TFObjectDetector.Parameters parameters = new TFObjectDetector.Parameters(tfodMonitorViewId);

        parameters.minimumConfidence = 0.75;

        tensorFlowObjectDetector = ClassFactory.getInstance().createTFObjectDetector(parameters, vuforiaLocalizer);

        tensorFlowObjectDetector.loadModelFromAsset(
                TFOD_MODEL_ASSET,
                LABEL_GOLD_MINERAL,
                LABEL_SILVER_MINERAL
        );

    }

    public void enable(){

        tensorFlowObjectDetector.activate();

    }

    public void disable(){

        tensorFlowObjectDetector.deactivate();

    }

    public String getGoldRelativePosition(){

        recognitions = tensorFlowObjectDetector.getRecognitions();

        if(recognitions.size() == 3) {

            int goldMineralX = -1;
            int silverMineral1X = -1;
            int silverMineral2X = -1;

            for (Recognition recognition : recognitions) {

                if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {

                    goldMineralX = (int) recognition.getLeft();

                } else if (silverMineral1X == -1) {

                    silverMineral1X = (int) recognition.getLeft();

                } else {

                    silverMineral2X = (int) recognition.getLeft();

                }
            }

            if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {

                if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {

                    goldMineralPosition = "LEFT";

                } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {

                    goldMineralPosition = "RIGHT";

                } else {

                    goldMineralPosition = "CENTER";

                }
            }

        }

        return goldMineralPosition;

    }
}
