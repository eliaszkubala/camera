void getTemp() {
  float t = dht.readTemperature();
  float h = dht.readHumidity();
  float hic = dht.computeHeatIndex(t, h, false);
  Serial.print("temp|");
  Serial.println(hic);
  delay(5);
}

void getHumi() {
  float h = dht.readHumidity();
  Serial.print("hum|");
  Serial.println(h);
    delay(5);
}

void gyroSetup() {
  mpu.initialize();
  devStatus = mpu.dmpInitialize();
  mpu.setXGyroOffset(220);
  mpu.setYGyroOffset(76);
  mpu.setZGyroOffset(-85);
  mpu.setZAccelOffset(1688); // 1688 factory default for my test chip
  if (devStatus == 0) {
    mpu.setDMPEnabled(true);
    mpuIntStatus = mpu.getIntStatus();
    dmpReady = true;
    packetSize = mpu.dmpGetFIFOPacketSize();
  }
  else {
    Serial.print(F("DMP Initialization failed (code "));
    Serial.print(devStatus);
    Serial.println(F(")"));
  }
}


float gyroY[10];
void getGyro(boolean pobierz) {
  if (!dmpReady) return;
  mpuIntStatus = mpu.getIntStatus();
  fifoCount = mpu.getFIFOCount();
  if ((mpuIntStatus & 0x10) || fifoCount == 1024) {
    mpu.resetFIFO();
    getGyro(true);
  }
  else if (mpuIntStatus & 0x02) {
    while (fifoCount < packetSize) fifoCount = mpu.getFIFOCount();
    mpu.getFIFOBytes(fifoBuffer, packetSize);
    fifoCount -= packetSize;

#ifdef OUTPUT_READABLE_YAWPITCHROLL
    mpu.dmpGetQuaternion(&q, fifoBuffer);
    mpu.dmpGetGravity(&gravity, &q);
    mpu.dmpGetYawPitchRoll(ypr, &q, &gravity);
    
    
    dodajNoweGyro();
    
    if(pobierz){
      Serial.print("gyro|");
      Serial.print(ypr[0] * 180 / M_PI);
      Serial.print("|");
      Serial.print(pobierzUsrednioneGyro() * 180 / M_PI);
      Serial.print("|");
      Serial.println(ypr[2] * 180 / M_PI);
      delay(5);
    }
#endif
  }
}

void dodajNoweGyro(){
    if(ypr[1] != 0 && gyroY[0] != 0){
      if(abs(ypr[1] - gyroY[0]) > 0.07){
        return;
      }
    }
  
    for(int i=0; i<5; i++){
     gyroY[i+1] = gyroY[i]; 
    }
    gyroY[0] = ypr[1];
}

float pobierzUsrednioneGyro(){
  float val = 0;
  int j = 0;
  for(int i=0; i<5; i++){
    if(gyroY[i] != 0){
         val += gyroY[i];
         j++;
    }
   }
   return val/j;
}

