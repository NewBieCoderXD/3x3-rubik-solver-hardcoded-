Name "rubikFROOK"
!verbose 4
Page directory
Page instfiles
RequestExecutionLevel admin 

Section "Installer Section"
    ; MessageBox MB_OK $INSTDIR
    ; NSISdl::download "https://github.com/NewBieCoderXD/3x3-rubik-solver-hardcoded/archive/refs/heads/main.zip" $INSTDIR\rubikFROOK
    inetc::get "https://github.com/NewBieCoderXD/rubikFROOK-3x3-solver/archive/refs/heads/main.zip" $INSTDIR\rubikFROOKmain.zip
    nsisunz::UnzipToLog  "$INSTDIR\rubikFROOKmain.zip" "$INSTDIR\"
    Pop $0
    StrCmp $0 "success" ok
        MessageBox MB_RETRYCANCEL "Please try again" IDRETRY -5 IDCANCEL 0
        Quit
    ok:
    Delete $INSTDIR\rubikFROOKmain.zip

    ; Rename "$INSTDIR\rubikFROOK-3x3-solver-main\*" "$INSTDIR\rubikFROOK\*"
    System::Call 'KERNEL32::MoveFile(t "I:\gg\rubikFROOK-3x3-solver-main", t "I:\gg\rubikFROOK") i .r0'
    EnVar::AddValue "Path" "$INSTDIR\rubikFROOK\scripts"

SectionEnd

; Section "un.Uninstaller Section"

; SectionEnd