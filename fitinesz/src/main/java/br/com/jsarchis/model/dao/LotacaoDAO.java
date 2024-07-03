package br.com.jsarchis.model.dao;

import br.com.jsarchis.model.Lotacao;
import br.com.jsarchis.model.Professor;

import java.util.List;

public class LotacaoDAO extends  GenericsDAO<Lotacao, Integer> {
    @Override
    public void inserir(Lotacao obj) {
        try{
            getCon().getTransaction().begin();
            getCon().persist(obj);
            getCon().getTransaction().commit();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Lotacao obj) {
        try{
            getCon().getTransaction().begin();
            getCon().merge(obj);
            getCon().getTransaction().commit();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletar(Lotacao obj) {
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
    public Lotacao find(Integer id) {
        try{
            Lotacao lotacao = new Lotacao();
            lotacao.setId(id);
            return getCon().find(Lotacao.class, lotacao.getId());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Lotacao> findAll() {
        return null;
    }
}
