package br.com.jsarchis.model;

import org.eclipse.persistence.annotations.CascadeOnDelete;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Ficha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFicha;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_aluno")
    @CascadeOnDelete
    private Aluno aluno;

    @OneToMany(mappedBy = "ficha", cascade = CascadeType.ALL, orphanRemoval = true)
    @CascadeOnDelete
    private ArrayList<Serie> series;

    @Column
    private boolean isAtual;

    public Ficha(Aluno aluno, boolean isAtual) {
        this.aluno = aluno;
        this.isAtual = isAtual;
    }

    public Ficha() {
        this.series = new ArrayList<>();
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public ArrayList<Serie> getSeries() {
        return series;
    }

    public void addSerie(Serie s) {
        this.series.add(s);
    }

    public boolean isAtual() {
        return isAtual;
    }

    public void setAtual(boolean atual) {
        isAtual = atual;
    }

    public int getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(int idFicha) {
        this.idFicha = idFicha;
    }

    public void addSeries(Serie serie) {
        this.series.add(serie);
    }
}
