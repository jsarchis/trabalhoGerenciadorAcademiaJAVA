package br.com.jsarchis.model.dao;

import br.com.jsarchis.model.Aluno;
import br.com.jsarchis.model.Ficha;

import javax.persistence.Query;
import java.util.List;

public class FichaDAO extends GenericsDAO<Ficha, Integer> {
    @Override
    public void inserir(Ficha obj) {
        try{
            getCon().getTransaction().begin();
            getCon().persist(obj);
            getCon().getTransaction().commit();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Ficha obj) {
        try{
            getCon().getTransaction().begin();
            getCon().merge(obj);
            getCon().getTransaction().commit();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletar(Ficha obj) {
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
    public Ficha find(Integer id) {
        try{
            Ficha ficha = new Ficha();
            ficha.setIdFicha(id);
            return getCon().find(Ficha.class, ficha.getIdFicha());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ficha> findAll() {
        try{
            return getCon().createQuery("SELECT DISTINCT ficha FROM Ficha ficha", Ficha.class).getResultList();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Ficha findByAlunoId(Integer id){
        try{
            Query query = getCon().createQuery("SELECT ficha From Ficha ficha LEFT join ficha.aluno where :id =  ficha.aluno.id");
            query.setParameter("id" , id);
            return (Ficha) query.getSingleResult();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

