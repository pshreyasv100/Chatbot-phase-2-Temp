@ECHO OFF
curl -X GET "http://localhost:8080/getdetails?beds=234&location=pune"
curl -X GET "http://localhost:8080/asknextquestion"
curl -X GET "http://localhost:8080/asknextquestion?answer=2"
curl -X GET "http://localhost:8080/asknextquestion?answer=2" 
curl -X GET "http://localhost:8080/suggest" | find "INTELLIVUE MX550"
if %errorlevel%==0 ECHO="test running successfully"
pause

