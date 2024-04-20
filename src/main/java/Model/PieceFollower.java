package Model;

import java.util.ArrayList;

public interface PieceFollower { //observer design patter
    void allChecks(Grid grid, ArrayList<Integer> possibleChecks);
}
