void lightSmooth() {
  Serial.println(lastLight);
  if (lastLight - currentLight > 0) {
    lastLight--;
  }
  else if (lastLight - currentLight < 0) {
    lastLight++;
  }
  else {
    MsTimer2::stop();
  }
  analogWrite(pinLight, lastLight);
}

void setLight(int i) {
  Serial.println(F("procedura *setLight()* zostala rozpoczeta"));
  lastLight = currentLight;
  if (i == 0) {
    currentLight = 0;
  }
  else {
    currentLight = 255 / (4 - i);
  }
  MsTimer2::set(5, lightSmooth);
  MsTimer2::start();
}

