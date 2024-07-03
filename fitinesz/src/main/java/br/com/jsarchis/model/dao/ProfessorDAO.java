package br.com.jsarchis.model.dao;


import br.com.jsarchis.model.Aluno;
import br.com.jsarchis.model.Professor;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.util.List;

public class ProfessorDAO extends GenericsDAO<Professor, Integer> {
    @Override
    public void inserir(Professor obj) {
        try{
            getCon().getTransaction().begin();
            getCon().persist(obj);
            getCon().getTransaction().commit();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Professor obj) {
        try{
            getCon().getTransaction().begin();
            getCon().merge(obj);
            getCon().getTransaction().commit();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletar(Professor obj) {
        try{
            getCon().getTransaction().begin();
            if(!getCon().contains(obj))
                obj = getCon().merge(obj);
            getCon().remove(obj);
            getCon().getTransaction().commit();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Professor find(Integer id) {
        try{
            Professor professor = new Professor();
            professor.setId(id);
            return getCon().find(Professor.class, professor.getId());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Professor> findAll() {
        try{
            return getCon().createQuery("SELECT DISTINCT professor FROM Professor professor", Professor.class).getResultList();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Professor checkLogin(String login, String senha) throws Exception {
        try{
            Query query = getCon().createQuery("SELECT DISTINCT professor FROM Professor professor WHERE professor.login = :login AND professor.senha = :senha");
            query.setParameter("login", login);
            query.setParameter("senha", senha);

            return (Professor) query.getSingleResult();
        } catch (NoResultException e){
            return null;
        } catch (NonUniqueResultException e){
            throw new Exception("Conflito de Usuarios (Login e senha duplicados)");
        } catch (Exception e){
            throw new Exception("Erro ao consultar no banco de dados :: "+e.getMessage());
        }
    }

    public Professor buscarPorCref(String cref) throws Exception {
        try{
            Query query = getCon().createQuery("select professor from Professor professor where professor.cref = :c");
            query.setParameter("c", cref);
            return (Professor) query.getSingleResult();
        } catch (NoResultException e){
            return null;
        } catch (NonUniqueResultException e){
            throw new Exception("Conflito de Usuarios dtplicados");
        } catch (Exception e){
            throw new Exception("Erro ao consultar no banco de dados :: "+e.getMessage());
        }
    }
}

