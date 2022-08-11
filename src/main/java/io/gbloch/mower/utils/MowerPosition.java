package io.gbloch.mower.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class MowerPosition {
    private int x;
    private int y;
    private Orientation orientation;
}
