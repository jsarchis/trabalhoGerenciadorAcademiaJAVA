package br.com.jsarchis.model;

import br.com.jsarchis.model.dao.AlunoDAO;
import br.com.jsarchis.model.dao.ProfessorDAO;

import javax.persistence.Entity;

@Entity
public class Adm extends Usuario{

    public void CadastraAluno(Aluno a){
        new AlunoDAO().inserir(a);
    }

    public void CadastraProfessor(Professor p){
        new ProfessorDAO().inserir(p);
    }

}
