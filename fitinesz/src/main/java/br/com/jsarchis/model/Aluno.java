package br.com.jsarchis.model;

import org.eclipse.persistence.annotations.CascadeOnDelete;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class Aluno extends Usuario{

    @OneToOne(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    @CascadeOnDelete
    private Ficha ficha;

    @Column
    private Integer matricula;

    public Aluno(int id, String nome, String login, String senha, Integer matricula) {
        super(id, nome, login, senha);
        this.matricula = matricula;
    }

    public Aluno() {
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Ficha getFichaa() {
        return ficha;
    }

    public void setFichaa(Ficha fichaa) {
        this.ficha = fichaa;
    }
}
