package io.gbloch.mower.utils;

public enum MovementType {
    D,G,A;

    public static MovementType fromChar(Character character) {
        return MovementType.valueOf(character.toString());
    }
}
