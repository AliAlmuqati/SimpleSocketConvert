import java.util.*;
import  java.io.*;
import java.net.Socket;
public class client {

    public static void main(String[] args) {
        try{
            //make object for the Socket 
            
            //print to the user the list of options  <>
            
            System.out.println("Please choose youre Options Letter \r \n "
            + "B : to convert to Binary \r\n"
            + "H : to convert to Hexadecimal \r\n"
            + "Q : to quit from  the program "
            );
            
            BufferedReader _BufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String _option= _BufferedReader.readLine();
            
            
            if (_option!=null) {
                Socket _Socket = new Socket("Localhost",4040);
                
                PrintWriter _Writer = new PrintWriter(_Socket.getOutputStream(),true);
    
    
                BufferedReader _BufferedReader_server = new BufferedReader(new InputStreamReader(_Socket.getInputStream()));
                
                if (_option.equalsIgnoreCase("B")||_option.equalsIgnoreCase("H")||_option.equalsIgnoreCase("Q")){
                    
                    if (_option.equalsIgnoreCase("Q")){
                        _Writer.println(_option);
    
                        System.out.println("Connection is closed!!!");
                        _Writer.close();
                        _BufferedReader.close();
                        _Socket.close();
                    }else  {
                        String _User_Input="";
                        try{
                            System.out.println("Please Enter the number to convert it :)");
                            int check=0;
                            _User_Input = _BufferedReader.readLine();
                            check=Integer.parseInt(_User_Input);

                        }catch(NumberFormatException e){
                            System.err.println("Please enter an integer number !! ");
                            return;
                        }
                        String _request = data(_option) + " " + Data_Numbr(_User_Input ); 
                        _Writer.println(_request);
    
                        String _respones = _BufferedReader_server.readLine();
    
                        String[] _Divied_respones = _respones.split(" ",2);
    
                        int _code = Integer.parseInt(_Divied_respones[0]);
    
                        switch (_code) {
                            case 200 :
                                System.out.println("Server respones: "+_code+" Ok "+_Divied_respones[1]);
                                break;
                            case 300 :
                                System.err.println(" server error: "+ _code +" "+ "Missing B or H");
                                break;
    
                            case 400 :
                                System.err.println("Server error:  "+_code +" "+ _Divied_respones[1]);
                                break;
    
                            case 500 :
                                System.err.println("Server error:  "+_code+" "+ _Divied_respones[1]);
                                break;
                        }
                    }
                }else{
                   _Writer.close();
                    _BufferedReader.close();
                    _Socket.close();
                    System.out.println("Invalid Options. Enter B , H , Q :(");
    
                }
            }
        }catch (IOException e ){
            System.err.println("Server is colsed :(\nPlease try again later.....");
        }
    }
    public static String data(String data){
        String [] a ={"","a","c","x"};
        Random random_error = new Random();
        Random rad = new Random();
        int Error_percent=random_error.nextInt(2);
        if (Error_percent==0) {
            
            data=a[rad.nextInt(a.length)];
        }
        
        return data;
    }
    public static String Data_Numbr(String data){
        Random Error_percent= new Random();
        int x= Error_percent.nextInt(2);
        if (x==0) {
            data ="";
            
        }
        return data;
    }


   
   
   
}



