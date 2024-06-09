import java.io.*;

import java.net.*;
import java.util.*;
public class server {

static boolean _num = true;
static boolean _lett= true;

static String _number , _letter;

static int _numberVal;


public static void main(String[] args) {
    while (true) {
        try{
            _lett=true;
            _num=true;
            ServerSocket _Socket = new ServerSocket(4040);
            System.out.println("Server is running and wating for connections.....");
    
            Socket _ClientSocket = _Socket.accept();
    
            BufferedReader _Reader = new BufferedReader(new InputStreamReader(_ClientSocket.getInputStream()));
    
            //can be print reader
            DataOutputStream _ClientOutPut = new DataOutputStream(_ClientSocket.getOutputStream());
    
            String _request = _Reader.readLine();
            
            
            
        

            if(_request==null||_request.isEmpty()){
                check(_request);
            }
            else{
                StringTokenizer _tokenAnalyze = new StringTokenizer(_request);
                

                if (_tokenAnalyze.hasMoreTokens()){
    
                _letter = _tokenAnalyze.nextToken();
    
                if(_letter.trim().equalsIgnoreCase("B")||_letter.trim().equalsIgnoreCase("H")){
                    if(_tokenAnalyze.hasMoreTokens()){
                        _number = _tokenAnalyze.nextToken();
                        
    
                        if (_number ==null || _number.isEmpty()||_number=="") {
                            _num =false;
                            
                        }
                    }
                }else{
                    _lett =false;
                    
                   
                   
                }
    
    
            }else{
                _num=false;
                _lett=false;
            }
            }
    
            
    
            if (_num==false&&_lett==false) {
                String _result = "500 Request is Empty";
                _ClientOutPut.writeBytes(_result+"\n");
                
            }
            else if (_num==true && _lett==false){
                String _result = "300 Bad request ";
    
                _ClientOutPut.writeBytes(_result+'\n');
            }
            else if (_num==false && _lett==true){
                String _result = "400 the number is missing";
                _ClientOutPut.writeBytes(_result+'\n');
            }
    
            else if ( _num==true && _lett== true){
                try{
                    _numberVal = Integer.parseInt(_number);
                    _number="";
    
                    if (_letter.equalsIgnoreCase("B")) {
                        String __BinaryVal = Convert2Binary(_numberVal);
    
                        String _result = "200"+" "+ __BinaryVal;
                        _ClientOutPut.writeBytes(_result+'\n');
                        _request="";
                        
                        
                    }
                    else if(_letter.equalsIgnoreCase("H")){
                        String _HexDecimalVal = Convert2Hexa(_numberVal);
                        String _result = "200"+" "+ _HexDecimalVal;
                        _ClientOutPut.writeBytes(_result+'\n');
                        _request="";
                    }
                }catch (NumberFormatException e){String _result = "400 The Number is missing";_ClientOutPut.writeBytes(_result+'\n');}
    
    
            }
    
    
        }catch( IOException e ){
            
        }
    }
    
       
        
    
    
   



}
public static String Convert2Binary(int _numberVal){
    String _result = "("+Integer.toBinaryString(_numberVal)+")"+"B";
    return _result; 
}

public static String Convert2Hexa(int _numberVal){
    String _result = "("+ Integer.toHexString(_numberVal)+")"+"H";
    return _result;
}
private static String check(String _request ){
    if (_request==null||_request.isEmpty()) {
        return "500 Bad request";
        
    }
    return"";
}


  
}