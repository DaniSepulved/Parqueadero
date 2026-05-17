package com.example.Parqueadero.exception;

// Clase para representar la estructura de una respuesta de error en la API.
public class ErrorResponse {
    private String message;  
    private int code;       
    private String detail;  

    // Constructor para inicializar los campos de la respuesta de error.
    public ErrorResponse(String message, int code, String detail) {
        this.message = message;
        this.code = code;
        this.detail = detail;
    }

    // Getters y setters para los campos de la respuesta de error.
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }
    public String getDetail() { return detail; }
    public void setDetail(String detail) { this.detail = detail; }
}