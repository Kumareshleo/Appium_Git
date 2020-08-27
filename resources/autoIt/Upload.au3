AutoItSetOption("WinTitleMatchMode","2")
ControlCommand ( "File Upload", "Save", "Open", "Cancel" )

If (StringCompare($cmdline[1],"firefox",0) = 0) Then
WinWait("File Upload") ; match the window with substring
$title = WinGetTitle("File Upload") ; retrives whole window title
WinWaitActive($title);
Send($cmdline[2])
Sleep(10000)
Send("{ENTER}")
ElseIf(StringCompare($cmdline[1],"GoogleChrome",0) = 0) Then
WinWait("Open") ; match the window with substring
$title = WinGetTitle("Open") ; retrives whole window title
WinWaitActive($title);
Send($cmdline[2])
Sleep(10000)
Send("{ENTER}")
EndIf

