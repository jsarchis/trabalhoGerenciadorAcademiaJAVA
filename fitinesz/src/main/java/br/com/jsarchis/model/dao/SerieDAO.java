package br.com.jsarchis.model.dao;

import br.com.jsarchis.model.Aluno;
import br.com.jsarchis.model.Ficha;
import br.com.jsarchis.model.Serie;

import javax.persistence.Query;
import java.util.List;

public class SerieDAO extends GenericsDAO<Serie, Integer>{
    @Override
    public void inserir(Serie obj) {
        try{
            getCon().getTransaction().begin();
            getCon().persist(obj);
            getCon().getTransaction().commit();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Serie obj) {
        try{
            getCon().getTransaction().begin();
            getCon().merge(obj);
            getCon().getTransaction().commit();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletar(Serie obj) {
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
    public Serie find(Integer id) {
        try{
            Serie serie = new Serie();
            serie.setIdSerie(id);
            return getCon().find(Serie.class, serie.getIdSerie());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Serie> findAll() {

        return null;
    }

    public List<Serie> acharSeries(Integer id){
        try{
            return getCon().createQuery("SELECT DISTINCT serie FROM Serie serie where serie.ficha.idFicha ="+id, Serie.class).getResultList();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
