package br.com.jsarchis.model;

import javax.persistence.*;

@Entity
public class Lotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column
    private int alunosSalao;

    public Lotacao() {
    }

    public Lotacao(int alunosSalao) {
        this.alunosSalao = alunosSalao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlunosSalao() {
        return alunosSalao;
    }

    public void setAlunosSalao(int alunosSalao) {
        this.alunosSalao = alunosSalao;
    }
}
