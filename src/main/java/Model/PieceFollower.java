package Model;

import java.util.ArrayList;

public interface PieceFollower { //observer design pattern
    void allChecks(Grid grid, ArrayList<Integer> possibleChecks);
}
