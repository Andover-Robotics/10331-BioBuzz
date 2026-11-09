package org.firstinspires.ftc.teamcode.Auto;
import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.pedropathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import static com.pedropathing.api.Paths.*;
import com.pedropathing.paths.Path;
import com.pedropathing.ivy.Scheduler;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
public class ExampleAuto extends OpMode {
    //we first need a follower
    //constants need to be configured before follower is functional

    private Follower follower;
    private final PoseFactory p = PoseFactory.degrees();


    //poses

    private final Pose startPose = p.of(58.9, 7.8, 90);
    private final Pose park = p.of(6.6, 106, 0);
    private final Pose shoot1and2 = p.of(58.9,19.2,90);
    private final Pose garden = p.of(8.2,10.5,270);
    private final Pose flower = p.of(46.93,132.3,90);
    private final Pose shoot3 = p.of(57.51,117.76,270);

    //path methods

    private Path shoot1and2() {
        return line(startPose, shoot1and2).linear(startPose, shoot1and2);
    }

    private Path garden() {
        return line(shoot1and2, garden).linear(shoot1and2, garden);
    }

    private Path shoot1and2() {
        return line(garden, shoot1and2).linear(garden, shoot1and2);
    }

    private Path flower() {
        return curve(shoot1and2,flower).linear(shoot1and2,flower);
    }
    private Path shoot3() {
        return line(flower, shoot3).linear(flower, shoot3);
    }
    private Path park() {
        return line(shoot3,park).linear(shoot3, park);
    }

    private Command autoRoutine() {
        return sequential(
                follow(follower, shoot1and2()),
                follow(follower, garden()),
                follow(follower, shoot1and2()),
                follow(follower, flower()),
                follow(follower, shoot3()),
                follow(follower, park())

        );
    }

    @Override
    public void init() {
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(startPose);
        follower.update();
    }

    @Override
    public void start() {
            schedule(autoRoutine());


    }
    @Override
    public void loop() {
        follower.update();
        Scheduler.execute();


        }
    }



}
