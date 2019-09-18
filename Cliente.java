/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author julian
 */
public class Cliente {
    public static void main(String[] args) {
        try{
            Socket s = new Socket("192.168.1.1", 6666);

	    Scanner scanner = new Scanner(System.in);

	    //  prompt for the user's name
	    System.out.print("Ingresa comando: ");
	
	    // get their input as a String
    	    String cmd = scanner.next();

            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF(cmd);
            dout.flush();
           
	    DataInputStream dis = new DataInputStream(s.getInputStream());
	    String str;

	    while(dis != null) {
	    	str = (String)dis.readUTF();
	    	System.out.println(str);
	    }	    

	    dout.close();
            s.close();
        } catch(Exception e){
            
        }
    }
}
