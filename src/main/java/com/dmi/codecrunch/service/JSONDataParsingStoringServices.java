package com.dmi.codecrunch.service;

import com.dmi.codecrunch.model.JSONDataModel;
import com.dmi.codecrunch.repository.JSONDataInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JSONDataParsingStoringServices {

    @Autowired
    private JSONDataInterface dataInterface;
    public String parsingAndStoringJsonData(String jsonData){
        String response=null;
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            response=parsingAndStoringJsonData(jsonObject,null);
        }
        catch (JSONException  js){
            response="There is an error in the json trying to get processed it is invalid!!!";
        }
        return response;
    }
    public String parsingAndStoringJsonData(JSONObject jsonObject,String Key)throws JSONException{
        String response;
        try {
            for (String key : jsonObject.keySet()) {
                Object value = jsonObject.get(key);
                if (value instanceof JSONObject) {
                    parsingAndStoringJsonData((JSONObject) value, key); // Recursively handle nested object
                } else if (value instanceof JSONArray jsonArray) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Object arrayElement = jsonArray.get(i);
                        if (arrayElement instanceof JSONObject) {
                            parsingAndStoringJsonData((JSONObject) arrayElement, key); // Recursively handle nested object in array
                        } else {
                            storeData(Key + "_" + key, String.valueOf(arrayElement)); // Store array element
                        }
                    }
                } else {
                    storeData(key, String.valueOf(value)); // Store primitive value
                }
            }
            response = "Parsing and storing is successfull!!!";
        }
        catch(Exception ex){
            response=ex.getMessage();
        }
        return response;
    }

    private String getDataType(String metaDataDataType) {
        // Add logic to map meta data types to SQL data types
        return "VARCHAR(255)"; // Placeholder, replace with actual logic
    }

    public String generateDDLScript(Map<String, String> metaData) {
        StringBuilder ddlScript = new StringBuilder();
        ddlScript.append("CREATE TABLE parsed_data (\n");
        for (Map.Entry<String, String> entry : metaData.entrySet()) {
            ddlScript.append(entry.getKey()).append(" ").append(getDataType(entry.getValue())).append(",\n");
        }
        ddlScript.append(");");
        return ddlScript.toString();
    }



    private void storeData(String key, String value){
        JSONDataModel jsonDataModel=new JSONDataModel();
        jsonDataModel.setKey(key);
        jsonDataModel.setValues(value);
        dataInterface.save(jsonDataModel);
    }
}
