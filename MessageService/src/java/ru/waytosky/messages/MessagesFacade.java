/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.messages;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ayrat
 */
@Stateless
public class MessagesFacade extends AbstractFacade<Messages> {

    @PersistenceContext(unitName = "MessageServicePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MessagesFacade() {
        super(Messages.class);
    }
    
}
