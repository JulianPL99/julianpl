/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.net.*;

/**
 *
 * @author julian
 */
public class Server {

    public static void main(String[] args) {
        try{
            ServerSocket ss = new ServerSocket(6666);
            Socket s = ss.accept();
            DataInputStream dis = new DataInputStream(s.getInputStream());
            String str = (String)dis.readUTF();
	    if(str.equals("exit")){
		System.exit(1);}
            System.out.println("Cliente ejecuta: " + str);

	    ProcessBuilder processBuilder = new ProcessBuilder();
	    processBuilder.command("bash", "-c", str);
	    Process process = processBuilder.start();

	    BufferedReader reader = 
		new BufferedReader(new InputStreamReader(process.getInputStream()));

	    String line;

	    DataOutputStream dout = new DataOutputStream(s.getOutputStream());

	    while((line = reader.readLine()) != null) {
		dout.writeUTF(line);
		dout.flush();
	    }
	    int exitCode = process.waitFor();
	    System.out.println("\nCodigo exit: " + exitCode);

            ss.close();
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
