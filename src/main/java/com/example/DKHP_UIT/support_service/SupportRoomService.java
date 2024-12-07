package com.example.DKHP_UIT.support_service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.DKHP_UIT.entities.Room;
import com.example.DKHP_UIT.repository.RoomRepository;
import com.example.DKHP_UIT.response.ResponseListRoom;

@Component
public class SupportRoomService {
    @Autowired
    private RoomRepository roomRepository;

    public ResponseListRoom create1ListRoom(List<Room> listRoomWithSameBuilding, String building) {
        // create max floor
        int maxFloor = 1;
        for (int i = 0; i < listRoomWithSameBuilding.size(); i++) {
            if (listRoomWithSameBuilding.get(i).getFloor() > maxFloor) {
                maxFloor = listRoomWithSameBuilding.get(i).getFloor();
            }
        }
        // create list floor contained rooms
        List<List<Integer>> listFloorContainedRooms = new ArrayList<>();
        for (int i = 0; i < maxFloor; i++) {
            listFloorContainedRooms.add(new ArrayList<>());
        }

        for (int i = 0; i < listRoomWithSameBuilding.size(); i++) {
            listFloorContainedRooms.get(listRoomWithSameBuilding.get(i).getFloor() - 1)
                    .add(listRoomWithSameBuilding.get(i).getNumber());
        }

        ResponseListRoom responseListRoom = ResponseListRoom.builder()
                .building(building)
                .listRoomsInFloors(listFloorContainedRooms)
                .build();
        return responseListRoom;
    }

    public List<ResponseListRoom> createResponseListRoom() {
        List<ResponseListRoom> response = new ArrayList<>();

        // get distinct building
        List<String> listDistinctBuilding = this.roomRepository.getDistinctBuilding();

        // in each building create data for floor and room.
        for (int i = 0; i < listDistinctBuilding.size(); i++) {
            // get room following by building
            List<Room> listRooms = this.roomRepository.getRoomFollowingByBuilding(listDistinctBuilding.get(i));
            // create 1 List Room
            ResponseListRoom response1ListRoom = create1ListRoom(listRooms, listDistinctBuilding.get(i));

            // add to response
            response.add(response1ListRoom);
        }
        return response;
    }
}
