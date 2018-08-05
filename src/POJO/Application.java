/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ndahigeze
 */
public class Application implements Serializable {
    private int code;
    private Users client;
    private int status;
    private Date  bookDate;
    private Location location;
    public Users getClient() {
        return client;
    }

    public void setClient(Users client) {
        this.client = client;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
  
 
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

  

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
     
}
