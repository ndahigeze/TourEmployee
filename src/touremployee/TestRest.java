/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package touremployee;

import POJO.Users;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;

/**
 *
 * @author Ndahigeze
 */
public class TestRest {

    public static void main(String args[]) {
        // TODO code application logic here
        ClientConfig c = new ClientConfig();
        Client client = ClientBuilder.newClient(c);
        WebTarget target = client.target("http://localhost:9998/service/");

       //  
       
      /*  //  UserService k=new UserService();
         //List<Users> list=k.login("pass", "passw");
       String response=target.path("/Users/").request(MediaType.APPLICATION_JSON).get(JsonArray.class).toString();
               Gson gson = new Gson();
               Type type = new TypeToken<List<Users>>() {}.getType();
        
                List<Users> list = gson.fromJson(response, type);
                list.forEach((u) -> {
                    System.out.println(u.getCode());
                     System.out.println(u.getFirstName());
                      System.out.println(u.getGender());
                        System.out.println(u.getGender());
                         System.out.println(u.getLastName());
                          System.out.println(u.getEmainl());
                          System.out.println(u.getPrivilege().getName());
                           System.out.println(u.getUserName());
                        
        });*/
        //return list;
     /* Privilege p=new Privilege();
       p.setCode(1);
        Users u = new Users();
       // u.setCode("1000");
        u.setGender("gender");
        u.setLastName("lastName");
        u.setFirstName("dfs");
        u.setPassword("pass");
        u.setUserName("usern");
        u.setEmainl("mail");
       //u.setPrivilege(p);
    
        ///Spacial For Sending Post And Saving
        Invocation.Builder invocationBuilder = target.path("applicationService/getDetails").request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(u, MediaType.APPLICATION_JSON));
     */
      
    }
}
