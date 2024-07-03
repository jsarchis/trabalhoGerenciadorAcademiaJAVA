package br.com.jsarchis.model.dao;

import br.com.jsarchis.model.caixaMsg;

import java.util.List;

public class CaixaMsgDAO extends GenericsDAO<caixaMsg, Integer>{
    @Override
    public void inserir(caixaMsg obj) {
        try{
            getCon().getTransaction().begin();
            getCon().persist(obj);
            getCon().getTransaction().commit();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(caixaMsg obj) {
        try{
            getCon().getTransaction().begin();
            getCon().merge(obj);
            getCon().getTransaction().commit();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletar(caixaMsg obj) {
        try{
            getCon().getTransaction().begin();
            if(!getCon().contains(obj)) // Duvida: pq o obj não esta no contexto atual? pq eu enviei somente o id ?
                obj = getCon().merge(obj);
            getCon().remove(obj);
            getCon().getTransaction().commit();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public caixaMsg find(Integer id) {
        try{
            caixaMsg caixaMsg = new caixaMsg();
            caixaMsg.setIdCaixaMsg(id);
            return getCon().find(caixaMsg.class, caixaMsg.getIdCaixaMsg());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<caixaMsg> findAll() {
        try{
            return getCon().createQuery("SELECT DISTINCT caixaMsg FROM caixaMsg caixaMsg", caixaMsg.class).getResultList();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
