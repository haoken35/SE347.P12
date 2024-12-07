package com.example.DKHP_UIT.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DKHP_UIT.request.RequestAddRoom;
import com.example.DKHP_UIT.service.RoomService;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping("/addRoom")
    public ResponseEntity addRoom(@RequestBody RequestAddRoom requestAddRoom) {
        return this.roomService.addRoom(requestAddRoom);
    }

    @PostMapping("/addListRoom")
    public ResponseEntity addListRoom(@RequestBody List<RequestAddRoom> listRequestAddRoom) {
        return this.roomService.addListRoom(listRequestAddRoom);
    }

    @GetMapping("/getListRoom")
    public ResponseEntity getListRoom() {
        return this.roomService.getListRoom();
    }

    public static void main(String[] args) {
        String fileName = "C:\\Users\\user\\Downloads\\TaiLieuHocTap\\Mon_hoc_UIT\\hocki5\\OOP\\DKHP_UIT\\DKHP_UIT\\src\\main\\java\\com\\example\\DKHP_UIT\\controller\\text.txt";

        try (FileWriter file = new FileWriter(fileName)) {
            file.write("[\n");

            String[] buildings = { "A", "B", "C" };

            // Outer loop for buildings
            for (int i = 0; i < buildings.length; i++) {
                String building = buildings[i];

                // Loop for floors
                for (int floor = 1; floor <= 5; floor++) {

                    // Loop for room numbers
                    for (int roomNumber = 1; roomNumber <= 9; roomNumber++) {

                        // Write the JSON object for each room
                        file.write("  {\n");
                        file.write("    \"building\": \"" + building + "\",\n");
                        file.write("    \"floor\": " + floor + ",\n");
                        file.write("    \"number\": " + roomNumber + "\n");
                        file.write("  }" + (i == buildings.length - 1 && floor == 5 && roomNumber == 9 ? "\n" : ",\n"));
                    }
                }
            }

            file.write("]\n");

            System.out.println("JSON data written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
