package com.mooneyserver.dublinpubs.controller;

import com.mooneyserver.dublinpubs.models.PersistenceConstants;
import com.mooneyserver.dublinpubs.models.Pub;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.mooneyserver.dublinpubs.models.PersistenceConstants.PERSISTENCE_UNIT_NAME;

@ApplicationScoped
public class PubController {

    @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;

    public List<Pub> getAllPubs() {
        return entityManager
              .createNamedQuery(PersistenceConstants.QUERY_FIND_ALL_PUBS, Pub.class)
              .getResultList();
    }

    public Optional<Pub> getPubById(Long id) {
    	return Optional.ofNullable(entityManager.find(Pub.class, id));        
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Pub savePub(Pub pub) {
        return entityManager.merge(pub);
    }
}