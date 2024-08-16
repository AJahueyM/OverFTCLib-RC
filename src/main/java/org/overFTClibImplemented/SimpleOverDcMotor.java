package org.overFTClibImplemented;

import org.overture.ftc.overftclib.OverDcMotor;

public class SimpleOverDcMotor implements OverDcMotor {
    private double power;
    private Direction direction;
    private RunMode mode;
    private int currentPosition;
    private int targetPosition;
    private double velocity;
    private double distance;

    public SimpleOverDcMotor() {
        this.power = 0.0;
        this.direction = Direction.FORWARD;
        this.mode = RunMode.RUN_WITHOUT_ENCODER;
        this.currentPosition = 0;
        this.targetPosition = 0;
        this.velocity = 0.0;
        this.distance = 0.0;
    }

    // OverEncoder methods
    @Override
    public void reset() {
        this.currentPosition = 0;
        this.distance = 0.0;
        this.velocity = 0.0;
    }

    @Override
    public int getCurrentPosition() {
        return this.currentPosition;
    }

    @Override
    public double getVelocity() {
        return this.velocity;
    }

    @Override
    public double getDistance() {
        return this.distance;
    }

    @Override
    public boolean isAtTargetPosition(int targetPosition) {
        return Math.abs(this.currentPosition - targetPosition) < 5; // tolerance for error
    }

    @Override
    public void setTargetPosition(int targetPosition) {
        this.targetPosition = targetPosition;
    }

    // OverDcMotor methods
    @Override
    public void setPower(double power) {
        this.power = power;
        if (this.direction == Direction.REVERSE) {
            this.power = -this.power; // Reverse the motor direction
        }
    }

    @Override
    public double getPower() {
        return this.power;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public void setMode(RunMode mode) {
        this.mode = mode;
        if (mode == RunMode.STOP_AND_RESET_ENCODER) {
            reset(); // Reset encoder values when resetting the motor
        }
    }

    public void updateMotorData(int newPosition, double newVelocity, double newDistance) {
        this.currentPosition = newPosition;
        this.velocity = newVelocity;
        this.distance = newDistance;
    }

    public void runToPosition() {
        if (this.mode == RunMode.RUN_TO_POSITION && this.targetPosition != this.currentPosition) {
            // Simulate movement toward the target position (this is where motor logic would go)
            if (this.currentPosition < this.targetPosition) {
                this.currentPosition += 1; // Simulating forward motion
            } else if (this.currentPosition > this.targetPosition) {
                this.currentPosition -= 1; // Simulating reverse motion
            }
        }
    }
}
