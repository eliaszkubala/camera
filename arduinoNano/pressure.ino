volatile unsigned long sensorValueM = millis();
int sensorValue[20];
void pressureRead(){
  if(millis() - sensorValueM < 50){
    return; 
  }
  sensorValueM = millis();
  for(int i=0; i<19; i++){
    sensorValue[i+1] = sensorValue[i];
  }
  sensorValue[0] = analogRead(pinPressure);
}

int getPressure(){
  int val = 0;
  int j = 0;
   for(int i=0; i<20-1; i++){
     if(sensorValue[i] != 0){
         val += sensorValue[i];
         j++;
     }
   }
   Serial.print(F("pre|"));
   Serial.println(val/j);
   delay(5);
}

