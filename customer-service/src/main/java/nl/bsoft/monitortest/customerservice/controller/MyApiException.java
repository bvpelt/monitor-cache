package nl.bsoft.monitortest.customerservice.controller;

public class MyApiException extends Exception {

    public MyApiException(String string, Exception ex) {
        super(string, ex);
    }
}