package org.firstinspires.ftc.teamcode.Auto;

import static com.pedropathing.ivy.pedro.PedroCommands.follow;


import com.pedropathing.api.Paths;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import com.pedropathing.paths.Path;


@Autonomous
public class autoTrial extends OpMode {
    private Follower follower;
    //--------------------------FLYWHEEL SETUP--------------------------------
    private FlywheelLogic shooter = new FlywheelLogic();
    private boolean shotsTriggered = false;

   //------------------------ESTABLISHING PATH STATES-------------------------
    public enum PathState {

        //DRIVE > MOVEMENT STATE
        //SHOOT > ATTEMPT TO SCORE
        DRIVE_STARTPOS_SHOOTPOS,
        SHOOT_PRELOAD,
        DRIVE_SHOOTPOS_GARDENPOS,
        DRIVE_GARDENPOS_SHOOTPOS,
        SHOOT_GARDEN,
        DRIVE_SHOOTPOS_ENDPOS
    }

    //-----------------ESTABLISHING POINTS ON THE MAP-------------------------
    //This path is for the red alliance starting at the upper left park position
    PathState pathState;
    private final Pose startAndEndPose = new Pose(9.3228, 105.7541, Math.toRadians(0));
    private final Pose shootPose = new Pose(58.8965, 19.2005, Math.toRadians(90));
    private final Pose shootPoseControl1 = new Pose (23.5346, 37.7652, Math.toRadians(0));
    private final Pose gardenPose = new Pose (58.8965, 19.2005, Math.toRadians(90));
    private final Pose endPoseControl1 = new Pose(18.4092, 35.3852, 0);


    //------------------------BUILDING PATHS--------------------------------------------
    public Path driveStartPosShootPos() {
        return Paths.curve(startAndEndPose, shootPoseControl1, shootPose).linear(startAndEndPose, shootPose);
    }
    public Path driveShootPosGardenPos() {
        return Paths.line(shootPose,gardenPose).linear(shootPose,gardenPose);
    }
    public Path driveGardenPosShooterPos() {
        return Paths.line(gardenPose, shootPose).linear(gardenPose,shootPose);
    }
    public Path driveShootPosEndPos() {
        return Paths.curve(shootPose, endPoseControl1, startAndEndPose).linear(shootPose, startAndEndPose);
    }

//--------------------SWITCHING BETWEEN PATH STATES--------------------------------
    public void statePathUpdate() {
        switch (pathState) {
            case DRIVE_STARTPOS_SHOOTPOS:
                follower.follow(driveStartPosShootPos());
                pathState = PathState.SHOOT_PRELOAD;
                break;
            case SHOOT_PRELOAD:
                if (!follower.isBusy()) {
                    //meaning that robot is in IDLE flywheelState
                    if (!shotsTriggered) {
                        shooter.fireShots(4);
                        shotsTriggered = true;
                    } else if (shotsTriggered && !shooter.isBusy()) {
                        //shots are done and we can transition states
                        pathState = PathState.DRIVE_SHOOTPOS_GARDENPOS;
                    }
                }
                break;
                //TODO need to add in an intake state right here
            case DRIVE_SHOOTPOS_GARDENPOS:
                follower.follow(driveShootPosGardenPos());
                pathState = PathState.DRIVE_GARDENPOS_SHOOTPOS;
                break;
            case DRIVE_GARDENPOS_SHOOTPOS:
                follower.follow(driveGardenPosShooterPos());
            case SHOOT_GARDEN:
                if (!follower.isBusy()) {
                    //meaning that robot is in IDLE flywheelState
                    if (!shotsTriggered) {
                        shooter.fireShots(4);
                        shotsTriggered = true;
                    } else if (shotsTriggered && !shooter.isBusy()) {
                        //shots are done and we can transition states
                        pathState = PathState.DRIVE_SHOOTPOS_ENDPOS;
                    }
                }
                break;
                //TODO might want to add intake here so that ball has 4 preloaded balls for Teleop
            case DRIVE_SHOOTPOS_ENDPOS:
                follower.follow(driveShootPosEndPos());
                break;
        }
    }

        public void setPathState(PathState newState){
            pathState = newState;
            shotsTriggered = false;
        }
//-------------------------------------INITILIZATION VALUES----------------------------------
        @Override
        public void init () {
            follower = Constants.create(hardwareMap);
            pathState = PathState.DRIVE_STARTPOS_SHOOTPOS;
            follower.setPose(startAndEndPose);
            shooter.init(hardwareMap);
        }

//----------------------------UPDATE THESE VALUES IN A LOOP----------------------------------
        @Override
        public void loop() {
            follower.update();
            shooter.update();
            statePathUpdate();
            telemetry.addData("x", follower.pose().x());
            telemetry.addData("y", follower.pose().y());
            telemetry.addData("heading", follower.pose().heading());

        }

}
