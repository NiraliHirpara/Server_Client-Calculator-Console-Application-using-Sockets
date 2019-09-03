import java.io.*;
import java.net.*;
import java.util.Scanner;

/*
 			   Author Name : Nirali Hirpara
     Professor's full name : Prof. Tevin Apenteng
	Assignment Description : Lab 1 - Java Networking
         Class Description : Client class to give instruction to the server. 
           Submission Date : 12 Jan 2019
*/
 
public class Client {

    public static void main(String[] args) {

        //read from console
        Scanner keyboard = new Scanner(System.in);

        String userInput;
        String choice = "Yes";

        try {

            // Open a socket to connect to the server.
            // server IP address/name: "localhost"
            // port number 2234
            Socket clientSocket = new Socket("localhost", 2234);


            // create output stream and input stream to the socket.
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
                    true);

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    clientSocket.getInputStream()));

            System.out.println();
            System.out.println("I/O streams connected to the socket");
            System.out.println();

            System.out.println("\"echo from the server: " + in.readLine() + "\"" + "- console");
            System.out.println();

            while (true) {

                System.out.print("Command >>");
                userInput = keyboard.nextLine();
                System.out.println();

                if (userInput != null) {
                    // send data to the server
                    out.println(userInput);
                    out.flush(); // data sent immediately!

                    // receive data from the server
                    String instr = in.readLine();

                    if (instr.contains("a number cannot be divided by 0")) {
                        System.out.println("\"echo from the server: a number cannot be divided by 0\" - console :");
                        System.out.println();
                    } else {
                        System.out.println("\"Calculation result:" + instr + "\" - console");
                        System.out.println();
                    }

                    System.out.println("Continue (Yes/No)?");
                    choice = keyboard.nextLine();
                    System.out.println();

                    if (choice.equalsIgnoreCase("No")) {
                        //sends "STOP" to server
                        out.println("STOP");
                        out.flush();
                        break;
                    }
                }
            }
            
            System.out.println("client stopped running");
            // Close the streams.
            keyboard.close();
            out.close();
            in.close();

            // Close the socket.
            clientSocket.close();

        } catch (IOException e) {
            System.out.println("Exception : " + e);
        }
    }

}
