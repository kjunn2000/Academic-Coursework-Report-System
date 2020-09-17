package com.moodyjun.Model.Util;

import javax.swing.*;
import java.util.Objects;

public class ID {

    private String rolePart ;
    private int numPart ;

    public ID(String rolePart , int numPart){
        this.rolePart = rolePart ;
        this.numPart = numPart ;
    }

    public static ID fromString(String idString){
        if(idString.length()>8) return null;
        try {
            return new ID(idString.substring(0,2),Integer.parseInt(idString.substring(2,8)));
        }catch(Exception e){
            return null;
        }
    }

    public static ID nextID(ID id){
        return new ID(id.getRolePart(),id.numPart+1);
    }

    public static boolean isValid(String idString){
        String[] roleParts = {"AD","LC","TP"};
        if(idString == null || idString.length()>8) {
            return false;
        }else {
            for(String rolePart : roleParts){
                if(idString.startsWith(rolePart)) return true;
            }
        }
        return false;
    }

    public String getRolePart() {
        return rolePart;
    }

    public int getNumPart() {
        return numPart;
    }

    @Override
    public String toString() {
        return rolePart+String.format("%06d",numPart);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ID id = (ID) o;
        return numPart == id.numPart &&
                rolePart.equals(id.rolePart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rolePart, numPart);
    }
}
