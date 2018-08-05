/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import POJO.Users;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ndahigeze
 * @param <X>
 */
public class Converter<X> {
     Gson g;
  public double toDouble(String amount){
    double num=0;
      try{
        num=Double.parseDouble(amount);
    }catch(NumberFormatException ex){
        
    }
      return num;
  }  
  public int toInt(String amount){
     int num=0;
      try{
        num=Integer.parseInt(amount);
    }catch(NumberFormatException ex){
        
    }
      return num;
  }
  
  public String toJson(Class l){
   g=new Gson();
    String json=g.toJson(l);
    return json;
  }
  public List<X> fromListJson(String json){
    Gson g=new Gson();
     Type type = new TypeToken<List<X>>() {}.getType();
     List<X> list = g.fromJson(json, type);
     return list;
  }
 
  
    //password Encryption 
    public String md5(String c){
       try{
           MessageDigest dg= MessageDigest.getInstance("MD5");
           dg.update((c).getBytes("UTF8"));
           String str=new String(dg.digest());
           return str;
       }catch(Exception ex){
           return ex.getMessage();
       }
    }
    
     public Date convertToDate(LocalDate dateToConvert) {
            return java.util.Date.from(dateToConvert.atStartOfDay()
            .atZone(ZoneId.systemDefault())
            .toInstant());
    }
     
    
}
