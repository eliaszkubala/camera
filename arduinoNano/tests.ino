void testServo1() {
  int pos;
  for (pos = 180; pos >= 0; pos -= 1) // goes from 180 degrees to 0 degrees
  {
    myservoH.write(pos);              // tell servo to go to position in variable 'pos'
    servoH = pos;
    delay(15);                       // waits 15ms for the servo to reach the position
  }
}

void testServo2() {
  int pos;
  for (pos = 180; pos >= 0; pos -= 1) // goes from 180 degrees to 0 degrees
  {
    myservoV.write(pos);              // tell servo to go to position in variable 'pos'
    servoV = pos;
    delay(15);                       // waits 15ms for the servo to reach the position
  }
}

void getTest() {
  Serial.println("test");
  Serial.println(F("polaczenie dziala - test wykonany"));
  delay(5);
}

