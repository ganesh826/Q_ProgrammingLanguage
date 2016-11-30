import java.util.*;
import java.io.*;
public class Queue {
    public static void main(String[] args) {

        BufferedReader reader;
        StringBuilder  tempSource ;
        String         line;
        String         source;
        String[]       tokenArray;
        Parser          parser;
        LexicalAnalyzer le;

        source = "";

        if (args.length != 1) {
            System.out.println ("Usage:   Queue [SOURCE_FILE]\n"    +
                                "example: Queue myfile.queue");
            System.exit(1);
        }

        try {
            //reader = new BufferedReader(new FileReader("source.queue"));
            reader = new BufferedReader(new FileReader(args[0])); 
            tempSource = new StringBuilder();
            line       = reader.readLine();

            while (line != null) {
                tempSource.append(line);
                tempSource.append(System.lineSeparator());
                line = reader.readLine();
            }
            reader.close();
            source = tempSource.toString();
        }
        catch (FileNotFoundException e) {
            System.out.println ("FileNotFoundException: cannot find file" + args[1]);
            System.exit(1);
        }
        catch (IOException e) {
            System.out.println ("IOException on file read");
            System.exit(1);
        }

        /*
          - Parser -
        */
        parser = new Parser(source);
        tokenArray = parser.getTokens();

        System.out.printf("############## Parsing.... #################\n");
        for (String token : tokenArray) {
            System.out.printf("Next token is %s\n", token);
        }
        System.out.printf("\n\n############## Analyzing.... #################\n");

        /*
          - Lexical Analysis -
        */
          le = new LexicalAnalyzer(tokenArray);
          le.analyzeTokens();

          System.out.printf("\n\n############## Compiling.... #################\n");
          /*
            - Compiler -
          */

          OurCompiler oc = new OurCompiler();
          oc.compileSource();

          System.out.printf("\n\n############## Done! #################\n");
          System.out.printf("Your file is compiled. You can run it by running: \n\n\tjava output\n\nHappy queueing!\n");


    }

}