package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class FlywheelLogic {
    private Servo gateServo;
    private DcMotorEx flywheel_1;
    private DcMotorEx flywheel_2;
    private ElapsedTime stateTimer = new ElapsedTime();

    //--------------------FLYWHEEL CAN BE IN 1 OF 4 STAGES-------------------------------
    private enum FlywheelState {
        IDLE,
        SPIN_UP,
        LAUNCH,
        RESET_GATE,
    }
    private FlywheelState flywheelState;

    //---------------GATE LOGIC---------------------------------------
    //TODO need to tune servo gate values for V1.5
    private double GATE_CLOSED_ANGLE = 0;
    private double GATE_OPEN_ANGLE= 90;
    private double GATE_OPEN_AND_CLOSE_TIME = 0.4;

    //----------------FLYWHEEL LOGIC---------------------------------
    private int shotsRemaining = 0;
    private double flywheelVelocityRPM = 0;
    private double TARGET_FLYWHEEL_RPM= 3000;
    private double MAX_SPIN_UP_TIME = 2;



//---------------DEFINING CONVERSION METHODS BETWEEN RPM AND TICKSPERSEC----------------------
    private final double TICKS_PER_REV = 537.7;
    private double rpmToTicksPerSec(double rpm) {

        return (rpm * TICKS_PER_REV) / 60.0;
    }

    private double ticksPerSecToRpm(double ticksPerSec) {
        return (ticksPerSec * 60.0) / TICKS_PER_REV;
    }

//------------------------------INITIALIZE---------------------------------------
    //TODO names must match the Driver Station configuration char for char
    public void init(HardwareMap hwMap) {
        gateServo = hwMap.get(Servo.class,"servo");
        flywheel_1 = hwMap.get(DcMotorEx.class, "flywheel_1");
        flywheel_2 = hwMap.get(DcMotorEx.class, "flywheel_2");

        flywheel_1.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        flywheel_2.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        flywheelState = FlywheelState.IDLE;
        flywheel_1.setVelocity(0);
        flywheel_2.setVelocity(0);
        gateServo.setPosition(GATE_CLOSED_ANGLE);
    }

    //-------------------GOING FROM STATE TO STATE-------------------------------
    public void update(){

        flywheelVelocityRPM = ticksPerSecToRpm(flywheel_1.getVelocity());
        //updates current flywheel velocity

        switch (flywheelState) {
            case IDLE:
                if (shotsRemaining>0) {
                    gateServo.setPosition(GATE_CLOSED_ANGLE);
                    double targetTicks = rpmToTicksPerSec(TARGET_FLYWHEEL_RPM);
                    flywheel_1.setVelocity(targetTicks);
                    flywheel_2.setVelocity(targetTicks);
                    //.set velocity is done in ticks per sec, so target rpm needs to be converted to tps
                    stateTimer.reset();
                    flywheelState = FlywheelState.SPIN_UP;
                }
                break;
            case SPIN_UP:
                if(flywheelVelocityRPM >= TARGET_FLYWHEEL_RPM*0.95 || stateTimer.seconds()>MAX_SPIN_UP_TIME) {
                    gateServo.setPosition(GATE_OPEN_ANGLE);
                    stateTimer.reset();
                    flywheelState = FlywheelState.LAUNCH;
                }
                break;
            case LAUNCH:
                if(stateTimer.seconds()>GATE_OPEN_AND_CLOSE_TIME){
                    shotsRemaining--;
                    //shotsRemaining -1
                    gateServo.setPosition(GATE_CLOSED_ANGLE);
                    stateTimer.reset();
                    flywheelState = FlywheelState.RESET_GATE;
                }
                break;
            case RESET_GATE:
                if(stateTimer.seconds()>GATE_OPEN_AND_CLOSE_TIME){
                    if (shotsRemaining>0){
                        stateTimer.reset();
                        flywheelState = FlywheelState.SPIN_UP;
                    }
                    else{
                        flywheel_1.setVelocity(0);
                        flywheel_2.setVelocity(0);
                        flywheelState = FlywheelState.IDLE;
                    }
                }
                break;
        }

    }

    //---------------------------DEFINE METHOD fireShots---------------------------------
    public void fireShots(int numberOfShots){
        if(flywheelState == FlywheelState.IDLE){
            shotsRemaining = numberOfShots;
        }
    }

    //-----------------THE ROBOT IS BUSY IN ALL STATES EXCEPT FOR IDLE---------------------
    public boolean isBusy(){
        return flywheelState != FlywheelState.IDLE;
    }
}
