package database.classes;

import java.sql.Date;

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
    
    public static Double StringToDouble (String s){
        try{
        Double decode = Double.parseDouble(s);
        return decode;
        }
        catch(NumberFormatException e){
            AlertBox.display("Exception", "Wrong data type");
            return 0.0;
        }
    }

    public static String DoubleToString(double i){
        try{
        return String.valueOf(i);
        }
        catch(Exception e){
            AlertBox.display("Exception", "Wrong data type");
            return "0";
        }
    }

    public static Date StringToDate (String s){
        try{
        Date decode = Date.valueOf(s);
        return decode;
        }
        catch(Exception e){
            AlertBox.display("Exception", "Wrong data type\nyyyy-[m]m-[d]d");
            return new Date(0, 0, 0);
        }
    }

    public static String DateToString(Date date){
        try{
        return date.toString();
        }
        catch(Exception e){
            AlertBox.display("Exception", "Wrong data type");
            return "0";
        }
    }


public static Long StringToLong (String s){
    try{
    Long decode = Long.parseLong(s);
    return decode;
    }
    catch(NumberFormatException e){
        AlertBox.display("Exception", "Wrong data type");
        return 0L;
    }
}

public static String LongToString(long i){
    try{
    return String.valueOf(i);
    }
    catch(Exception e){
        AlertBox.display("Exception", "Wrong data type");
        return "0";
    }
}

}
