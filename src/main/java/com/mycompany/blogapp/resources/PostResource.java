/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blogapp.resources;

import com.mycompany.blogapp.ejb.PostBean;
import com.mycompany.blogapp.entities.Posts;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author litemoreapple_dev
 */
@Path("posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {
   @EJB
   PostBean postService;
    
    //get all posts from database
    @GET
    public Response getAllPosts(){
        
        return Response.ok(postService.getAllPosts()).build();
        
    }
    
    @GET
    @Path("{id}")
    public Response getPost(@PathParam("id") int id){
        Posts post = postService.getById(id);
        return Response.ok(post).build();
    }
    
    @PUT
    @Path("/update/{id}")
    public Response updatePost(@PathParam("id") int id, Posts post){
        Posts updatePost = postService.getById(id);
        
        updatePost.setTitle(post.getTitle());
        updatePost.setBody(post.getBody());
        
        postService.update(updatePost);
        return Response.ok().build();
    }
    
    @POST
    @Path("/create")
    public Response createPost(Posts post){
        postService.create(post);
        return Response.ok().build();
    }
    
    @DELETE
    @Path("/delete/{id}")
    public Response deletePost(@PathParam("id") int id){
        
        Posts getPost = postService.getById(id);
        postService.delete(getPost);
        return Response.ok().build();
    }
}
