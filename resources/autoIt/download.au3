; set the select mode to select using substring
AutoItSetOption("WinTitleMatchMode","2")
ControlCommand ( "Opening", "Save", "&Save", "Check" )
; wait until dialog box appears
WinWait("Opening") ; match the window with substring
$title = WinGetTitle("Opening") ; retrives whole window title
WinActive($title);

; if user choose to save file
If (StringCompare($cmdline[1],"transcript",0) = 0) Then

WinActivate($title)
WinWaitActive($title)
Sleep(10)

; if user choose to open File

; If firefox is set to save the file on some specific location without asking to user.
; It will be save after this point.

Send("{Enter}")

;If Dialog appear prompting for Path to save
WinWait("Enter name")
$title = WinGetTitle("Enter name")
If (WinExists($title)) Then
;Set path and save file
WinActivate($title)
WinWaitActive($title)
ControlSetText($title, "", "Edit1", "&Save")
ControlClick($title, "", "Button2")
EndIf

WinWait("Downloads")
If( WinExists("Downloads") )Then
$title = WinGetTitle("Downloads")
WinActivate($title)
WinWaitActive($title)
Send("{ESCAPE}")
EndIf

ElseIf(StringCompare($cmdline[1],"media",0) = 0) Then

WinActivate($title)
WinWaitActive($title)
Sleep(10)

; if user choose to open File

; If firefox is set to save the file on some specific location without asking to user.
; It will be save after this point.

Send("{DOWN}")
Sleep(5)
Send("{Enter}")

;If Dialog appear prompting for Path to save
WinWait("Enter name")
$title = WinGetTitle("Enter name")
If WinExists($title) Then
;Set path and save file
WinActivate($title)
WinWaitActive($title)
ControlSetText($title, "", "Edit1", "&Save")
ControlClick($title, "", "Button2")
EndIf

WinWait("Downloads")
If WinExists("Downloads") Then
$title = WinGetTitle("Downloads")
WinActivate($title)
WinWaitActive($title)
Send("{ESCAPE}")
EndIf


Else
;Firefox is configured to save file at specific location
Exit
EndIf
; do not save the file
If (StringCompare("Cancel","Cancel",0) = 0) Then
WinWaitActive($title)
Send("{ESCAPE}")
EndIf