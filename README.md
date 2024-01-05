# rubikFROOK 3x3 solver

This is a hard-coded program solving 3x3 rubik.

### Prerequirement
- Java JRE

### How to install
- Windows
    download win-installer.exe from release

### How to run
to run use command "rubikFROOK"(for mac) or "rubikFROOK.bat"(for windows).
add path to environmental path to be able to use it from any locations.

### Command Usage and Options
rubikFROOK [-cdhv] [scrambles]
- scrambles
  available scrambles: R, F, U, D, L, B, R_, F_, U_, D_, L_, B_
  * X_ means inverted notation of X
  - example
    
    "rubikFROOK U D B U_"
- -c/--count-moves
  
  print number of moves used for solving the rubik
- -d/--detail-print
  
  print each notation in solving and print rubik in unfolded-box format
  *this will also show solution before notation cancellation
- -h/--help
  
  print help message
- -v/--version
  
  print version
