package br.com.jsarchis.model.dao;

import br.com.jsarchis.model.Aluno;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

public class AlunoDAO extends GenericsDAO<Aluno, Integer> {

    @Override
    public void inserir(Aluno obj) {
        try{
            getCon().getTransaction().begin();
            getCon().persist(obj);
            getCon().getTransaction().commit();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Aluno obj) {
        try{
            getCon().getTransaction().begin();
            getCon().merge(obj);
            getCon().getTransaction().commit();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletar(Aluno obj) {
        try{
            getCon().getTransaction().begin();
            if(!getCon().contains(obj))
                obj = getCon().merge(obj);
            getCon().remove(obj);
            getCon().getTransaction().commit();
        } catch (Exception e){
            throw new RuntimeException(e+"Erro Aqui ");
        }
    }

    @Override
    public Aluno find(Integer id) {
         try{
               return getCon().createQuery("SELECT DISTINCT aluno FROM Aluno aluno where "+id+" = aluno.id LEFT JOIN aluno.ficha fi", Aluno.class).getSingleResult();
         } catch (Exception e){
             throw new RuntimeException(e);
         }
    }



    @Override
    public List<Aluno> findAll() {
        try{
            return getCon().createQuery("SELECT DISTINCT aluno FROM Aluno aluno LEFT JOIN aluno.ficha fi left join Ficha.series", Aluno.class).getResultList();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Aluno checkLogin(String login, String senha) throws Exception {
        try{
            Query query = getCon().createQuery("select aluno from Aluno aluno where aluno.login = :log and aluno.senha = :sen");
            query.setParameter("log" , login);
            query.setParameter("sen", senha);
            return (Aluno) query.getSingleResult();
        } catch (NoResultException e){
            return null;
        } catch (NonUniqueResultException e){
            throw new Exception("Conflito de Usuarios (Login e senha duplicados)");
        } catch (Exception e){
            throw new Exception("Erro ao consultar no banco de dados :: "+e.getMessage());
        }
    }

    public Aluno buscarPorMatricula(Integer matricula) throws Exception {
        try{
            Query query = getCon().createQuery("select aluno from Aluno aluno where aluno.matricula = :c");
            query.setParameter("c", matricula);
            return (Aluno) query.getSingleResult();
        } catch (NoResultException e){
            return null;
        } catch (NonUniqueResultException e){
            throw new Exception("Conflito de Usuarios dtplicados");
        } catch (Exception e){
            throw new Exception("Erro ao consultar no banco de dados :: "+matricula);
        }
    }

    public Aluno buscarPorId(Integer id) throws Exception {
        try{
            Query query = getCon().createQuery("select aluno from Aluno aluno where aluno.id = :c");
            query.setParameter("c", id);
            return (Aluno) query.getSingleResult();
        } catch (NoResultException e){
            return null;
        } catch (NonUniqueResultException e){
            throw new Exception("Conflito de Usuarios dtplicados");
        } catch (Exception e){
            throw new Exception("Erro ao consultar no banco de dados :: "+id);
        }
    }

    public void excluirPorMatricula(Integer matricula) throws Exception {
        try {
            Query query = getCon().createQuery("delete from Aluno aluno where aluno.id = :c");
            query.setParameter("c", matricula);
            query.executeUpdate();
        } catch (NonUniqueResultException e) {
            throw new Exception("Conflito de Usuarios dtplicados");
        } catch (Exception e) {
            throw new Exception("Erro ao consultar no banco de dados :: " + matricula);
        }
    }
}
