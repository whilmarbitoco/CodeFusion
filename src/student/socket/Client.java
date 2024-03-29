package student.socket;

import Core.Packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import student.Interface.studentInterface;

public class Client implements Runnable {

    public Socket socket;
    public String host;
    public int port;
    public ObjectOutputStream out;
    public ObjectInputStream in;
    public studentInterface listener;

    public Client(studentInterface listener) {
//        this.host = host;
//        this.port = port;
        this.listener = listener;
    }
    
    public void setNetwork(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(this.host, this.port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            Packet recPacket;
            while (!(recPacket = (Packet) in.readObject()).message.equals("/close")) {

                if (recPacket.auth) {
                    this.listener.authorize(recPacket.auth, recPacket.student);
                } 
                if(!recPacket.auth && recPacket.message.equals("x0FailedLogin")) {
                    this.listener.loginFailed();
                }

                if (recPacket.message.equals("Quiz2Ans")) {
                    this.listener.setQuiz(recPacket.quizes);
                    this.listener.setIns(recPacket.instruction);
                }
                
                if (recPacket.message.equals("0xAlreadyLogin")) {
                    this.listener.alreadyLogin();
                }

            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public void sendMessage(Packet packet) {
        try {
            out.writeObject(packet);
        } catch (IOException | NullPointerException e) {
            this.listener.serverOffline();
        }
    }

}
