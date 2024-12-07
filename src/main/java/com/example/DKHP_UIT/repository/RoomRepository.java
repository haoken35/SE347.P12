package com.example.DKHP_UIT.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.DKHP_UIT.entities.Room;

public interface RoomRepository extends JpaRepository<Room, String> {
    @Query("select room from Room room where room.roomName = :roomName")
    public Room getRoomFollowingByRoomName(String roomName);

    @Query("select distinct room.building from Room room order by building")
    public List<String> getDistinctBuilding();

    @Query("select room from Room room \n" + // +
            "where building = :building \n" + // +
            "order by building, floor, number ")
    public List<Room> getRoomFollowingByBuilding(String building);
}
