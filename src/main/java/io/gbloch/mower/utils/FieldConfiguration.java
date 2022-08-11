package io.gbloch.mower.utils;

import java.util.List;

public record FieldConfiguration(int length, int height, List<MowerInstruction> instructions) {

}
