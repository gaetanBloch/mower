package io.gbloch.mower.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * I used the cardinal points North, East, South, West with degrees orientation
 * associated for this enumeration as the base for further calculation
 */
@RequiredArgsConstructor
@Getter
public enum Orientation {
    N(90),
    E(180),
    S(-90),
    W(0);

    private final int degrees;

    /**
     * Use degree calculation to provide the right orientation based on movement
     * @param movementType Type of movement (D or G)
     * @return Cardinal orientation
     */
    public Orientation changeOrientation(MovementType movementType) {
        switch (movementType) {
            case D -> {
                return fromDegrees(this.getDegrees() == 180 ? -90 : this.getDegrees() + 90);
            }
            case G -> {
                return fromDegrees(this.getDegrees() == 0 ? -90 : Math.abs(this.getDegrees() - 90));
            }
            case A -> {
                return this;
            }
        }
        return this;
    }

    /**
     * Provide the right orientation based on a cardinal / circle degrees calculation
     */
    private static Orientation fromDegrees(int degrees) {
        switch (degrees) {
            case 90 -> {
                return N;
            }
            case 180 -> {
                return E;
            }
            case -90 -> {
                return S;
            }
            case 0 -> {
                return W;
            }
            default -> throw new IllegalStateException("Unexpected value: " + degrees);
        }
    }
}
