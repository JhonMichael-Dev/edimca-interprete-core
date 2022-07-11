package com.edimca.core.model;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class ValidatorModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Parameters
     */
    private boolean success;
    private String log;
    private Object obj;
    private Map<String, String> mapKeyMessage;
    //private long delay;    

    /**
     * Constructor
     */
    public ValidatorModel() {
    }

    public ValidatorModel(String method) {
        this.success = false;
        this.log = "Not Initialized (" + method + ")";
    }

    public ValidatorModel(boolean success, String log) {
        this.success = success;
        this.log = log;
    }

    public ValidatorModel(boolean success, String log, Object obj) {
        this.success = success;
        this.log = log;
        this.obj = obj;
    }
    
    public ValidatorModel(boolean success) {
        this.success = success;
    }   
    
    /**
     * Getters $ Setters
     */
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
        if (this.success) {
            this.log = "";
        }
    }
    
    public void setSuccessOnly(boolean success) {
        this.success = success;        
    }
    
    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public void setLogByException(Exception ex) {
        this.log = this.getStackTraceAsString(ex);
        this.success = false;
    }
    
    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Map<String, String> getMapKeyMessage() {
        return mapKeyMessage;
    }

    public void setMapKeyMessage(Map<String, String> mapKeyMessage) {
        this.mapKeyMessage = mapKeyMessage;
    }        

    public void addKeyMessage(String key, String message){
        this.addKeyMessage(key, message, this.success);
    }
    
    public void addKeyMessage(String key, String message, boolean success){
        if(this.getMapKeyMessage() == null){
            this.setMapKeyMessage(new HashMap<String, String>());
        }
        this.getMapKeyMessage().putIfAbsent(key, message);
        this.setSuccess(success);
    }

    public boolean isSuccessThenReset() {
        boolean sucessAux = success;
        this.setSuccess(false);
        return sucessAux;
    }

    
    @Override
    public String toString() {
        return "ValidatorModel{" + "success=" + success + ", log=" + log + ", obj=" + obj + '}';
    }
    
    public String getStackTraceAsString(final Throwable throwable) {
        try {
            final StringWriter sw = new StringWriter();
            final PrintWriter pw = new PrintWriter(sw, true);
            throwable.printStackTrace(pw);
            //return sw.getBuffer().toString();
            return throwable.getMessage() + ", detail: " + sw.getBuffer().toString();
        } catch (Exception ex) {
            return "EX (getStackTraceAsString)";
        }
    }
}
