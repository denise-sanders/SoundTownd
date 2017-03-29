package computersnewmedia;

import java.io.*;
import java.net.*;
import java.util.Map;
import javax.sound.sampled.*;


public class Server {

    Map<Integer, ActionObject> actionObjectLocations;
    Map<Integer, Player> playerLocations;

    // The server will recieve LOGIN, DISCONNECT, UPDATE, ACTION packets
    public static void processPacket(DatagramPacket p){
        System.out.println(p.getData());
    }

    public static void main(String[] args) throws Exception
    {



        SourceDataLine sourceDataLine;
        AudioFormat audioFormat = new AudioFormat(8000.0f, 16, 1, true, true);
        byte tempBuffer[] = new byte[1024];

        // play soundfile from client
        System.out.println("Server: reading from 127.0.0.1:6666");
        try{
            DataLine.Info dataLineInfo1 = new DataLine.Info(SourceDataLine.class, audioFormat);

            sourceDataLine = (SourceDataLine)AudioSystem.getLine(dataLineInfo1);
            sourceDataLine.open(audioFormat);
            sourceDataLine.start();

            DatagramSocket server_socket=new DatagramSocket(Integer.valueOf(4200));




            while(true)
            {
                if(sourceDataLine.isControlSupported(FloatControl.Type.MASTER_GAIN))
                {
                    FloatControl volume = (FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
                    volume.setValue(0.0F);//use this to adjust the output
                }
                DatagramPacket receive_packet = new DatagramPacket(tempBuffer,tempBuffer.length);
                server_socket.receive(receive_packet);
                sourceDataLine.write(receive_packet.getData(), 0,tempBuffer.length);

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
    }
}