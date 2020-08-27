AutoItSetOption("WinTitleMatchMode","2")
ControlCommand ( "Print", "Save", "&Save", "Cancel" )
WinWait("Print") ; match the window with substring
$title = WinGetTitle("Print") ; retrives whole window title
WinWaitActive($title)
Send("{ESCAPE}")
