/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Conf.ServerConnection;
import POJO.Guide;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Ndahigeze
 */
public class GuideService {
    
    ServerConnection con;
    Converter c;
    Gson g;
     public String createLocation(Guide u){
        con=new ServerConnection();
       Invocation.Builder invoc = con.target().path("guide/create").
                request(MediaType.APPLICATION_JSON).
               accept(MediaType.APPLICATION_JSON);
        Response response = invoc.post(Entity.entity(u, MediaType.APPLICATION_JSON));
        Message msg=response.readEntity(Message.class);
        return msg.getMessage(); 
                
     }
     
     public String updateGuider(Guide u){
        con=new ServerConnection();
       Invocation.Builder invoc = con.target().path("guide/update").
                request(MediaType.APPLICATION_JSON).
               accept(MediaType.APPLICATION_JSON);
         Response response = invoc.put(Entity.entity(u, MediaType.APPLICATION_JSON));
         Message msg=response.readEntity(Message.class);
         return msg.getMessage();
     }

     public List<Guide> viewGuiders(){
        con=new ServerConnection();
        con.target();
        String response=con.target().path("guide/viewGuiders/").request(MediaType.APPLICATION_JSON).get(JsonArray.class).toString();
               
                 Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                Type type = new TypeToken<List<Guide>>() {}.getType();
                List<Guide> list = gson.fromJson(response, type);
        return list;         
     }
     
      public List<Guide> viewAsigned(Date dt){
        con=new ServerConnection();
        con.target();
        String date=new SimpleDateFormat("yyyy-MM-dd").format(dt);
        String response=con.target().path("guide/asigned/"+date).request(MediaType.APPLICATION_JSON).get(JsonArray.class).toString();               
                 Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                Type type = new TypeToken<List<Guide>>() {}.getType();
                List<Guide> list = gson.fromJson(response, type);
        return list;         
     }
      public List<Guide> viewNotAsigned(Date dt){
        con=new ServerConnection();
        con.target();
        String date=new SimpleDateFormat("yyyy-MM-dd").format(dt);
        String response=con.target().path("guide/notAsigned/"+date).request(MediaType.APPLICATION_JSON).get(JsonArray.class).toString();
               
                 Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                Type type = new TypeToken<List<Guide>>() {}.getType();
                List<Guide> list = gson.fromJson(response, type);
        return list;         
     }
}
