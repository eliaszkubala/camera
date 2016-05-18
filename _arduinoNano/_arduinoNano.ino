#include <Wire.h>
#include <SoftwareSerial.h>
#include "I2Cdev.h"
#include "MPU6050_6Axis_MotionApps20.h" // gyro
#include <stdio.h>    
#include <stdlib.h>
//#include <Servo.h>
#include <ServoTimer1.h>
#include <MsTimer2.h>
#include "DHT.h"


#define RS485Transmit    HIGH
#define RS485Receive     LOW
#define DHTTYPE DHT22   // DHT 22  (AM2302)
#define OUTPUT_READABLE_YAWPITCHROLL; //OUTPUT_READABLE_WORLDACCEL

int pinRX = 0;
#define SSerialTxControl 2   //RS485 Direction control, DC
int pinTX = 1;
int pinMotorFirst = 8;
int pinMotorSecond = 4;
int pinMotorEnabled = 5;

int pinLight = 6;
int currentLight = 0;  //sterowanie swiatlem
int lastLight;
int lightPower=0;

int pinServoV = 9;
int pinServoH = 10;
#define DHTPIN 13     // what pin we're connected to

//[zapasowe piny]


// Serva konfiguracja
ServoTimer1 myservoH;
ServoTimer1 myservoV;
int offset = 2;
int servoMin = 0;
int servoMax = 180;
int servoH;
int servoV; 

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
uint8_t teapotPacket[14] = { '$', 0x02, 0,0, 0,0, 0,0, 0,0, 0x00, 0x00, '\r', '\n' };
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

  analogWrite(pinLight,0);
  pinMode(pinMotorFirst, OUTPUT);      // sets the digital pin as output
  pinMode(pinMotorSecond, OUTPUT);
  pinMode(SSerialTxControl, OUTPUT);  
  digitalWrite(SSerialTxControl, RS485Receive);  // Init Transceiver  
  dht.begin();

  gyroSetup();
  myservoV.attach(pinServoV);  
  myservoH.attach(pinServoH);
  cameraReset();
}

void loop() {
  readMethod();
}

void testServo1(){
  int pos;
  for(pos = 180; pos>=0; pos-=1)     // goes from 180 degrees to 0 degrees 
  {                                
    myservoH.write(pos);              // tell servo to go to position in variable 'pos' 
    servoH = pos;
    delay(15);                       // waits 15ms for the servo to reach the position 
  }  
}

void testServo2(){
  int pos;
  for(pos = 180; pos>=0; pos-=1)     // goes from 180 degrees to 0 degrees 
  {                                
    myservoV.write(pos);              // tell servo to go to position in variable 'pos' 
    servoV = pos;
    delay(15);                       // waits 15ms for the servo to reach the position 
  }  
}

void rsReceive(){
  digitalWrite(SSerialTxControl, RS485Receive);  // Enable RS485 receive  
  delay(10);
}

void rsTransmit(){
  digitalWrite(SSerialTxControl, RS485Transmit);  // Enable RS485 Transmit
  delay(10);
}

void readMethod(){ 
  // funkcja czyta dane wyslane od komputera i odpala poszczegolne funkcje  
  rsReceive();
  
  char lastline[50];  int k=0;  
  if (Serial.available() > 0) 
  { 
   delay(3);
   while (Serial.available() > 0) {
      lastline[k]=Serial.read(); //read data  
      k++;      
   }
   lastline[k]='\0';
   
   rsTransmit();
   delay(3);
   Serial.println(lastline);
   
    if(strcmp(lastline, "forward()") == 0){
      forward();
    }
    else if(strcmp(lastline, "backward()") == 0){
      backward();
    }
    else if(strcmp(lastline, "stopEngine()") == 0){
      stopEngine();
    }
    else if(strcmp(lastline, "cameraLeft()") == 0){
      cameraLeft();
    }
    else if(strcmp(lastline, "cameraRight()") == 0){
      cameraRight();
    }
    else if(strcmp(lastline, "cameraUp()") == 0){
      cameraUp();
    }
    else if(strcmp(lastline, "cameraDown()") == 0){
      cameraDown();
    }
    else if(strcmp(lastline, "cameraReset()") == 0){
      cameraReset();
    }
    else if(strcmp(lastline, "getSensorsData()") == 0){
      getSensorsData();
    }
    else if(strcmp(lastline, "setLight(0)") == 0){
      setLight(0);
    }
    else if(strcmp(lastline, "setLight(1)") == 0){
      setLight(1);
    }
    else if(strcmp(lastline, "setLight(2)") == 0){
      setLight(2);
    }
    else if(strcmp(lastline, "setLight(3)") == 0){
      setLight(3);
    } 
    else if(strcmp(lastline, "getGyro()") == 0){
      getGyro(); 
    }
    else if(strcmp(lastline, "getTemp()") == 0){
      getTemp(); 
    }
    else if(strcmp(lastline, "getHumi()") == 0){
      getHumi(); 
    }
    else if(strcmp(lastline, "getTest()") == 0){
      getTest();
    }
    else if(strcmp(lastline, "testServo1()") == 0){
      testServo1();
    }    
    else if(strcmp(lastline, "testServo2()") == 0){
      testServo2();
    }    
//    delay(5);
  } 
}

void getTest(){
   Serial.println("test");
   Serial.println(F("polaczenie dziala - test wykonany")); 
}

void forward(){
   //Serial.println(F("procedura *forward()* zostala rozpoczeta")); 
   analogWrite(pinMotorEnabled, 255);
   digitalWrite(pinMotorFirst, HIGH);
   digitalWrite(pinMotorSecond, LOW);
}

void backward(){
   //Serial.println(F("procedura *bakward()* zostala rozpoczeta"));  
   analogWrite(pinMotorEnabled, 255);
   digitalWrite(pinMotorFirst, LOW);
   digitalWrite(pinMotorSecond, HIGH);
}

void stopEngine(){
    digitalWrite(pinMotorFirst, LOW);
    digitalWrite(pinMotorSecond, LOW);
}

void cameraRight(){
   servoH += offset;
   if(servoH > servoMax) servoH = servoMax;
   Serial.print(F("> "));  
   Serial.println(servoH);
   
   myservoH.write(servoH);
   delay(15);
}

void cameraLeft(){
   servoH -= offset;
   if(servoH < 0) servoH = servoMin;
   Serial.print(F("< "));  
   Serial.println(servoH);
   
   myservoH.write(servoH);
   delay(15);
}

void cameraReset(){
   servoH = servoMax /2 + 33;
   servoV = servoMax /2;   
   myservoH.write(servoH);
   myservoV.write(servoV);
  delay(15);   
}

void cameraDown(){
   servoV -= offset;
   if(servoV < 0) servoV = servoMin;
   Serial.print(F(".. "));  
   Serial.println(servoV);
   
   myservoV.write(servoV); 
   delay(15);
}

void cameraUp(){
   //Serial.println(F("procedura *cameraUp()* zostala rozpoczeta"));  
   servoV += offset;
   if(servoV > servoMax) servoV = servoMax;
   Serial.print(F("^^ "));  
   Serial.println(servoV);
   
   myservoV.write(servoV); 
   delay(15);
}

void getSensorsData(){
   Serial.println(F("procedura *getSensorsData()* zostala rozpoczeta"));  
}

void lightSmooth(){
  Serial.println(lastLight);
  if(lastLight - currentLight > 0){
    lastLight--;
  }
  else if(lastLight - currentLight < 0){
    lastLight++;
  }
  else{
    MsTimer2::stop();
  }
  analogWrite(pinLight, lastLight);
  }

void setLight(int i){
   Serial.println(F("procedura *setLight()* zostala rozpoczeta")); 
   lastLight = currentLight;
   if(i == 0){ 
     currentLight = 0;
   }else{
     currentLight = 255/(4-i);
   }
   MsTimer2::set(5, lightSmooth);
   MsTimer2::start();
}

void getTemp(){
  float t = dht.readTemperature();
   float h = dht.readHumidity();
  // Compute heat index in Celsius (isFahreheit = false)
  float hic = dht.computeHeatIndex(t, h, false);
  Serial.print("temp|");
  delay(10);
  Serial.println(hic);
}

void getHumi(){
   float h = dht.readHumidity();
  Serial.print("hum|");
  delay(10);
  Serial.println(h);
}


// funckje odpowiedzialne za dzialanie żyroskopu

void gyroSetup(){
    //Serial.println("Initialize MPU");
    mpu.initialize();
    //Serial.println(mpu.testConnection() ? "Connected" : "Connection failed");
    devStatus = mpu.dmpInitialize();
    mpu.setXGyroOffset(220);
    mpu.setYGyroOffset(76);
    mpu.setZGyroOffset(-85);
    mpu.setZAccelOffset(1688); // 1688 factory default for my test chip
    if (devStatus == 0) {
        // turn on the DMP, now that it's ready
        //Serial.println(F("Enabling DMP..."));
        mpu.setDMPEnabled(true);

        // enable Arduino interrupt detection
        //Serial.println(F("Enabling interrupt detection (Arduino external interrupt 0)..."));
        //attachInterrupt(0, dmpDataReady, RISING);
        mpuIntStatus = mpu.getIntStatus();

        // set our DMP Ready flag so the main loop() function knows it's okay to use it
        //Serial.println(F("DMP ready! Waiting for first interrupt..."));
        dmpReady = true;

        // get expected DMP packet size for later comparison
        packetSize = mpu.dmpGetFIFOPacketSize();
    } else {
        // ERROR!
        // 1 = initial memory load failed
        // 2 = DMP configuration updates failed
        // (if it's going to break, usually the code will be 1)
        Serial.print(F("DMP Initialization failed (code "));
        Serial.print(devStatus);
        Serial.println(F(")"));
    }
}

void getGyro(){
   if (!dmpReady) return;
   
   

    // wait for MPU interrupt or extra packet(s) available
    //while (!mpuInterrupt && fifoCount < packetSize) {}

    // reset interrupt flag and get INT_STATUS byte
    //mpuInterrupt = false;
    mpuIntStatus = mpu.getIntStatus();
    // get current FIFO count
    fifoCount = mpu.getFIFOCount();

    // check for overflow (this should never happen unless our code is too inefficient)
    if ((mpuIntStatus & 0x10) || fifoCount == 1024) {
        mpu.resetFIFO();
        delay(25);
        getGyro();
    } else if (mpuIntStatus & 0x02) {
        // wait for correct available data length, should be a VERY short wait
        while (fifoCount < packetSize) fifoCount = mpu.getFIFOCount();

        // read a packet from FIFO
        mpu.getFIFOBytes(fifoBuffer, packetSize);
        
        // track FIFO count here in case there is > 1 packet available
        // (this lets us immediately read more without waiting for an interrupt)
        fifoCount -= packetSize;

        #ifdef OUTPUT_READABLE_YAWPITCHROLL
            // display Euler angles in degrees
            mpu.dmpGetQuaternion(&q, fifoBuffer);
            mpu.dmpGetGravity(&gravity, &q);
            mpu.dmpGetYawPitchRoll(ypr, &q, &gravity);
            Serial.print("gyro|");
            delay(3);
            Serial.print(ypr[0] * 180/M_PI);
            delay(3);
            Serial.print("|");
            delay(3);
            Serial.print(ypr[1] * 180/M_PI);
            delay(3);
            Serial.print("|");
            delay(3);
            Serial.println(ypr[2] * 180/M_PI);
            delay(3);
        #endif
    } 
}
