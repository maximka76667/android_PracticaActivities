package com.example.practicaactivities;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nick, nombre, apellidos;
    private char sexo;

    public Usuario(String nick, String nombre, String apellidos, char sexo) {
        this.nick = nick;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
}
