package com.trabalhoweb2.demo.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Mensagem {
    @Id
    @GeneratedValue
    private int id;
    private String mensagem;
    @ManyToOne
    private Usuario receptor;
    @ManyToOne
    private Usuario emissor;
    private Date data;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Usuario getReceptor() {
        return receptor;
    }

    public void setReceptor(Usuario receptor) {
        this.receptor = receptor;
    }

    public Usuario getEmissor() {
        return emissor;
    }

    public void setEmissor(Usuario emissor) {
        this.emissor = emissor;
    }
}
