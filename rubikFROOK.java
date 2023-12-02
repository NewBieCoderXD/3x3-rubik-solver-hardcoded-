import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

class rubikFROOK {
  private final int[][] directions = new int[][]{
    {1, 3, 4, 2},
    {3, 0, 2, 5},
    {1, 0, 4, 5},
    {4, 0, 1, 5},
    {2, 0, 3, 5},
    {1, 2, 4, 3}
  };
  private final String[] faceToNotation = new String[]{
    "U",
    "F",
    "R",
    "L",
    "B"
  };
  public boolean IsSolvingStarted = false;
  public int[][] rubik;
  public static void main(String[] args) {
    rubikFROOK main = new rubikFROOK();
    main.mainnaja();
  }
  private int findIndex(int[] list, int value) {
    for (int i = 0; i < list.length; i++) {
      if (list[i] == value) {
        return i;
      }
    }
    return 5;
  }

  private int[][] createClonedRubik(){
    int[][] newRubik = new int[6][8];
    for(int i=0;i<6;i++){
      System.arraycopy(rubik[i], 0, newRubik[i], 0, 8);
    }
    return newRubik;
  }
  
  private void rotateClockwise(int face,int direction){
    int[][] newRubik = createClonedRubik();
    int lateralSide = directions[face][(direction+1+4)%4];
    for(int i=0;i<4;i++){
      int side = directions[lateralSide][i];
      int nextSide = directions[lateralSide][(i+1)%4];
      for(int j=0;j<3;j++){
        newRubik[nextSide][((findIndex(directions[nextSide],side)-1)*2+j+8)%8]=rubik[side][((findIndex(directions[side],nextSide)+1)*2+j+8)%8];
      }
    }
    for(int i=0;i<8;i++){
      newRubik[lateralSide][i]=rubik[lateralSide][(i+6)%8];
    }
    rubik=newRubik;
  }
  
  private void rotateAntiClockwise(int face,int direction){
    int[][] newRubik = createClonedRubik();
    int lateralSide = directions[face][(direction-1+4)%4];
    for(int i=0;i<4;i++){
      int side = directions[lateralSide][i];
      int nextSide = directions[lateralSide][(i+1)%4];
      for(int j=0;j<3;j++){
        newRubik[nextSide][((findIndex(directions[nextSide],side)+1)*2+j+8)%8]=rubik[side][((findIndex(directions[side],nextSide)-1)*2+j+8)%8];
      }
    }
    for(int i=0;i<8;i++){
      newRubik[5-lateralSide][i]=rubik[5-lateralSide][(i+2)%8];
    }
    rubik=newRubik;
  }
  
  //direction is top side
  private void addToSolution(String s){
    if(IsSolvingStarted){
      solution.add(s);
    }
  }
  public void R(){
    rotateClockwise(1,1);
    System.out.println("R");
    addToSolution("R");
    printRubik(rubik);
  }
  public void L(){
    rotateClockwise(1,-1);
    System.out.println("L");
    addToSolution("L");
    printRubik(rubik);
  }
  public void U(){
    rotateClockwise(1,0);
    System.out.println("U");
    addToSolution("U");
    printRubik(rubik);
  }
  public void D(){
    rotateClockwise(1,2);
    System.out.println("D");
    addToSolution("D");
    printRubik(rubik);
  }
  public void F(){
    rotateClockwise(3,1);
    System.out.println("F");
    addToSolution("F");
    printRubik(rubik);
  }
  public void B(){
    rotateClockwise(3,3);
    System.out.println("B");
    addToSolution("B");
    printRubik(rubik);
  }
  public void R_(){
    rotateAntiClockwise(1,1);
    System.out.println("R'");
    addToSolution("R'");
    printRubik(rubik);
  }
  public void L_(){
    rotateAntiClockwise(1,-1);
    System.out.println("L'");
    addToSolution("L'");
    printRubik(rubik);
  }
  public void U_(){
    rotateAntiClockwise(1,0);
    System.out.println("U'");
    addToSolution("U'");
    printRubik(rubik);
  }
  public void D_(){
    rotateAntiClockwise(1,2);
    System.out.println("D'");
    addToSolution("D'");
    printRubik(rubik);
  }
  public void F_(){
    rotateAntiClockwise(3,1);
    System.out.println("F'");
    addToSolution("F'");
    printRubik(rubik);
  }
  public void B_(){
    rotateAntiClockwise(3,3);
    System.out.println("B'");
    addToSolution("B'");
    printRubik(rubik);
  }

  private void call(String methodName){
    switch(methodName){
      case "R":
        R();
        break;
      case "U":
        U();
        break;
      case "L":
        L();
        break;
      case "D":
        D();
        break;
      case "F":
        F();
        break;
      case "B":
        B();
        break;
      case "R_":
        R_();
        break;
      case "U_":
        U_();
        break;
      case "L_":
        L_();
        break;
      case "D_":
        D_();
        break;
      case "F_":
        F_();
        break;
      case "B_":
        B_();
        break;
      default:
        System.out.println("error, no command: "+methodName);
        System.exit(1);
    }
  }

  private void rotateTo(int side,int targetSide,String action){
    int turns=side-targetSide;
    if(turns==0){
      return;
    }
    if(Math.abs(turns)>2){
      call(action+"_");
      return;
    }
    for(int t=0;t<Math.abs(turns);t++){
      if(turns>0){
        call(action);
      }
      else{
        call(action+"_");
      }
    }
  }

  private void cross(){
    for(int i=0;i<6;i++){
      for(int j=1;j<8;j+=2){
        if(rubik[i][j]==5){
          int direction = Math.round((j-1)/2);
          int latSide = directions[i][direction];
          int latDirection = findIndex(directions[latSide],i);
          int color = rubik[latSide][(latDirection*2+1)%8];
          if(i!=5||latSide!=color){
            System.out.println("i "+i+" latSide "+latSide+" Color "+color+" direction "+direction);
            if(i==5){
              call(faceToNotation[latSide]);
              call(faceToNotation[latSide]);
              rotateTo(findIndex(directions[0],color),findIndex(directions[0],latSide),"U");
              continue;
            }
            if(i==0){
              rotateTo(findIndex(directions[0],color),findIndex(directions[0],latSide),"U");
              call(faceToNotation[color]);call(faceToNotation[color]);
              continue;
            }
            if(color==latSide){
              call(faceToNotation[latSide]+((direction>1)?"_":""));
              continue;
            }
            if(color==5-latSide){
              call(faceToNotation[i]);
              call(faceToNotation[i]);
              call(faceToNotation[color]+((direction>1)?"":"_"));
              continue;
            }
            if(direction==1||direction==3){
              System.out.println("this");
              if(findIndex(directions[i],color)<5){
                rotateTo(findIndex(directions[i],color),direction,faceToNotation[i]);
                call(faceToNotation[color]+((direction>2)?"_":""));
                continue;
              }
              if(color==i){
                U_();
                call(faceToNotation[directions[5][(findIndex(directions[5],color)+1)%4]]+"_");
                call(faceToNotation[color]);
                call(faceToNotation[directions[5][(findIndex(directions[5],color)+1)%4]]);
                continue;
              }
              call(faceToNotation[color]);
              call(faceToNotation[color]);
              U_();
              call(faceToNotation[directions[5][(findIndex(directions[5],color)+5)%4]]+"_");
              call(faceToNotation[color]);
              call(faceToNotation[directions[5][(findIndex(directions[5],color)+5)%4]]);
              continue;
            }
            //direction = 0,2
            call(faceToNotation[latSide]+((direction>1)?"":"_"));
            System.out.println(findIndex(directions[0],color)+" "+findIndex(directions[0],latSide));
            rotateTo(findIndex(directions[0],color)+((color==1?4:0)),findIndex(directions[0],latSide),"U");
            call(faceToNotation[latSide]+((direction<1)?"":"_"));
            call(faceToNotation[color]);call(faceToNotation[color]);
          }
        }
      }
    }
  }

  private void corner(){
    for(int i=1;i<5;i++){
      for(int j=0;j<7;j+=2){
        if(rubik[i][j]==5){
          int latSide = directions[5][(findIndex(directions[5],i)+((j>3)?1:-1)+4)%4];
          int color = rubik[latSide][6-j];
          System.out.println("\nCorner found. lat side: "+latSide+" color: "+color+" j: "+j+"\n");
          if(j==0||j==6){
            System.out.println("low level");
            call(faceToNotation[i]+((j<3)?"":"_"));
            call("U"+((j<3)?"":"_"));
            call(faceToNotation[i]+((j<3)?"_":""));
            call("U"+((j<3)?"_":""));
            return;
          }
          else{
            System.out.println("high level");
            int newSide = directions[5][(findIndex(directions[5],color)+((j>3)?-1:1)+4)%4];
            System.out.println("newSide "+newSide);
            rotateTo(findIndex(directions[5],latSide),findIndex(directions[5],color),"U");
            call(faceToNotation[newSide]+((j>3)?"_":""));
            call("U"+((j>3)?"_":""));
            call(faceToNotation[newSide]+((j>3)?"":"_"));
            return;
          }
        }
      }
    }
    if(checkCorner()){
      return;
    }
    //yellow side
    for(int j=0;j<8;j+=2){
      if(rubik[0][j]==5){
        System.out.println("yellow "+j);
        int side = (new int[]{3,1,2,4})[Math.round(j/2)];
        for(int k=0;k<8;k++){
          if(rubik[5][k]!=5){
            rotateTo(Math.round((10-k)%8/2),Math.round(j/2),"U");
            side = (new int[]{3,1,2,4})[Math.round((10-k)%8/2)];
            break;
          }
        }
        call(faceToNotation[side]);
        U();U();
        call(faceToNotation[side]+"_");
      }
    }
    for(int j=0;j<8;j++){
      if(rubik[5][j]!=5){
        return;
      }
    }
    for(int side=1;side<5;side++){
      if(rubik[side][2]!=side){
        String turn = faceToNotation[rubik[5-side][2]];
        call(turn);
        U();
        call(turn+"_");
        return;
      }
    }
  }
  
  private void edge(){
    boolean hasEdge = false;
    for(int j=1;j<8;j+=2){
      if(rubik[0][j]!=0){
        int latSide = directions[0][Math.round((j-1)/2)];
        if(rubik[latSide][3]!=0){
          hasEdge = true;
          int top=rubik[0][j];
          int bottom=rubik[latSide][3];
          System.out.println("Edge found. latSide: "+latSide+" top: "+top+" bottom: "+bottom+" "+findIndex(directions[5],top)+" "+findIndex(directions[5],bottom));
          if(directions[5][(findIndex(directions[5],top)+2)%4]!=latSide){
            rotateTo(findIndex(directions[0],5-top),findIndex(directions[0],latSide),"U");
          }
          if(findIndex(directions[5],top)<findIndex(directions[5],bottom)&&(top!=1||bottom!=3)||(top==3&&bottom==1)){
            System.out.println("gg");
            call(faceToNotation[top]+"_");
            U_();
            call(faceToNotation[top]);
            U();
            call(faceToNotation[bottom]);
            U();
            call(faceToNotation[bottom]+"_");
          }
          else{
            System.out.println("g");
            call(faceToNotation[top]);
            U();
            call(faceToNotation[top]+"_");
            U_();
            call(faceToNotation[bottom]+"_");
            U_();
            call(faceToNotation[bottom]);
          }
          
        }
      }
    }
    if(!hasEdge){
      System.out.println("no edges");
      for(int side=1;side<5;side++){
        int color = rubik[side][1];
        if(color!=0&&color!=side){
          int latSide = directions[5][(findIndex(directions[5],side)+3)%4];
          int latColor = rubik[latSide][5];
          if(latColor!=0&&(color!=side||latSide!=latColor)){
            System.out.println("wrong edge. side: "+side+" latSide: "+latSide+" color: "+color+" latColor: "+latColor);
            call(faceToNotation[side]);
            U();
            call(faceToNotation[side]+"_");
            U_();
            call(faceToNotation[latSide]+"_");
            U_();
            call(faceToNotation[latSide]);
          }
        }
      }
    }
  }

  private void OLL(){
    System.out.println("OLL");
    ArrayList<Integer> yellowEdgeList = new ArrayList<Integer>();
    ArrayList<Integer> yellowCornerList = new ArrayList<Integer>();
    for(int j=1;j<8;j+=2){
      if(rubik[0][j]==0){
        yellowEdgeList.add(Math.round(j/2));
      }
    }
    if(yellowEdgeList.size()==0){
      F();
      R();
      U();
      R_();
      U_();
      F_();
      return;
    }
    
    if(yellowEdgeList.size()==2){
      if(yellowEdgeList.get(0)+yellowEdgeList.get(1)==5){ //Line
        int side = directions[0][(yellowEdgeList.get(0)-1+4)%4];
        int latSide = 5-directions[0][yellowEdgeList.get(0)];
        call(faceToNotation[side]);
        U();
        call(faceToNotation[latSide]);
        U_();
        call(faceToNotation[latSide]+"_");
        call(faceToNotation[side]+"_");
      }
      else{
        int maxDirection = findIndex(directions[0],Math.max(yellowEdgeList.get(0),yellowEdgeList.get(1)));
        int leftSide = directions[0][(maxDirection+1)%4];
        int side = directions[0][maxDirection];
        call(faceToNotation[leftSide]);
        U();
        call(faceToNotation[side]);
        U_();
        call(faceToNotation[side]+"_");
        call(faceToNotation[leftSide]+"_");
      }
      return;
    }
    
    // 4 yellows
    for(int j=0;j<7;j+=2){
      if(rubik[0][j]==0){
        yellowCornerList.add(j);
      }
    }
    if(yellowCornerList.size()==0){
      for(int side=1;side<5;side++){
        if(rubik[side][2]==0&&rubik[side][4]==0){
          System.out.println(5-side);
          // int direction = findIndex(directions[0],side);
          if(rubik[5-side][2]==0&&rubik[5-side][4]==0){
            call(faceToNotation[side]);
            U();
            call(faceToNotation[side]+"_");
            U();
            call(faceToNotation[side]);
            U_();
            call(faceToNotation[side]+"_");
            U();
            call(faceToNotation[side]);
            U();U();
            call(faceToNotation[side]+"_");
            return;
          }
          else{
            int oppositeSide = 5-side;
            String oppositeFace=faceToNotation[oppositeSide];
            call(oppositeFace);
            U();U();
            call(oppositeFace);
            call(oppositeFace);
            U_();
            call(oppositeFace);
            call(oppositeFace);
            U_();
            call(oppositeFace);
            call(oppositeFace);
            U();U();
            call(oppositeFace);
          }
        }
      }
    }
    if(yellowCornerList.size()==1){ //ปลาตะเพียน
      int side = directions[0][(Math.round(yellowCornerList.get(0)/2)-1+4)%4]; //right side
      //System.out.println(side);
      if(rubik[side][4]==0){
        int latSide = directions[0][(Math.round(yellowCornerList.get(0)/2)+2)%4];
        System.out.println(latSide);
        call(faceToNotation[latSide]);
        U();
        call(faceToNotation[latSide]+"_");
        U();
        call(faceToNotation[latSide]);
        U();U();
        call(faceToNotation[latSide]+"_");
        return;
      }
      int latSide = directions[0][(Math.round(yellowCornerList.get(0)/2)+1)%4];
      call(faceToNotation[latSide]+"_");
      U_();
      call(faceToNotation[latSide]);
      U_();
      call(faceToNotation[latSide]+"_");
      U();U();
      call(faceToNotation[latSide]);
      return;
    }
    if(yellowCornerList.size()==2){
      int direction1 = Math.round(yellowCornerList.get(0)/2);
      int direction2 = Math.round(yellowCornerList.get(1)/2);
      if(Math.abs(direction1-direction2)==2){
        int side = directions[0][(direction1+2)%4];
        int rightSide = directions[0][(direction1+1)%4];
        int leftSide = directions[0][(direction1+3)%4];
        if(rubik[side][2]!=0){
          side = directions[0][(direction2+2)%4];
          rightSide = directions[0][(direction2+1)%4];
          leftSide = directions[0][(direction2+3)%4];
        }
        System.out.println(side+" "+rightSide);
        String rightFace=faceToNotation[rightSide];
        call(faceToNotation[side]);
        call(rightFace+"_");
        call(faceToNotation[side]+"_");
        call(faceToNotation[leftSide]);
        call(faceToNotation[side]);
        call(rightFace);
        call(faceToNotation[side]+"_");
        call(faceToNotation[leftSide]+"_");
        return;
      }
      //รถถัง
      int side = directions[0][(4-Math.min(yellowCornerList.get(0),yellowCornerList.get(1)))%4];
      int oppositeSide = 5-side;
      int rightSide = directions[0][(4-Math.min(yellowCornerList.get(0),yellowCornerList.get(1))+3)%4];
      System.out.println("side "+side);
      String oppositeFace=faceToNotation[oppositeSide];
      String rightFace=faceToNotation[rightSide];
      call(faceToNotation[side]);
      call(rightFace);
      call(oppositeFace+"_");
      call(rightFace+"_");
      call(faceToNotation[side]+"_");
      call(rightFace);
      call(oppositeFace);
      call(rightFace+"_");
    }
  }

  private void PLL(){
    System.out.println("PLL");
    for(int side=1;side<5;side++){
      if(rubik[side][2]==rubik[side][4]){
        int leftSide = directions[5][(findIndex(directions[5],side)-1+4)%4];
        int rightSide = 5-leftSide;
        if(rubik[leftSide][2]==rubik[leftSide][4]){
          if(rubik[side][2]==rubik[side][3]&&rubik[leftSide][2]==rubik[leftSide][3]){
            int color = rubik[side][2];
            rotateTo(findIndex(directions[5],side),findIndex(directions[5],color),"U");
            return;
          }
          if(rubik[side][2]==rubik[side][3]||rubik[leftSide][2]==rubik[leftSide][3]||rubik[rightSide][2]==rubik[rightSide][3]){
            continue;
          }
          String turn = faceToNotation[rightSide];
          if(rubik[rightSide][2]==rubik[side][3]){
            call(turn);U_();
            call(turn);U();
            call(turn);U();
            call(turn);U_();
            call(turn+"_");U_();
            call(turn);call(turn);
            return;
          }
          call(turn);call(turn);
          U();call(turn);
          U();call(turn+"_");
          U_();call(turn+"_");
          U_();call(turn+"_");
          U();call(turn+"_");
          return;
        }
        int oppositeSide = 5-side;
        String oppositeFace=faceToNotation[oppositeSide];
        String rightFace=faceToNotation[rightSide];
        call(oppositeFace);
        U();
        call(oppositeFace+"_");
        U_();
        call(oppositeFace+"_");
        call(rightFace);
        call(oppositeFace);call(oppositeFace);
        U_();
        call(oppositeFace+"_");
        U_();
        call(oppositeFace);
        U();
        call(oppositeFace+"_");
        call(rightFace+"_");
        return;
      }
    }
    // no perfect corner
    F();
    R();
    U_();
    R_();
    U_();
    R();
    U();
    R_();
    F_();
    R();
    U();
    R_();
    U_();
    R_();
    F();
    R();
    F_();
    return;
  }

  private boolean checkPLL(){
    for(int side=1;side<5;side++){
      for(int j=2;j<5;j++){
        if(rubik[side][j]!=side){
          return false;
        }
      }
    }
    return true;
  }
  
  private boolean checkOLL(){
    for(int j=0;j<8;j++){
      if(rubik[0][j]!=0){
        return false;
      }
    }
    return true;
  }

  private boolean checkEdge(){
    for(int side=1;side<5;side++){
      for(int j=1;j<6;j+=4){
        if(rubik[side][j]!=side){
          return false;
        }
      }
    }
    return true;
  }
  
  private boolean checkCross(){
    for(int i=1;i<8;i+=2){ //cross
      if(rubik[5][i]!=5){
        return false;
      }
    }
    for(int i=1;i<5;i++){ //right cross
      if(rubik[i][7]!=i){
        return false;
      }
    }
    return true;
  }

  private boolean checkCorner(){
    for(int i=1;i<5;i++){
      if(rubik[i][0]!=i||rubik[i][6]!=i){
        return false;
      }
    }
    return true;
  }

  private ArrayList<String> solution = new ArrayList<String>();

  private void solve(){
    while(!checkCross()){
      cross();
    }
    System.out.println("\n"+"finish cross "+solution.size()+"\n");
    while(!checkCorner()){
      corner();
    }
    System.out.println("\n"+"finish corner "+solution.size()+"\n");
    while(!checkEdge()){
      edge();
    }
    System.out.println("\n"+"finish edge "+solution.size()+"\n");
    while(!checkOLL()){
      OLL();
    }
    System.out.println("\n"+"finish OLL "+solution.size()+"\n");
    while(!checkPLL()){
      PLL();
    }
    System.out.println("\n"+"finish PLL "+solution.size()+"\n");
  }
  
  public void mainnaja(){
    rubik = new int[6][8];
    //generate rubik
    for(int i=0;i<6;i++){
      rubik[i]=new int[]{
        i,i,i,i,i,i,i,i
      };
    }
    printRubik(rubik);
    
    String scramble = "";

    /*for(String scramble: "B U U B F R U U R R U U L B U U L L F U R B R B U L B F B B R U B U B U L B R L U R U F F R F L U U U B R U R B R F U U F F U U F B B U U B F R B F R F U B U U B L F B B B U F L F L R R U B B U F B F F L L B F B R U B U R L F B L F R U R U B F U F R U F R B F B L U L R L L B B F B U U U B R R R U U U F L B B U U L B B B L F L L L U U U B U U L R L U U U L B F L R U U R B B R F B F F B B R L L L R R B B F L L L F L R U B L L B U L F B U L U U U F U B L U U B B L F R F L U B U B R R B F F L L L R F L R B B L F F U B B B U R R B U L R L F R F R R L L B L B L U U R B L L L U R L R R L F L F U L B B U L U B R U R L F F F B L L B R R U B R F U F L F U F F F L B U F L F U U U L B F L L L L U B L U U L B B U U B U R U F R L F B B B B F B R R U B F L B B L U L L B F B L L R R F L L L L F L U B R F R L U L F L U F L U L U L B F F F F F L L L L R B F B U R R F U F L R B R B R B U L L L F U U L F F F U B B U L R B F F U L F B B F F F F U U F L U B L B F B U U R B F R U F U U B R B B L R F U F L R".split(" ")){
      call(scramble);
    }*/
    
    Scanner systemIn = new Scanner(System.in);
    int numOfScrambles=Integer.valueOf(systemIn.next());
    for(int i=0;i<numOfScrambles;i++){
      String inputScramble=systemIn.next();
      scramble+=inputScramble+" ";
      call(inputScramble);
    }
    systemIn.close();

    // for(int time=0;time<10;time++){
    //   String random = (new String[]{"U","R","L","F","D","B","U_","R_","L_","F_","D_","B_"})[(int) (Math.random()*5)];
    //   call(random);
    //   string+=random+" ";
    // }
    
    System.out.println("\nStarting Solving\n");

    IsSolvingStarted = true;
    
    solve();
    System.out.println("scrambles: "+scramble);
    System.out.println("solution: "+String.join(", ",solution));
    System.out.println("solution moves: "+solution.size());
    
    int i=0;
    while(i<solution.size()-1){
      String a = solution.get(i);
      String b = solution.get(i+1);
      if(a.charAt(0)==b.charAt(0)){
        if(a.length()!=b.length()){
          System.out.println("cancel "+a);
          solution.remove(i);
          solution.remove(i);
        }
      }
      if(solution.size()-i<3){
        i++;
        continue;
      }
      String c = solution.get(i+2);
      if(a==b&&b==c){
        System.out.println("replace "+a);
        solution.remove(i);
        solution.remove(i);
        solution.remove(i);
        if(i<solution.size()-3){
          if(solution.get(i)==a){
            System.out.println("remove "+a);
            solution.remove(i);
            i++;
            continue;
          }
        }
        if(a.length()>1){
          solution.add(i,Character.toString(a.charAt(0)));
        }
        else{
          solution.add(i,a+"'");
        }
      }
      i++;
    }
    System.out.println("solution: "+String.join(", ",solution));
    System.out.println("solution moves: "+solution.size());
  }
  protected void printRubik(int[][] rubik){
    System.out.println("      "+rubik[1][2]+" "+rubik[1][3]+" "+rubik[1][4]);
    System.out.println("      "+rubik[1][1]+" 1 "+rubik[1][5]);
    System.out.println("      "+rubik[1][0]+" "+rubik[1][7]+" "+rubik[1][6]);
    System.out.println(
      rubik[3][4]+" "+rubik[3][5]+" "+rubik[3][6]+" "+
      rubik[5][0]+" "+rubik[5][1]+" "+rubik[5][2]+" "+
      rubik[2][0]+" "+rubik[2][1]+" "+rubik[2][2]+" "+
      rubik[0][0]+" "+rubik[0][1]+" "+rubik[0][2]+" "
    );
    System.out.println(
      rubik[3][3]+" 3 "+rubik[3][7]+" "+
      rubik[5][7]+" 5 "+rubik[5][3]+" "+
      rubik[2][7]+" 2 "+rubik[2][3]+" "+
      rubik[0][7]+" 0 "+rubik[0][3]+" "
    );
    System.out.println(
      rubik[3][2]+" "+rubik[3][1]+" "+rubik[3][0]+" "+
      rubik[5][6]+" "+rubik[5][5]+" "+rubik[5][4]+" "+
      rubik[2][6]+" "+rubik[2][5]+" "+rubik[2][4]+" "+
      rubik[0][6]+" "+rubik[0][5]+" "+rubik[0][4]+" "
    );
    System.out.println("      "+rubik[4][6]+" "+rubik[4][7]+" "+rubik[4][0]);
    System.out.println("      "+rubik[4][5]+" 4 "+rubik[4][1]);
    System.out.println("      "+rubik[4][4]+" "+rubik[4][3]+" "+rubik[4][2]);
  }
}