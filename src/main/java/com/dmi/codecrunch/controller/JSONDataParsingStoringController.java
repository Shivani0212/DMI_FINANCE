package com.dmi.codecrunch.controller;

import com.dmi.codecrunch.service.JSONDataParsingStoringServices;
import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;

@RestController
public class JSONDataParsingStoringController {

    @Autowired
    private JSONDataParsingStoringServices service;

    //@ApiOperation(value = "Parse and Store JSON", notes = "This endpoint parses JSON string  data and stores it in the database")
    @PostMapping("/parse-and-store/Str-as-input")
    public String parseAndStoreJSON(@RequestBody String jsonData) {
        return service.parsingAndStoringJsonData(jsonData);
    }

    //@ApiOperation(value = "Parse and Store JSON", notes = "This endpoint parses JSON string  data and stores it in the database")
    @PostMapping("/parse-and-store/JSON-as-input")
    public String parseAndStoreJSON(@RequestBody  JSONObject jsonData) {
        return service.parsingAndStoringJsonData(new JSONObject(jsonData),null);
    }


}
