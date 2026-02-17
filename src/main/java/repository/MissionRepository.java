package repository;

import model.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class MissionRepository {

    public List<Astronaut> loadAstronauts(String filePath) throws IOException {
        List<Astronaut> list = new ArrayList<>();
        List<Map<String, String>> rawData = readJson(filePath);

        for (Map<String, String> map : rawData) {
            list.add(new Astronaut(
                    Integer.parseInt(map.get("id")),
                    map.get("name"),
                    map.get("spacecraft"),
                    AstronautStatus.valueOf(map.get("status")),
                    Integer.parseInt(map.get("experienceLevel"))
            ));
        }
        return list;
    }

    public List<MissionEvent> loadEvents(String filePath) throws IOException {
        List<MissionEvent> list = new ArrayList<>();
        List<Map<String, String>> rawData = readJson(filePath);

        for (Map<String, String> map : rawData) {
            list.add(new MissionEvent(
                    Integer.parseInt(map.get("id")),
                    Integer.parseInt(map.get("astronautId")),
                    Integer.parseInt(map.get("day")),
                    MissionEventType.valueOf(map.get("type")),
                    Integer.parseInt(map.get("basePoints"))
            ));
        }
        return list;
    }

    public List<Supply> loadSupplies(String filePath) throws IOException {
        List<Supply> list = new ArrayList<>();
        List<Map<String, String>> rawData = readJson(filePath);

        for (Map<String, String> map : rawData) {
            list.add(new Supply(
                    Integer.parseInt(map.get("id")),
                    Integer.parseInt(map.get("astronautId")),
                    SupplyType.valueOf(map.get("type")),
                    Integer.parseInt(map.get("value"))
            ));
        }
        return list;
    }

    private List<Map<String, String>> readJson(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        content = content.trim();
        if (content.startsWith("[")) content = content.substring(1);
        if (content.endsWith("]")) content = content.substring(0, content.length() - 1);

        List<Map<String, String>> parsedObjects = new ArrayList<>();
        String[] objects = content.split("\\},\\s*\\{");

        for (String obj : objects) {
            obj = obj.replace("{", "").replace("}", "").replace("\"", "");
            Map<String, String> map = new HashMap<>();
            for (String field : obj.split(",")) {
                String[] kv = field.split(":");
                if (kv.length >= 2) {
                    map.put(kv[0].trim(), kv[1].trim());
                }
            }
            parsedObjects.add(map);
        }
        return parsedObjects;
    }
}