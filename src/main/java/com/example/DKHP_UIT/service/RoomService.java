package com.example.DKHP_UIT.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.DKHP_UIT.entities.Room;
import com.example.DKHP_UIT.exception.ExceptionCode;
import com.example.DKHP_UIT.exception.ExceptionUser;
import com.example.DKHP_UIT.repository.RoomRepository;
import com.example.DKHP_UIT.request.RequestAddRoom;
import com.example.DKHP_UIT.response.ResponseCode;
import com.example.DKHP_UIT.support_service.SupportRoomService;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private SupportRoomService supportRoomService;

    public ResponseEntity addRoom(RequestAddRoom requestAddRoom) {
        // create room name
        String roomNumber = "";
        if (requestAddRoom.getNumber() < 10) {
            roomNumber = roomNumber + "0" + requestAddRoom.getNumber();
        } else {
            roomNumber = roomNumber + requestAddRoom.getNumber();
        }
        String roomName = requestAddRoom.getBuilding() + "." + requestAddRoom.getFloor() + "." + roomNumber;
        // check room
        Room room = this.roomRepository.getRoomFollowingByRoomName(roomName);

        if (room != null) {
            throw new ExceptionUser(ExceptionCode.AddRoomFail);
        }

        // create Room
        room = Room.builder()
                .building(requestAddRoom.getBuilding())
                .floor(requestAddRoom.getFloor())
                .number(requestAddRoom.getNumber())
                .roomName(roomName)
                .build();
        // save room
        this.roomRepository.save(room);
        return ResponseEntity.ok().body(ResponseCode.jsonOfResponseCode(ResponseCode.AddRoom));
    }

    public ResponseEntity addListRoom(List<RequestAddRoom> listRequestAddRoom) {
        for (int i = 0; i < listRequestAddRoom.size(); i++) {
            // create room name
            String roomNumber = "";
            if (listRequestAddRoom.get(i).getNumber() < 10) {
                roomNumber = roomNumber + "0" + listRequestAddRoom.get(i).getNumber();
            } else {
                roomNumber = roomNumber + listRequestAddRoom.get(i).getNumber();
            }
            String roomName = listRequestAddRoom.get(i).getBuilding() + "." + listRequestAddRoom.get(i).getFloor() + "."
                    + roomNumber;
            // check room
            Room room = this.roomRepository.getRoomFollowingByRoomName(roomName);

            if (room != null) {
                throw new ExceptionUser(ExceptionCode.AddRoomFail);
            }

            // create Room
            room = Room.builder()
                    .building(listRequestAddRoom.get(i).getBuilding())
                    .floor(listRequestAddRoom.get(i).getFloor())
                    .number(listRequestAddRoom.get(i).getNumber())
                    .roomName(roomName)
                    .build();
            // save room
            this.roomRepository.save(room);
        }

        return ResponseEntity.ok().body("add successfull");
    }

    public ResponseEntity getListRoom() {
        return ResponseEntity.ok().body(supportRoomService.createResponseListRoom());
    }
}
