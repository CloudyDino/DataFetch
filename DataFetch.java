/*
 * DataFetch.java
 * For Google Code-In: FOSSASIA
 * Author: Amaan
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.NumberFormatException;
import java.net.URL;
import java.util.Scanner;

public class DataFetch {
    public static void main(String[] args) throws Exception {
        
        // Set to 0 in case argument doesn't exist
        int user_id = 0;
        
        // Check for argument
        if (args.length > 0) {
            try {
               user_id = Integer.parseInt(args[0]);
            } catch (NumberFormatException nfe) {   // In case argument is not an integer
                System.out.println("Your should either have 0 or 1 argument(s) and that argument should be a number.\n");
                nfe.printStackTrace();
                System.out.println();
            }
        }
        
        // Get number from them until they give a valid user_id
        while ( !(user_id >= 1 && user_id <= 100)) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter a integer from 1 to 100 inclusive");
            user_id = scan.nextInt();
        }
        System.out.println();
        
        URL typicode_link = new URL("http://jsonplaceholder.typicode.com/posts/"+user_id);
        BufferedReader in = new BufferedReader(new InputStreamReader(typicode_link.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            if (inputLine.indexOf(":") >= 0) {
                String[] line = inputLine.split(":");
                line[0] = clean(line[0]);
                line[1] = clean(line[1]);
                System.out.println(line[0]+": "+line[1]);
            }
        }
        in.close();
    }
    
    // Make string human readable
    public static String clean(String str) {
        // Remove extra spaces and tabs
        str = str.trim();
        
        // Remove comma at end of string
        if (str.charAt(str.length()-1) == ',') {
            str = str.substring(0, str.length()-1);
        }
        
        // Remove quotations around string
        if (str.charAt(0) == '"' && str.charAt(str.length()-1) == '"') {
            str = str.substring(1, str.length()-1);
        }
        return str;
    }
}