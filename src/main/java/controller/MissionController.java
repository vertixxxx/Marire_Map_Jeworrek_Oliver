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
            // TASK 1
            service.loadData("C:\\Users\\hsvdg\\OneDrive\\Documents\\VSCode_Projects\\marire_Jeworrek_Oliver\\src\\main\\resources\\astronauts.json",
                    "C:\\Users\\hsvdg\\OneDrive\\Documents\\VSCode_Projects\\marire_Jeworrek_Oliver\\src\\main\\resources\\events.json",
                    "C:\\Users\\hsvdg\\OneDrive\\Documents\\VSCode_Projects\\marire_Jeworrek_Oliver\\src\\main\\resources\\supplies.json");

            System.out.println("Astronauts loaded: " + service.getAllAstronauts().size());
            System.out.println("Events loaded: " + service.getEventsCount());
            System.out.println("Supplies loaded: " + service.getSuppliesCount());

            for (Astronaut a : service.getAllAstronauts()) {
                System.out.println(a);
            }

            // TASK 2
            handleSpacecraftFilter();

            // TASK 3
            System.out.println("\nSorted Astronauts (Exp Desc, Name Asc):");
            List<Astronaut> sorted = service.getSortedAstronauts();
            sorted.forEach(System.out::println);

            // TASK 4
            service.exportReverseSortedAstronauts("astronauts_sorted.txt");

            // TASK 5
            System.out.println();
            for (MissionEvent e : service.getFirstNEvents(5)) {
                System.out.printf("Event %d -> raw=%d -> computed=%d%n",
                        e.getId(), e.getBasePoints(), service.calculateComputedPoints(e));
            }

            // TASK 6
            System.out.println("\nTop 5 Astronauts:");
            List<Astronaut> top5 = service.getTopAstronauts(5);
            for (int i = 0; i < top5.size(); i++) {
                Astronaut a = top5.get(i);
                System.out.printf("%d. %s (%s) -> %d%n",
                        i + 1, a.getName(), a.getSpacecraft(), service.calculateTotalScore(a.getId()));
            }
            if (!top5.isEmpty()) {
                System.out.println("Leading spacecraft: " + top5.get(0).getSpacecraft());
            }

            // TASK 7
            service.generateMissionReport("mission_report.txt");
            System.out.println("Task 7: mission_report.txt created.");

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