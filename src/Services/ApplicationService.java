/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Conf.ServerConnection;
import POJO.Application;
import POJO.Location;
import POJO.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.json.JsonArray;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Ndahigeze
 */

public class ApplicationService {
    ServerConnection con;
     public List<Application> requestCheck(){
        con=new ServerConnection(); 
        con.target();
        String response=con.target().path("applicationService/getRequest").request(MediaType.APPLICATION_JSON).get(JsonArray.class).toString();
                Gson gso = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                Type type = new TypeToken<List<Application>>() {}.getType();
                List<Application> list = gso.fromJson(response, type); 
                return list;
    }
     
      public List<Application> requestDone(){
        con=new ServerConnection(); 
        con.target();
        String response=con.target().path("applicationService/getDoneRequest").request(MediaType.APPLICATION_JSON).get(JsonArray.class).toString();
                Gson gso = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                Type type = new TypeToken<List<Application>>() {}.getType();
                List<Application> list = gso.fromJson(response, type);
        return list;
    }
      
      
    public String updateRequest(Application u){
        con=new ServerConnection();
        Invocation.Builder invoc = con.target().path("applicationService/mark").request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        Response response = invoc.put(Entity.entity(u, MediaType.APPLICATION_JSON));
        Message msg=response.readEntity(Message.class);
        return msg.getMessage();
     }
   
    
     public List<Application> searchByLocation(int id,Date dt){
        con=new ServerConnection(); 
        con.target();
        String date=new SimpleDateFormat("yyyy-MM-dd").format(dt);
          String response=con.target().path("applicationService/searchTrip/"+id+"/"+date).request(MediaType.APPLICATION_JSON).get(JsonArray.class).toString();
                Gson gso = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                Type type = new TypeToken<List<Application>>() {}.getType();
                List<Application> list = gso.fromJson(response, type);
           return list;
    }
      
}
