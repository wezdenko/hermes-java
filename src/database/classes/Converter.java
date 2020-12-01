package database.classes;

public class Converter {
    
    public static String IntToString(int i){
        try{
        return String.valueOf(i);
        }
        catch(Exception e){
            AlertBox.display("Exception", "Wrong data type");
            return "0";
        }
    }

    public static int StringToInt (String s){
        try{
        Integer decode = Integer.decode(s);
        return decode;
        }
        catch(Exception e){
            AlertBox.display("Exception", "Wrong data type");
            return 0;
        }
    }
}
