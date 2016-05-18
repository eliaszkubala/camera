#include <Wire.h>
#include "I2Cdev.h"
#include "MPU6050_6Axis_MotionApps20.h"
#include <stdio.h>
#include <stdlib.h>
#include <ServoTimer1.h>
#include <MsTimer2.h>
#include "DHT.h"

#define RS485Transmit    HIGH
#define RS485Receive     LOW
#define OUTPUT_READABLE_YAWPITCHROLL; //OUTPUT_READABLE_WORLDACCEL
#define DHTTYPE DHT22   // DHT 22  (AM2302)
#define DHTPIN 13     // what pin we're connected to
#define SSerialTxControl 7   //RS485 Direction control, DC

int pinRX = 0;
int pinTX = 1;
int pinMotorFirst = 8;  //sterowanie silnikiem
int pinMotorSecond = 4;
int pinMotorEnabled = 5;
int pinLight = 6;
int pinServoV = 9;
int pinServoH = 10;
int pinPressure = A0;

int motorSpeed = 0;
int speedNeed = 0;
int currentLight = 0;  //sterowanie swiatlem
int lastLight;
int lightPower = 0;

ServoTimer1 myservoH;
ServoTimer1 myservoV;
int offset = 1;
int servoMin = 0;
int servoMax = 180;
int servoH;
int servoV;
int droga;
int predkosc;

// GYROSKOP
MPU6050 mpu;
bool blinkState = false;
bool dmpReady = false;  // set true if DMP init was successful
uint8_t mpuIntStatus;   // holds actual interrupt status byte from MPU
uint8_t devStatus;      // return status after each device operation (0 = success, !0 = error)
uint16_t packetSize;    // expected DMP packet size (default is 42 bytes)
uint16_t fifoCount;     // count of all bytes currently in FIFO
uint8_t fifoBuffer[64]; // FIFO storage buffe
Quaternion q;           // [w, x, y, z]         quaternion container
VectorInt16 aa;         // [x, y, z]            accel sensor measurements
VectorInt16 aaReal;     // [x, y, z]            gravity-free accel sensor measurements
VectorInt16 aaWorld;    // [x, y, z]            world-frame accel sensor measurements
VectorFloat gravity;    // [x, y, z]            gravity vector
float euler[3];         // [psi, theta, phi]    Euler angle container
float ypr[3];           // [yaw, pitch, roll]   yaw/pitch/roll container and gravity vector
uint8_t teapotPacket[14] = {
  '$', 0x02, 0, 0, 0, 0, 0, 0, 0, 0, 0x00, 0x00, '\r', '\n'
};
volatile bool mpuInterrupt = false;     // indicates whether MPU interrupt pin has gone high

void dmpDataReady() {
  mpuInterrupt = true;
}

DHT dht(DHTPIN, DHTTYPE);
int16_t ax, ay, az;
int16_t gx, gy, gz;

void setup() {
  Wire.begin();
  Serial.begin(38400);

  analogWrite(pinLight, 0);
  pinMode(pinMotorFirst, OUTPUT);      // sets the digital pin as output
  pinMode(pinMotorSecond, OUTPUT);
  pinMode(SSerialTxControl, OUTPUT);
  digitalWrite(SSerialTxControl, RS485Receive);  // Init Transceiver
  dht.begin();

  gyroSetup();
  myservoV.attach(pinServoV);
  myservoH.attach(pinServoH);
  cameraReset();
  attachInterrupt(0, enkoder1h, RISING);         //deklaracja przerwania: funkcja blink() zostaje wywoĹ‚ana przy zmianie stanu (CHANGE) wejĹ›cia 2
  attachInterrupt(1, enkoder2h, RISING);
}

void loop() {
  readMethod();
  speedControl();
  pressureRead();
  heartBeat();
}

void readMethod() {
  // funkcja czyta dane wyslane od komputera i odpala poszczegolne funkcje
  rsReceive();

  char lastline[50];
  int k = 0;
  if (Serial.available() > 0)
  {
    delay(5);
    while (Serial.available() > 0) {
      lastline[k] = Serial.read();
      k++;
    }
    lastline[k] = '\0';

    if (strcmp(lastline, "forward()") == 0) {
      forward();
    }
    else if (strcmp(lastline, "backward()") == 0) {
      backward();
    }
    else if (strcmp(lastline, "stopEngine()") == 0) {
      stopEngine();
    }
    else if (strcmp(lastline, "cameraLeft()") == 0) {
      cameraLeft();
    }
    else if (strcmp(lastline, "cameraRight()") == 0) {
      cameraRight();
    }
    else if (strcmp(lastline, "clearDis()") == 0) {
      clearDistance();
    }
    else if (strcmp(lastline, "cameraUp()") == 0) {
      cameraUp();
    }
    else if (strcmp(lastline, "cameraDown()") == 0) {
      cameraDown();
    }
    else if (strcmp(lastline, "cameraReset()") == 0) {
      cameraReset();
    }
    else if (strcmp(lastline, "setLight(0)") == 0) {
      setLight(0);
    }
    else if (strcmp(lastline, "setLight(1)") == 0) {
      setLight(1);
    }
    else if (strcmp(lastline, "setLight(2)") == 0) {
      setLight(2);
    }
    else if (strcmp(lastline, "setLight(3)") == 0) {
      setLight(3);
    }
    else if (strcmp(lastline, "getGyro()") == 0) {
      rsTransmit();
      getGyro(true);
    }
    else if (strcmp(lastline, "getTemp()") == 0) {
      rsTransmit();
      getTemp();
    }
    else if (strcmp(lastline, "getHumi()") == 0) {
      rsTransmit();
      getHumi();
    }
    else if (strcmp(lastline, "getTest()") == 0) {
      rsTransmit();
      getTest();
    }
    else if (strcmp(lastline, "testServo1()") == 0) {
      testServo1();
    }
    else if (strcmp(lastline, "testServo2()") == 0) {
      testServo2();
    }
    else if (strcmp(lastline, "getSpeed()") == 0) {
      rsTransmit();
      getSpeed();
    }
    else if (strcmp(lastline, "getDistance()") == 0) {
      rsTransmit();
      getDistance();
    }
    else if (strcmp(lastline, "getDirect()") == 0) {
      rsTransmit();
      getDirect();
    }
    else if (strcmp(lastline, "speedUp()") == 0) {
      speedUp();
    }
    else if (strcmp(lastline, "speedDown()") == 0) {
      speedDown();
    }
    else if (strcmp(lastline, "speedMax()") == 0) {
      speedMax();
    }


    for (int k = 0; k <= 20; k++) {
      String string = "setSpeed(";
      string += k;
      string += ")";
      if (strcmp(lastline, string.c_str()) == 0) {
        setSpeedd(k);
      }
    }
  }
}


