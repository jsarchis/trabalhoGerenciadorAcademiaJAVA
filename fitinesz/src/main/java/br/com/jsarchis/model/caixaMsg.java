package br.com.jsarchis.model;

import javax.persistence.*;

@Entity
public class caixaMsg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCaixaMsg;

    @Column(length = 50)
    private String nome;

    @Column
    private String texto;
    @Column
    private String data;

    public caixaMsg() {
    }

    public int getIdCaixaMsg() {
        return idCaixaMsg;
    }

    public void setIdCaixaMsg(int idCaixaMsg) {
        this.idCaixaMsg = idCaixaMsg;
    }

    public caixaMsg(String nome) {
        this.nome = nome;
        this.texto = texto;
        this.data = data;
    }

    public caixaMsg(String texto, String data) {
        this.texto = texto;
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
