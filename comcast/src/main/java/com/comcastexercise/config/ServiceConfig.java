package com.comcastexercise.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.comcastexercise.resource.LcsResource;


/**
 * This class extends ResourceConfig of Jersey to register Provider packages and classes having REST
 * services.
 */
public class ServiceConfig extends Application {

   @Override
   public Set <Class <?>> getClasses() {

      Set <Class <?>> classes = new HashSet <>();

    classes.add(LcsResource.class);
      return classes;
   }
}