package model;

public record Action(
        Creature actor,
        Cell originalPosition,
        Cell newPosition,
        boolean died,
        Creature child,
        Cell childPosition) {


    public boolean shouldDie() {
        return died;
    }
}