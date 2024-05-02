package cta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDBGM {
	int port = 5008;
	private ServerSocket serverSocket;
	private InputStream input;

	public static void main(String[] args) {
		 ServerDBGM dbgm = new ServerDBGM();
         dbgm.initDBGM();		 
			
	}

	public void initDBGM()  {			
	try {
		
	serverSocket = new ServerSocket(port);
	
		 
        System.out.println("Server is listening on port " + port);

     
            Socket socket = serverSocket.accept();

            System.out.println("New client connected");

            input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            
            int data = input.read();
            while(data != -1) {
              //do something with data...
             System.out.print(data+"="+(char) data +",");

              data = input.read();
            }
            System.out.println("\n");
            
            
        

		} catch (IOException ex) {
			System.out.println("Server exception: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				input.close();
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
}
