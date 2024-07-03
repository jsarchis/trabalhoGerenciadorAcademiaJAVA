package br.com.jsarchis.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public abstract class GenericsDAO<C, K> {

    private EntityManager con;

    public GenericsDAO(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fit_PU");
        con = emf.createEntityManager();
    }

    public EntityManager getCon(){
        return con;
    }

    public abstract void inserir(C obj);

    public abstract  void update(C obj);

    public abstract  void deletar(C obj);

    public abstract C find(K id);

    public abstract List<C> findAll();
}
