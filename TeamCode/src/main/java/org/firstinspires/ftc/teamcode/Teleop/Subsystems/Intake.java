package org.firstinspires.ftc.teamcode.Teleop.Subsystems;

import static com.pedropathing.ivy.commands.Commands.instant;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.pedropathing.ivy.*;


@Config
public class Intake {
    //awesome intake stuff
    public final MotorEx intakeMotor;

    //Servo positions
    public static double flowerIntakePos = 0.0;
    public static double nectarBlockPos = 0.0;
    public static double raisedPos = 0.0;
    public static double closeGatePos = 0.55;
    public static double openGatePos = 0.80;

    public static double power;
    //this robot is going to dance ehehehehehe
    public Servo dancingServo;
    public Servo gate;
    public Intake (OpMode opMode){
        intakeMotor = new MotorEx(opMode.hardwareMap, "intake", Motor.GoBILDA.BARE);
        dancingServo = opMode.hardwareMap.servo.get("dancingServo");
        gate = opMode.hardwareMap.servo.get("gate");

    }
    //tune these positions later
    public void raiseTheRoof() {dancingServo.setPosition(0);}

    public void limbo(){dancingServo.setPosition(0);}
    public void getOnTheDanceFloor(){dancingServo.setPosition(0);}
    public void openGate() {gate.setPosition(openGatePos);}
    public void closeGate() {gate.setPosition(closeGatePos);}


    public void setPower(double power){intakeMotor.set(power);}

    public void reverseIntake(double power){intakeMotor.set(-power);}
    public void stopIntake(){intakeMotor.set(0);}

    //??? how do commands work????
//    public Command closeGate() {
//        return instant(() -> gate.setPosition(closeGatePos));
//    }
//    public Command openGate() {
//        return instant(() -> gate.setPosition(openGatePos));
//    }
//
//    public Command stopIntake(){
//        return instant(() -> intakeMotor.set(0));
//    }
//
//    public Command reverseIntake(double power){
//        return instant(() -> intakeMotor.set(-power));
//    }


}
