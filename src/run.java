package src;

//run command: jar cfmv build/rubikFROOK.jar META-INF/MANIFEST.MF -C bin .

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class run {
    static RubikFROOK rubikFROOK = new RubikFROOK();
    static private boolean IsOption(String arg){
        return arg.charAt(0)=='-';
    }

    static private void printHelpMessage(){
        Properties properties = new Properties();
        System.out.println("gg");
        // try (InputStream input = run.class.getClassLoader().getResourceAsStream("/config.json")) {
        //     ObjectMapper mapper = new ObjectMapper();
        //     properties.load(input);
            
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
    }

    private final static HashMap<Character,Runnable> argFunctions = new HashMap<>(Map.of(
        'd',()->{
            rubikFROOK.detailPrint=true;
        },
        'v',()->{
            System.out.println("1.0.0");
            System.exit(0);
        },
        'h',()->{
            printHelpMessage();
            System.exit(0);
        }
    ));

    public static void main(String[] args) {
        int lastOption=-1;
        for(int i=0;i<args.length;i++){
            if(IsOption(args[i])){
                lastOption=i;
                Runnable optionMethod = argFunctions.get(args[i].charAt(1));
                if(optionMethod==null){
                    rubikFROOK.throwError("Invalid option "+args[i]);
                    return;
                }
                optionMethod.run();
            }
        }
        if(lastOption>=args.length-1){
            rubikFROOK.throwError("No scrambles taken");
            return;
        }
        String[] scrambles = Arrays.copyOfRange(args, lastOption+1, args.length);
        
        rubikFROOK.mainSolving(scrambles);
    }
}
