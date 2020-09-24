/**
 * Michael Valverde Navarro
 * Carné: 2020044189
 * Curso: Algoritmos y estructuras de datos I
 * 2 semestre 2020
 *
 */
package serverchat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *Class that makes threads, these threads listen to what the clients say 
 * there's a thread for every client connected to a server 
 *each thread can listen to one specific client
 * @author Michael Valverde 
 */
public class ClientThread extends Thread{
    
    /**
     * Socket used for communication
     */
    private final Socket socket;
    
    /**
     * Stream to send objects
     */
    private ObjectOutputStream objectOutputStream;
    
    /***
     * Stream to receive objects 
     */
    private ObjectInputStream objectInputStream;
    
    /**
     * Server that belongs to this thread 
     */
    private Server server;
    
    /**
     * ID of the client in which the client uses to communicate
     */
    private String ID;
    
    /**
     * Boolean that is true when the thread is listening for new connections, otherwise is false 
     */
    private boolean listening;
    
    /**
     * Constructor of this class
     * @param socket
     * @server
     */
    public ClientThread(Socket socket, Server server){
        this.server = server;
        this.socket = socket;
        try{
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            
        }catch(IOException ex){
            System.err.println("Error initializing objectOutputStream and the objectInputStream");
        }//End catch
    }
        
        /**
         * Method in charge to close the socket that is communicating
         */
        public void disconnect(){
            try{
                socket.close();
                listening = false;
                
            }catch(IOException ex){
                System.err.println("Error when closing client");
                
        }//End catch
    }//End disconnect()
        
        
}//End class 
