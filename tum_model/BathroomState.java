package tum_model;

import core.Coord;
import core.Settings;
import core.SimClock;
import movement.Path;
import movement.TumCharacter;

public class BathroomState implements IState {
    private Coord bathroomCoord;
    private double bathroomStayDuration;

    public BathroomState() {
        bathroomCoord = FmiBuilding.getInstance().makeCoord(11.668698191642761, 48.26249450894131);
        bathroomStayDuration = TumModelSettings.getInstance().getDouble(TumModelSettings.TUM_BATHROOM_TIME);
    }

    @Override
    public void enterState(TumCharacter character) {
        //Will need to handle a queue somehow
        TumUtilities.printStateAccessDetails(character,true);
        character.setLastBathroomVisitTime(SimClock.getTime());
    }

    @Override
    public Path getPathForCharacter(TumCharacter character) {
        final Path p = new Path(character.getDefaultSpeed());

        p.addWaypoint(bathroomCoord);
        return p;
    }

    @Override
    public double getPauseTimeForCharacter(TumCharacter character) {
        return bathroomStayDuration;
    }

    @Override
    public void exitState(TumCharacter character) {
        //Will need to handle a queue somehow
        TumUtilities.printStateAccessDetails(character,false);
    }
}
