void cameraRight() {
  servoH += offset;
  if (servoH > servoMax) servoH = servoMax;
  /*   Serial.print(F("> "));
   Serial.println(servoH);*/

  myservoH.write(servoH);
  //   delay(15);
}

void cameraLeft() {
  servoH -= offset;
  if (servoH < 0) servoH = servoMin;
  /* Serial.print(F("< "));
   Serial.println(servoH);*/
  myservoH.write(servoH);
  //delay(15);
}

void cameraReset() {
  servoH = servoMax / 2;
  servoV = servoMax / 2;
  myservoH.write(servoH);
  myservoV.write(servoV);
  // delay(15);
}

void cameraDown() {
  servoV -= offset;
  if (servoV < 0) servoV = servoMin;
  //  Serial.print(F(".. "));
  // Serial.println(servoV);
  myservoV.write(servoV);
  //delay(15);
}

void cameraUp() {
  //Serial.println(F("procedura *cameraUp()* zostala rozpoczeta"));
  servoV += offset;
  if (servoV > servoMax) servoV = servoMax;
  // Serial.print(F("^^ "));
  // Serial.println(servoV);
  myservoV.write(servoV);
  // delay(15);
}

