//enkoder
volatile boolean enk1;
volatile boolean enk2;
volatile boolean kierunek;
volatile unsigned long time1 = 0;
volatile unsigned long time2 = 0;
volatile long int impulsy = 0;

void enkoder1h()                                            //funkcja obsĹ‚ugi przerwania. Nie posiada parametrĂłw i nie zwraca ĹĽadnej wartoĹ›ci
{
  enk1 = true;
  attachInterrupt(0, enkoder1l, FALLING);
  if (enk2 == false) {
    kierunek = true;
    impulsy++;
  }
  time1 = time2;
  time2 = millis();

}
void enkoder2h() {
  enk2 = true;
  attachInterrupt(1, enkoder2l, FALLING);
  if (enk1 == false) {
    kierunek = false;
    impulsy--;
  }
}
void enkoder1l() {
  enk1 = false;
  attachInterrupt(0, enkoder1h, RISING);
  if (enk2 == true) {
    kierunek = true;
  }
}
void enkoder2l() {
  enk2 = false;
  attachInterrupt(1, enkoder2h, RISING);
  if (enk2 == true) {
    kierunek = false;
  }
}

void getSpeed() {
  predkosc = 27.48 / (time2 - time1); // w m/min
  if ((millis() - time1) > 200) {
    predkosc = 0;
  }
  Serial.println(predkosc);
  delay(5);
}

void setSpeedd(int i) {
  speedNeed = i;
}

void clearDistance() {
  impulsy = 0;
}

void getDistance() {   // w mm
  droga = impulsy * 0.458;
  Serial.print("dis|");
  Serial.println(droga);
  delay(5);
}

void getDirect() {
  Serial.println(kierunek);
  delay(5);
}

