package broadridge;


import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class Pips
{

    private static Map<Integer, Integer> transitions = computeTransitions();

    private static Map<Integer, Integer> computeTransitions() {
        Map<Integer, Integer > transitions = new HashMap<>();

        transitions.put(1, 6);
        transitions.put(6, 1);

        transitions.put(3, 4);
        transitions.put(4, 3);

        transitions.put(2, 5);
        transitions.put(5, 2);

        return transitions;
    }

    private static void print(Object output) {
        System.out.println(output);
    }

    private static Map<Integer, Set<Integer>> computePlurality(int[] dies) {
        Map<Integer, Integer > counts = new HashMap<>( );
        for ( int dy : dies )
        {
            Integer count = counts.getOrDefault( dy, 0 );
            counts.put( dy, ++count );
        }

        Map<Integer, Set<Integer>> weights = new TreeMap<>( Comparator.reverseOrder( ) );

        for (Map.Entry<Integer, Integer> entry: counts.entrySet()) {
            Integer key =  entry.getKey();
            Integer count = entry.getValue();

            Set<Integer> sameWeight = weights.computeIfAbsent(count, k -> new HashSet<>());

            sameWeight.add( key );
        }

        return weights;
    }

    /*
    would want to compute a lookup table since there are maximum six numbers that can appear here
    can derive the cost
    */
    private static int getFixed(Set<Integer> numbers) {
        return numbers.size();
    }

    private static int computeCost(int start, int finish) {
        if (start == finish) {
            return 0;
        }
        return transitions.get( start ) == finish ? 2 : 1;
    }

    private static int computeCostForSet(int die, int count, Set<Integer> numbers) {
        int thisCost = 0;
        if (die != 0) {
            for(Integer number: numbers) {
                int cost = computeCost( die, number );
                thisCost += ( cost * count );
            }
        }

        return thisCost;
    }

    private static int computeTurns(int[] dies) {
        Map<Integer, Set<Integer>> weights = computePlurality( dies );

        int die = 0;

        int totalTurns = 0;

        for (Map.Entry<Integer, Set<Integer>> entry: weights.entrySet()) {
            Integer count = entry.getKey();
            Set<Integer> numbers = entry.getValue();

            if (count > 1 && die == 0) {
                Set<Integer> copy = new HashSet<>( numbers );

                die = getFixed( numbers );

                copy.remove( die );
                totalTurns += computeCostForSet( die, count, copy );

                continue;
            }

            totalTurns += computeCostForSet( die, count, numbers );
        }


        return totalTurns;
    }

    // I wrote this shit in 10 minutes
    private static int computerTurnsSimple(int[] dies) {
        Map<Integer, Integer > turnsForNumber = new HashMap<>();
        for (int turn = 1; turn <= 6; turn ++) {
            turnsForNumber.put(turn, 0);
        }

        for ( int dy : dies )
        {
            for (int turn = 1; turn <= 6; turn ++) {
                int cost = computeCost( dy, turn );
                turnsForNumber.put(turn, turnsForNumber.get(turn) + cost);
            }
        }

        int min = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry: turnsForNumber.entrySet()) {
            if (entry.getValue() < min) {
                min = entry.getValue();
            }
        }

        return min;
    }

    public int solution(int[] a) {
        return Pips.computerTurnsSimple( a );
    }

    public static void main(String[] args) {
        Pips p = new Pips();
        print(p.solution( new int[] {1, 2, 3}));
        print(p.solution( new int[] {1, 1, 6}));
        print(p.solution( new int[] {1, 6, 2, 3}));

    }
}
