package com.ryoga.bbs.util;

import java.nio.ByteBuffer;
import java.util.UUID;

public class MySQLTool {
    public static byte[] UUIDToBytes(UUID uuid) {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.putLong(uuid.getMostSignificantBits());
        buffer.putLong(uuid.getLeastSignificantBits());
        return buffer.array();
    }

    public static byte[] stringToBytes(String uuidString) {
        return UUIDToBytes(UUID.fromString(uuidString));
    }

    public static UUID bytesToUUID(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        return new UUID(buffer.getLong(), buffer.getLong());
    }

    public static String bytesToString(byte[] bytes) {
        return bytesToUUID(bytes).toString();
    }
}
