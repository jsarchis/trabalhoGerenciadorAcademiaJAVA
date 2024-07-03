package br.com.jsarchis.model.dao;

import br.com.jsarchis.model.Adm;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.util.List;

public class AdmDAO extends GenericsDAO<Adm, Integer> {
    @Override
    public void inserir(Adm obj) {
        try{
            getCon().getTransaction().begin();
            getCon().persist(obj);
            getCon().getTransaction().commit();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Adm obj) {
        try{
            getCon().getTransaction().begin();
            getCon().merge(obj);
            getCon().getTransaction().commit();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletar(Adm obj) {
        try{
            getCon().getTransaction().begin();
            getCon().remove(obj);
            getCon().getTransaction().commit();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Adm find(Integer id) {
        try{
            Adm adm = new Adm();
            adm.setId(id);
            return getCon().find(Adm.class, adm.getId());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Adm> findAll() {
        try{
            return getCon().createQuery("SELECT DISTINCT adm FROM Adm adm", Adm.class).getResultList();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Adm checkLogin(String login, String senha) throws Exception {
        try{
            Query query = getCon().createQuery("SELECT DISTINCT adm FROM Adm adm WHERE adm.login = :login AND adm.senha = :senha");
            query.setParameter("login", login);
            query.setParameter("senha", senha);

            return (Adm) query.getSingleResult();
        } catch (NoResultException e){
            return null;
        } catch (NonUniqueResultException e){
            throw new Exception("Conflito de Usuarios (Login e senha duplicados)");
        } catch (Exception e){
            throw new Exception("Erro ao consultar no banco de dados :: "+e.getMessage());
        }
    }
}
