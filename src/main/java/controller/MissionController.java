package controller;

import model.*;
import service.MissionControlService;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MissionController {
    private MissionControlService service;

    public MissionController(MissionControlService service) {
        this.service = service;
    }

    public void run() {
        try {
            // --- TASK 1: Load and Print Stats ---
            service.loadData("C:\\Users\\hsvdg\\OneDrive\\Documents\\VSCode_Projects\\marire_Jeworrek_Oliver\\src\\main\\resources\\astronauts.json",
                    "C:\\Users\\hsvdg\\OneDrive\\Documents\\VSCode_Projects\\marire_Jeworrek_Oliver\\src\\main\\resources\\events.json",
                    "C:\\Users\\hsvdg\\OneDrive\\Documents\\VSCode_Projects\\marire_Jeworrek_Oliver\\src\\main\\resources\\supplies.json");

            System.out.println("Astronauts loaded: " + service.getAllAstronauts().size());
            System.out.println("Events loaded: " + service.getEventsCount());
            System.out.println("Supplies loaded: " + service.getSuppliesCount());

            for (Astronaut a : service.getAllAstronauts()) {
                System.out.println(a);
            }

            // --- TASK 2: Filter ---
            handleSpacecraftFilter();

            // --- TASK 3: Sort ---
            System.out.println("\nSorted Astronauts (Exp Desc, Name Asc):");
            List<Astronaut> sorted = service.getSortedAstronauts();
            sorted.forEach(System.out::println);



        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void handleSpacecraftFilter() {
        System.out.print("Input Spacecraft: ");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            String input = scanner.next();
            List<Astronaut> filtered = service.filterAstronauts(input, AstronautStatus.ACTIVE);
            filtered.forEach(System.out::println);
        }
    }
}