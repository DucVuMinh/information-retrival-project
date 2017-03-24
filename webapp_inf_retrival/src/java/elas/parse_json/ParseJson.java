/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elas.parse_json;

/**
 * This class is created to contain function for parsing parameter then creating
 * json string that be used to interact with elasticsearch
 *
 * @author ducvu
 */
import org.json.simple.JSONObject;

public class ParseJson {

    /**
     *
     * @param nameIndex
     * @return
     */
    public static String createIndex(int id) {
        JSONObject obj = new JSONObject();
        obj.put("_id",id);
        JSONObject objId = new JSONObject();
        objId.put("index", obj);
        return objId.toString();
    }
    public static void main( String []args){
        System.out.println( createIndex(0) );
    }

}
