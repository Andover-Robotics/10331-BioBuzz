
package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.api.Paths;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.pedropathing.paths.Path;

import org.firstinspires.ftc.teamcode.pedro.Constants;


    @Autonomous
    public class Auto_Testing extends OpMode {
        private Follower follower;

        public enum PathState {
            //STARTPOS_ENDPOS
            //DRIVE > MOVEMENT STATE
            //SCORE > ATTEMPT TO SCORE
            DRIVE_STARTPOS_SHOOTPOS,
            SHOOT_PRELOAD
        }
        org.firstinspires.ftc.teamcode.Auto.autoTrial.PathState pathState;
        private final Pose startPose = new Pose(9.3228, 105.7541, Math.toRadians(0));
        private final Pose shootPose = new Pose(9.3228, 135, Math.toRadians(90));
        private final Pose shootPoseControl1 = new Pose (10, 105, Math.toRadians(0));

        public Path driveStartPosShootPos() {
            return Paths.line(startPose, shootPose).linear(startPose, shootPose);
        }
        public void statePathUpdate(){
            switch(pathState) {
                case DRIVE_STARTPOS_SHOOTPOS:
                    follower.follow(driveStartPosShootPos());
                    pathState = org.firstinspires.ftc.teamcode.Auto.autoTrial.PathState.SHOOT_PRELOAD;
                    break;
                case SHOOT_PRELOAD:
                    if (!follower.isBusy()){
                        telemetry.addLine("Done Path 1");
                    }
                    break;

            }
        }


        @Override
        public void init(){
            follower = Constants.create(hardwareMap);
            pathState = org.firstinspires.ftc.teamcode.Auto.autoTrial.PathState.DRIVE_STARTPOS_SHOOTPOS;
            follower.setPose(startPose);

        }



        @Override
        public void loop(){
            follower.update();
            statePathUpdate();
            telemetry.addData("x", follower.pose().x());
            telemetry.addData("y", follower.pose().y());
            telemetry.addData("heading", follower.pose().heading());

        }

    }
