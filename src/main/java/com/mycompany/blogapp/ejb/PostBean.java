/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blogapp.ejb;

import com.mycompany.blogapp.entities.Posts;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author litemoreapple_dev
 */
@Stateless
public class PostBean {
    @PersistenceContext(unitName = "com.mycompany_blogapp_war_1.0-SNAPSHOTPU")
    EntityManager em;
    
    public List getAllPosts(){
        return em.createNamedQuery("Posts.findAll", Posts.class).getResultList();
    }
    
    public Posts getById(int id){
        return em.find(Posts.class, id);
    }
    
    public void update(Posts post){
         em.merge(post);
    }
    
    public void create(Posts post){
        em.persist(post);
    }
    
    public void delete(Posts post){
      if(!em.contains(post)){
        post = em.merge(post);
      }
      em.remove(post);
    }
    
}
