package ba.unsa.etf.rpr.exceptions;

public class FlightsException extends Exception{
    public FlightsException(String msg, Exception reason){
        super(msg, reason);
    }

    public FlightsException(String msg){
        super(msg);
    }
}
