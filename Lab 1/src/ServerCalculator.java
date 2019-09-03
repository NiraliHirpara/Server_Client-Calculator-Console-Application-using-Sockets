import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
  			   Author Name : Nirali Hirpara
     Professor's full name : Prof. Tevin Apenteng
	Assignment Description : Lab 3 - Java Networking
         Class Description : Listen to the request given by the client and provide service accordingly. 
           Submission Date : 12 Jan 2019
*/

public class ServerCalculator {

    public static void main(String[] args) {

        //date and time format
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a ");
        Date date = new Date();

        float num1 = 0;
        String sign = null;
        float num2 = 0;
        int result;

        System.out.println();
        System.out.println("ServerCalculator started. Enter STOP to close the connection ");
        System.out.println();

        while (true) {

            try {
                // create a server socket port number:8177
                ServerSocket s = new ServerSocket(2234);

                // listen and wait
                // TCP socket that is connected
                // to the client
                Socket clientSocket = s.accept();

                // connect input and output streams to the socket
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        clientSocket.getInputStream()));

                PrintWriter out = new PrintWriter(new OutputStreamWriter(
                        clientSocket.getOutputStream()));


                //send the date and time to the client
                out.println("Simple Calculator (" + dateFormat.format(date) + ")");
                out.flush();

                System.out.println("\"Simple Calculator (" + dateFormat.format(date) + ")\" - response (sent to the client):");

                //print date and time to console
                System.out.println("Simple Calculator (" + dateFormat.format(date) + ")");
                System.out.println();

                while (true) {

                    String str = in.readLine();

                    if (str == null) {
                        break;
                    } else if (str.equals("STOP")) {
                        System.out.println("- console display: \"STOP command received\"");

                    } else {
                        //create token from the string received from client and save them in variables
                        String[] token = str.split(" ");

                        for (int j = 0; j < token.length; j++) {
                            num1 = Float.valueOf(token[0]);
                            sign = token[1];
                            num2 = Float.valueOf(token[2]);
                        }

                        System.out.println("- console display: \"client requested calculation: " + num1 + sign + num2 + "\"");

                        // calculate and send answer to the client

                        if (sign.equals("*")) {
                            result = (int) (num1 * num2);
                            out.println(result);
                            out.flush();
                            System.out.println("- response (sent to the client):" + "\"" + result + "\"");
                            System.out.println();
                        } else if (sign.equals("+")) {
                            result = (int) (num1 + num2);
                            out.println(result);
                            out.flush();
                            System.out.println("- response (sent to the client):" + "\"" + result + "\"");
                            System.out.println();
                        } else if (sign.equals("-")) {
                            result = (int) (num1 - num2);
                            out.println(result);
                            out.flush();
                            System.out.println("- response (sent to the client):" + "\"" + result + "\"");
                            System.out.println();
                        } else if (sign.equals("/")) {
                            if (num2 == 0) {
                                out.println("a number cannot be divided by 0");
                                out.flush();
                                System.out.println("- response (sent to the client): \"a number cannot be divided by 0\"");
                                System.out.println();

                            } else {
                                result = (int) (num1 / num2);
                                out.println(result);
                                out.flush();
                                System.out.println("- response (sent to the client):" + "\"" + result + "\"");
                                System.out.println();
                            }
                        }
                    }
                }
                clientSocket.close();
                s.close();

            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
    }

}
