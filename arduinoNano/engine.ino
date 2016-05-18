void forward() {
  //Serial.println(F("procedura *forward()* zostala rozpoczeta"));
  analogWrite(pinMotorEnabled, motorSpeed);
  digitalWrite(pinMotorFirst, HIGH);
  digitalWrite(pinMotorSecond, LOW);
}

void backward() {
  //Serial.println(F("procedura *bakward()* zostala rozpoczeta"));
  analogWrite(pinMotorEnabled, motorSpeed);
  digitalWrite(pinMotorFirst, LOW);
  digitalWrite(pinMotorSecond, HIGH);
}

void stopEngine() {
  digitalWrite(pinMotorFirst, LOW);
  digitalWrite(pinMotorSecond, LOW);
}

void speedUp() {
  if (speedNeed < 20) {
    speedNeed = speedNeed++;
  }
}

void speedDown() {
  if (speedNeed > 1) {
    speedNeed = speedNeed--;
  }
}

void speedControl() {
  if (speedNeed > predkosc) {
    if (motorSpeed < 235) {
      motorSpeed = motorSpeed + 20;      
    }
  }
  if (speedNeed < predkosc) {
    if (motorSpeed > 20) {
      motorSpeed = motorSpeed - 20;
    }
  }
  analogWrite(pinMotorEnabled, motorSpeed);
}

void speedMax() {
  motorSpeed = 255;
}

