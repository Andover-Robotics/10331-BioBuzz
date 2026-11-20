package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.pedropathing.ivy.*;

import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Teleop.Subsystems.PIDController;
import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Shooter;

public class Bot {
    //add all parts of bot here
    public static Bot instance;
    public OpMode opMode;
    public Shooter shooter;
    public Intake intake;

    public PIDController pidController;

    public static double sleeptime;
    public Bot(OpMode opMode){
        this.opMode = opMode;
        this.shooter = new Shooter(opMode);
        this.intake = new Intake(opMode);

    }

    public void teleOpIntake(){
        //intake.closeGate();
        intake.setPower(1);
    }


    public void teleOpShoot(){
        //standard shooting for teleop
    }

    public void teleOpIntakeFromFlower(){
        //intaking from the flower with the dancer mechanism
    }

    public void teleOpPrep(){

    }

    //commands


}
