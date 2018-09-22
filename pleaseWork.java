package org.firstinspires.ftc.teamcode;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.android.AndroidSoundPool;
import org.firstinspires.ftc.robotcore.external.android.AndroidTextToSpeech;

@TeleOp(name = "pleaseWorkJava", group = "")
public class pleaseWork extends LinearOpMode {

  private Servo mymanLeftServo;
  private DcMotor mymanLeftWheelMotor;
  private DcMotor mymanRightWheelMotor;
  private DcMotor mymanArmMotor;
  private Servo mymanRightServo;
  private Servo mymanMiniArm;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    double rServoPos;
    double lServoPos;
    float lStickY;
    float lStickX;
    double motorSpeed;
    double lMotorPower;
    double rMotorPower;
    double topServo;
    boolean topToggler;
    boolean sideToggle;
    boolean sound;

    mymanLeftServo = hardwareMap.servo.get("mymanLeftServo");
    mymanLeftWheelMotor = hardwareMap.dcMotor.get("mymanLeftWheelMotor");
    mymanRightWheelMotor = hardwareMap.dcMotor.get("mymanRightWheelMotor");
    mymanArmMotor = hardwareMap.dcMotor.get("mymanArmMotor");
    mymanRightServo = hardwareMap.servo.get("mymanRightServo");
    mymanMiniArm = hardwareMap.servo.get("mymanMiniArm");

    // Put initialization blocks here.
    lServoPos = 0.5;
    rServoPos = 0.42;
    motorSpeed = 1;
    topToggler = true;
    sideToggle = true;
    waitForStart();
    mymanLeftServo.scaleRange(0, 1);
    mymanLeftWheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    mymanRightWheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    mymanArmMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    mymanLeftWheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    mymanRightWheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    mymanLeftWheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    mymanRightWheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    while (opModeIsActive()) {
      lStickY = gamepad1.left_stick_y;
      lStickX = gamepad1.left_stick_x;
      lMotorPower = -(lStickY * motorSpeed) + lStickX * 0.5;
      rMotorPower = -(lStickY * motorSpeed) - lStickX * 0.5;
      if (gamepad1.a || gamepad2.b) {
        // Right Servo has to be around 0.9 or 0.8 smaller due to a hardware problem
        rServoPos = 0.42;
        lServoPos = 0.5;
      } else if (gamepad1.x || gamepad2.x) {
        rServoPos = 0.11;
        lServoPos = 0.8;
      }
      if (gamepad1.b) {
        rServoPos = 0.28;
        lServoPos = 0.65;
      }
      if (gamepad1.left_bumper) {
        mymanLeftWheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mymanRightWheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mymanLeftWheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mymanRightWheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      }
      telemetry.addData("Left Power", lMotorPower);
      telemetry.addData("Left Power", lMotorPower);
      telemetry.addData("Left Wheel Motor Position", mymanLeftWheelMotor.getCurrentPosition());
      telemetry.addData("Right Wheel Motor Position", mymanRightWheelMotor.getCurrentPosition());
      telemetry.addData("Top Servo", topServo);
      mymanLeftWheelMotor.setPower(lMotorPower);
      mymanRightWheelMotor.setPower(rMotorPower);
      mymanArmMotor.setPower(-(gamepad1.right_stick_y * 0.3 - 0.1));
      mymanRightServo.setPosition(rServoPos);
      mymanLeftServo.setPosition(lServoPos);
      mymanMiniArm.setPosition(1);
      telemetry.update();
    }

  }
}