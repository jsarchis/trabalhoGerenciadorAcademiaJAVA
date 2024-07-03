package br.com.jsarchis.model;

import org.eclipse.persistence.annotations.CascadeOnDelete;

import javax.persistence.*;
import java.util.List;

@Entity
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSerie;

    @ManyToOne
    @JoinColumn(name = "id_ficha")
    private Ficha ficha;

    @Column(length = 100)
    private String exercicio;

    @Column(length = 10)
    private String series;
    @Column(length = 10)
    private String repeticoes;
    @Column(length = 10)
    private String carga;

    @Column
    private char treinoABC;

    public char getTreinoABC() {
        return treinoABC;
    }

    public Serie(int idSerie, Ficha ficha, String exercicio, String series, String repeticoes, String carga, char treinoABC) {
        this.idSerie = idSerie;
        this.ficha = ficha;
        this.exercicio = exercicio;
        this.series = series;
        this.repeticoes = repeticoes;
        this.carga = carga;
        this.treinoABC = treinoABC;
    }

    public Serie(Ficha ficha, String exercicio, String series, String repeticoes, String carga, char treinoABC) {
        this.ficha = ficha;
        this.exercicio = exercicio;
        this.series = series;
        this.repeticoes = repeticoes;
        this.carga = carga;
        this.treinoABC = treinoABC;
    }

    public void setTreinoABC(char treinoABC) {
        this.treinoABC = treinoABC;
    }

    public int getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(int idSerie) {
        this.idSerie = idSerie;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public Serie() {
    }

    public Serie(String exercicio, String series, String repeticoes, String carga) {
        this.exercicio = exercicio;
        this.series = series;
        this.repeticoes = repeticoes;
        this.carga = carga;
    }

    public String getExercicio() {
        return exercicio;
    }

    public void setExercicio(String exercicio) {
        this.exercicio = exercicio;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(String repeticoes) {
        this.repeticoes = repeticoes;
    }

    public String getCarga() {
        return carga;
    }

    public void setCarga(String carga) {
        this.carga = carga;
    }
}
