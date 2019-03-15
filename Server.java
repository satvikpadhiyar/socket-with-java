import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.io.*;
import java.net.*;
import java.util.Scanner;
class Server
{
    public static void main(String args[])throws IOException
    {
        Scanner sc=new Scanner(System.in);
        int port;
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now=LocalDateTime.now();
        System.out.print("Enter the port to be used : ");
        port=sc.nextInt();
        ServerSocket s=new ServerSocket(port);
        Socket s1=new Socket();
        s1=s.accept();
        DataInputStream dis=new DataInputStream(s1.getInputStream());
        DataOutputStream dos=new DataOutputStream(s1.getOutputStream());
        String message,mes;
        System.out.print("Enter Your user name : ");
        String us=sc.next();
        dos.writeUTF(us);
        mes=dis.readUTF();
        File f=new File("D:\\"+us+" to "+mes+".txt");
        FileWriter fw=new FileWriter(f,true);
        PrintWriter pw=new PrintWriter(fw);
        fw.write(dtf.format(now));
        pw.println();
        fw.close();
        while(true)
        {
        	dtf=DateTimeFormatter.ofPattern("HH:mm");
            fw=new FileWriter(f,true);
            pw=new PrintWriter(fw);
            message=dis.readUTF();
            now=LocalDateTime.now();
            pw.println();
            fw.write(dtf.format(now));
            pw.println();
            fw.write(mes+" : "+message);
            pw.println();
            pw.println();
            System.out.println(mes+" : "+message);
            System.out.print(us+" : ");
            message=sc.nextLine();
            fw.write(dtf.format(now));
            pw.println();
            fw.write(us+" : "+message);
            pw.println();
            dos.writeUTF(message);
            fw.close();
        }
    };
};
