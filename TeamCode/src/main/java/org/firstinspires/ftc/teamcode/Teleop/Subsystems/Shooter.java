package org.firstinspires.ftc.teamcode.Teleop.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.arcrobotics.ftclib.controller.PIDController;

@Config
public class Shooter {

    public static double kP = 0;
    public static double kI = 0;
    public static double kD = 0;
    public static double kF = 0;
    public static double targetRPM = 0;

    public static double toleranceRPM = 50;

    public final MotorEx shooterMotor1;
    private final VoltageSensor voltageSensor;
    public final PIDController shooterPID;
    public boolean isEnabled = false;
    public double currentRPM = 0;
    public double shooterPower = 0;

    //gobilda motor 28 ticks per revolution
    public static final double TICKS_PER_REV = 28.0;



    public Shooter(OpMode opMode){
        shooterMotor1 = new MotorEx(opMode.hardwareMap, "shooter", Motor.GoBILDA.BARE);
        shooterMotor1.setRunMode(Motor.RunMode.RawPower);

        voltageSensor = opMode.hardwareMap.voltageSensor.iterator().next();

        shooterPID = new PIDController(kP, kI, kD);

    }

    public void setPower(double power){
        shooterMotor1.set(power);
    }
    public void enable(){
        isEnabled = true;
    }

    public void disable(){
        isEnabled = false;
        setPower(0);
        shooterPID.reset();
    }

    public void setTargetRPM(double rpm){
        targetRPM = rpm;
    }

    public double getRPM(){
        return currentRPM;
    }

    public boolean checkSpeed(){
        return Math.abs(targetRPM-getRPM())<= toleranceRPM;
    }
    public double getTargetRPM(){
        return targetRPM;
    }
    public void periodic(){
        double ticksPerSecond = shooterMotor1.getVelocity();
        currentRPM = ticksPerSecond*60/TICKS_PER_REV;

        if (!isEnabled){
            setPower(0);
            shooterPID.reset();
        }

        shooterPID.setPID(kP,kI,kD);
        double pidOutput = shooterPID.calculate(currentRPM, targetRPM);
        double ff = kF * targetRPM; //whaaaaaaattt
        setPower(pidOutput + ff);




    }

    public void reset(){
        disable();
        targetRPM = 0;
        shooterPower = 0;
        shooterMotor1.stopAndResetEncoder();
    }


}
