package tum_model;

import movement.Path;
import movement.TumCharacter;

/**
 * Created by rober on 11-Nov-15.
 */
public class LeaveSimulationAreaState implements IState {

    @Override
    public void enterState(TumCharacter character) {

    }

    @Override
    public Path getPathForCharacter(TumCharacter character) {

        Path path = new Path();
        path.addWaypoint(character.getLastLocation());
        path.addWaypoint(character.getInitialLocation());

        return path;
    }

    @Override
    public double getPauseTimeForCharacter(TumCharacter character) {
        return 0;
    }

    @Override
    public void exitState(TumCharacter character) {

    }
}
