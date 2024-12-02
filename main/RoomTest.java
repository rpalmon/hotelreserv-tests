package main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    @Test
    public void testRoomConstructorAndGetters() {
        Room room = new Room(1, "101", Room.RoomType.SINGLE, 150.0, true);

        assertEquals(1, room.getRoomID(), "Room ID should match.");
        assertEquals("101", room.getRoomNum(), "Room number should match.");
        assertEquals(Room.RoomType.SINGLE, room.getRoomType(), "Room type should match.");
        assertEquals(150.0, room.getPrice(), 0.01, "Room price should match.");
        assertTrue(room.getAvail(), "Room availability should match.");
    }

    @Test
    public void testRoomSetters() {
        Room room = new Room(1, "101", Room.RoomType.SINGLE, 150.0, true);

        room.setRoomID(2);
        room.setRoomNum("102");
        room.setRoomType(Room.RoomType.DOUBLE);
        room.setPrice(200.0);
        room.setAvail(false);

        assertEquals(2, room.getRoomID(), "Room ID should match after update.");
        assertEquals("102", room.getRoomNum(), "Room number should match after update.");
        assertEquals(Room.RoomType.DOUBLE, room.getRoomType(), "Room type should match after update.");
        assertEquals(200.0, room.getPrice(), 0.01, "Room price should match after update.");
        assertFalse(room.getAvail(), "Room availability should match after update.");
    }

    @Test
    public void testIsAvail() {
        Room room = new Room(1, "101", Room.RoomType.SINGLE, 150.0, true);

        assertTrue(room.isAvail(), "Room should be available.");
        room.setAvail(false);
        assertFalse(room.isAvail(), "Room should not be available after update.");
    }

    @Test
    public void testToString() {
        Room room = new Room(1, "101", Room.RoomType.SINGLE, 150.0, true);
        String expectedString = "Room{" +
                "roomId=1" +
                ", roomNumber='101'" +
                ", roomType='SINGLE'" +
                ", price=150.0" +
                ", availability=true" +
                '}';
        assertEquals(expectedString, room.toString(), "toString output should match.");
    }
}
