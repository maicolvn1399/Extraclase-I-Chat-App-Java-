/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientchat;


import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**Java class used for the client
 *
 * @author Michael Valverde 
 */
public class client extends Thread{
    
    /*
    *Socket used to communicate with the server
    */
    private Socket socket;
    
    /*
    Stream for sending messages to the server
    */
    private ObjectOutputStream objectOutputStream;
    
    //Stream for receiving messages from the server or client
    private ObjectInputStream objectInputStream;
    
    //Window used as GUI for the client 
    private WindowClient windowClient;
    
    //ID to identify the client inside the chat
    private String ID;
    
    //Variable used to determine if the client is listening or not to the server, it runs once the thread with the client starts
    private boolean listening;
    
    //Variable that stores the IP of the host of the server 
    private String host;
    
    // variable that stores the port in which the server listens to the clients
    private int port;
    
    /*
    Class Constructor
    @param window
    @param host 
    @param port
    @param name
    */
    
    Client(WindowClient window,String host, Int port, String name){
        //this.window = window;
        this.host = host;
        this.port = port;
        this.ID = name;
        listening = true;
        this.start();
        
    }//End class constructor 
    
    /*
    Method to run the thread of communication of the client's side
    
    */
    
    public void run(){
        try{
            socket = new Socket(host,port);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("Successfull connection");
            //this.sendConnectionRequest(ID);
            //this.listen();
            
            
        }catch(UnknownHostException ex){
            //JOptionPane.showMessageDialog(window, "Server unknown, you may not have entered a valid IP\n"
                    //+ "or the server is not running \n"
                    //+ "This app will shut down");
            System.exit(0);
        }catch(IOException ex){
           /* JOptionPane.showMessageDialog(window, "Input/Output error, you may not have entered a valid IP\n"
                    + "or you entered a invalid port\n"
                    + "The server may not be running"
                    + "This app will shut down");
            */
            System.exit(0);
            
        }//End catch
    }//End run()
    
    /*
    Method that closes the socket and the streams of communication
    */
    public void disconnect(){
        try{
            objectOutputStream.close();
            objectInputStream.close();
            socket.close();
            listening = false;
            
        }catch(Exception ex){
            System.err.println("Error while closing the elements of communication");
        }//End catch
        
    }//End disconnect()
    
    /*
    Method to send a message to some other client to the server 
    @param receiving_client
    @param message 
    */
    public void sendMessage(String receiving_client, String message){
        LinkedList<String> list = new LinkedList<>();
        //Type
        list.add("MESSAGE");
        //sender 
        list.add(ID);
        //receiving client 
        list.add(receiving_client);
        //Message to send
        list.add(message);
        
        try{
            objectOutputStream.writeObject(list);
            
        }catch(IOException ex){
            System.out.println("Writing or reading error when trying to send the message to the server");
        }//End catch
    }//End sendMessage()
    
    /*
    Method that listens to the server 
    */
    public void listen(){
        try{
            while(listening){
                Object aux = objectInputStream.readObject();
                if(aux != null){
                    if(aux instanceof LinkedList){
                        execute((LinkedList<String>)aux);
                    }else{
                        System.err.println("An unknown object has been sent to the socket");
                    }
                    
                }else{
                    System.err.println("A null has been received through the socket");
                }//End else
                
                
            }//End while
            
        }catch(Exception e ){
            JOptionPane.showMessageDialog(window, "The connection with the server has been lost\n"
                    + "This chat will end"
                    + "The will shut down");
            System.exit(0);
            
        }//End catch
    }//End listen()
    
    /*
    Method 
    */
    
    
    
    
    
    
    
    
    
    
    
}//End client class
