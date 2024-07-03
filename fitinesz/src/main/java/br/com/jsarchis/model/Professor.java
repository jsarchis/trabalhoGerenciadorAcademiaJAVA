package br.com.jsarchis.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Professor extends Usuario{

    @Column(length = 8)
    private String cref;

    public Professor(int id, String nome, String login, String senha) {
        super(id, nome, login, senha);
    }

    public Professor(int id, String nome, String login, String senha, String cref) {
        super(id, nome, login, senha);
        this.cref = cref;
    }

    public Professor(String cref) {
        this.cref = cref;
    }

    public Professor() {
    }

    public String getCref() {
        return cref;
    }

    public void setCref(String cref) {
        this.cref = cref;
    }
}
