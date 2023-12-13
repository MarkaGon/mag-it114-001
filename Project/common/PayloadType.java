package Project.common;
import Project.common.Constants;
import Project.common.Payload;
import Project.common.PayloadType;
import Project.common.RoomResultPayload;

public enum PayloadType {
    CONNECT, DISCONNECT, MESSAGE, CLIENT_ID, RESET_USER_LIST,
    SYNC_CLIENT, CREATE_ROOM, JOIN_ROOM, GET_ROOMS,
    READY, PHASE, CHARACTER, TURN, MOVE, CELL, GRID, GRID_RESET,
}