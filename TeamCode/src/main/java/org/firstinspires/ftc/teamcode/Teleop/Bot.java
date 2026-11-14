package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Shooter;

public class Bot {
    //add all parts of bot here
    public static Bot instance;
    public OpMode opMode;
    public Shooter shooter;
    public Intake intake;
    public Bot(OpMode opMode){
        this.opMode = opMode;
        this.shooter = new Shooter(opMode);
        this.intake = new Intake(opMode);

    }

    public void teleOpIntake(){
        //standard intaking method for teleop
    }

    public void teleOpShoot(){
        //standard shooting for teleop
    }

    public void teleOpIntakeFromFlower(){
        //intaking from the flower with the dancer mechanism
    }

    public void teleOpPrep(){
        //prep robot during init for teleop
    }

    public void autoPrep(){
        //prep robot during init for auto
    }


}
