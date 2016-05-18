boolean isRsReceive = false;
boolean isRsTransmit = false;

void rsReceive() {
  if (isRsReceive) return;
  isRsReceive = true;
  digitalWrite(SSerialTxControl, RS485Receive);  // Enable RS485 receive
  isRsTransmit = false;
  delay(10);
}

void rsTransmit() {
  if (isRsTransmit) return;
  isRsTransmit = true;
  digitalWrite(SSerialTxControl, RS485Transmit);  // Enable RS485 Transmit
  isRsReceive = false;
  delay(10);
}

