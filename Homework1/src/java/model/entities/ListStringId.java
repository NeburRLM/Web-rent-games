/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Ruben
 */
@XmlRootElement
public class ListStringId implements Serializable{
    private static final long serialVersionUID = 1L;
    //@JsonbProperty("strings")
    private Collection<Integer> l;
    
    public ListStringId (){ this.l = new ArrayList<>();}
    public ListStringId (Collection<Integer> s){
        this.l=s;
    }
    public Collection<Integer> getList(){
        return l;
    }
    public void setList(Collection<Integer> list) {
        this.l = list;
    }
    
}
