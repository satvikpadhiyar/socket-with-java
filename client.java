import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.io.*;
import java.net.*;
import java.util.Scanner;
class client
{
    public static void main(String args[])throws IOException
    {
        Scanner sc=new Scanner(System.in);
        String IP;
        int port;
        System.out.print("Enter the IP of server : ");
        IP=sc.nextLine();
        System.out.print("Enter the port : ");
        port=sc.nextInt();
        Socket s=new Socket(IP,port);
        DataInputStream dis=new DataInputStream(s.getInputStream());
        DataOutputStream dos=new DataOutputStream(s.getOutputStream());
        String message;
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now=LocalDateTime.now();
		System.out.print("Enter your username : ");
		String serverus;
		String str=sc.next();
		serverus=dis.readUTF();
		dos.writeUTF(str);
        File f=new File("D:\\"+str+" to "+serverus+".txt");
        FileWriter fw=new FileWriter(f,true);
        PrintWriter pw=new PrintWriter(fw);
        pw.println();
        fw.write(dtf.format(now));
        fw.close();
        while(true)
        {
            dtf=DateTimeFormatter.ofPattern("HH:mm");
            fw=new FileWriter(f,true);
            pw=new PrintWriter(fw);
            System.out.print(str+" : ");
            message=sc.nextLine();
            now=LocalDateTime.now();
            fw.write(dtf.format(now));
            pw.println();
            fw.write(str+" : "+message);
            pw.println();
            pw.println();
            dos.writeUTF(message);
            message=dis.readUTF();
            now=LocalDateTime.now();
            System.out.println(serverus+" : "+message);
            fw.write(dtf.format(now));
            pw.println();
            fw.write(serverus+" : "+message);
            pw.println();
            pw.println();
            fw.close();
        }
    };
};
