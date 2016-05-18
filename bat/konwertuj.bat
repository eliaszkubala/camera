@echo on
echo Poprawa pliku z inspekcji
:: Fetch param1
set "param1=%~1"
goto :param1Check
:param1Prompt
set /p "param1=Podaj nazwe projektu: "
:param1Check
if "%param1%"=="" goto :param1Prompt

echo %param1%

:: Fetch param2    
set "param2=%~2"
goto :param2Check
:param2Prompt
set /p "param2=Podaj nazwe inspekcji: "
:param2Check
if "%param2%"=="" goto :param2Prompt

echo %param2%

:: Fetch param3    
set "param3=%~3"
goto :param3Check
:param3Prompt
set /p "param3=Podaj sciezke: "
:param3Check
if "%param3%"=="" goto :param3Prompt

echo %param3%

ffmpeg -y -i "%param3%\%param1%\%param2%.mp4" -preset ultrafast "%param3%\%param1%\%param2%2.mp4"

cd "%param3%\%param1%"

del "%param2%.mp4"
rename "%param2%2.mp4" "%param2%.mp4"

echo "ukonczono"
echo 0

#konwertuj.bat "nowy projekt" "test"