package com.ggFROOK;

//run command: jar cfmv build/rubikFROOK.jar META-INF/MANIFEST.MF -C bin .

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;

@Command(name = "rubikFROOK", version="v1.0.0")
public class run implements Callable<Integer> {
    @Option(names = {"-h", "--help"},
            usageHelp = true,
            description = "Display this help message")
    static public boolean usageHelpRequested;

    @Option(names={"-v","--version"},versionHelp=true,description="Display version")
    static public boolean versionOption;

    @Option(names={"-d","--detail-print"},description="Print all the process of solving, in unfolded-box format")
    static public boolean detailPrintOption;

    @Parameters(description = "scrambles")
    static public String[] scrambles;
    
    static public String version="v1.0.0";
    static RubikFROOK rubikFROOK = new RubikFROOK();

    public Integer call() {
        // if(versionOption){
        //     System.out.println(version);
        //     return 0;
        // }
        rubikFROOK.mainSolving(scrambles);
        return 0;
    }

    public static void main(String[] args){
        int exitCode = new CommandLine(new com.ggFROOK.run()).execute(args);
        System.exit(exitCode);
    }
}
