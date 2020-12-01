package com.example.logparser.service;

import com.example.logparser.model.ApplicationServerLogRecord;
import com.example.logparser.model.LogRecord;
import com.example.logparser.model.Record;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class responsible for reading and parsing the JSON objects from the file to the Record objects.
 */
@Component
public class LogParser {
    private Path path;

    private Logger logger = Logger.getLogger(LogParser.class.getName());

    private Map<String, Map<String, String>> logs = new HashMap<>();

    public LogParser() {
    }

    public List<Record> parseFile(String textPath){
        try {
            this.path = Paths.get(textPath);
        } catch (Exception e){
            logger.log(Level.SEVERE, "Invalid path to the file: \n" + e);
        }
        logger.info("Reading JSON objects from " + path.getFileName());
        try (BufferedReader br = Files.newBufferedReader(this.path)) {
            String line;
            logger.info("JSON objects details extracted and saved");
            while ((line = br.readLine()) != null) {
                JSONObject object = new JSONObject(line);
                extractDetails(object);
            }
        } catch (IOException e) {

            logger.warning( "Error while reading the file \n " + e);
        }

        return generateRecordObjects();
    }

    private void extractDetails(JSONObject object){
        String id = object.getString("id");
        Map <String, String> details;

        // The details about the record is stored in the new map, or the existing one is updated
        if (!logs.keySet().contains(id)){
            details = new HashMap<>();
            logs.put(id, details);
        } else {
            details = logs.get(id);
        }
        // JSON fields are extracted and saved to a map
        if(object.getString("state").equals("STARTED")){
            details.put("startTime", String.valueOf(object.getInt("timestamp")));
        }
        else details.put("finishTime", String.valueOf(object.getInt("timestamp")));

        if(object.has("host") && object.has("type")){
            details.put("type", object.getString("type"));
            details.put("host", object.getString("host"));
        }
    }
    private List <Record> generateRecordObjects(){
        List<Record> records = new ArrayList<>();
        Record record;

        logger.info("LogRecord and ApplicationServerLogRecord objects are created based on JSON objects' saved details");

        for(String id : logs.keySet()){
            int duration = Integer.parseInt(logs.get(id).get("finishTime")) - Integer.parseInt(logs.get(id).get("startTime"));
            boolean isLong = Math.abs(duration) > 4;

            if(logs.get(id).containsKey("host") && logs.get(id).containsKey("type")){
                record = new ApplicationServerLogRecord(id, duration, isLong, logs.get(id).get("host"), logs.get(id).get("type"));
            } else {
                record = new LogRecord(id, duration, isLong);
            }

            records.add(record);
        }

        return records;
    }
}
