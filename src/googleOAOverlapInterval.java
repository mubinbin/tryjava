/*
* You are giving oncall timings for various person i.e startTime, endTime, person doing the on call and the intervals may or may not be overlapping. Return the intervals in such a way all of them are on overlapping and which person is doing on call in that interval.
E.g.


StartTime   EndTime	  OnCall
10           100       P1
50           70        P2
60           120       P3
150          300       P4
Output


StartTime   EndTime	  OnCall
10  		50 			P1
50  		60 			P1, P2
60  		70 			P1, P2, P3
70 			100 		P1, P3
100 		120 		P3
150 		300 		P4
*
*
* */

import java.util.*;

public class googleOAOverlapInterval {
    public static class Intervals{
        public int start, end;
        public HashSet<String> Person = new HashSet<>();
        public Intervals(int s, int e, String p){
            this.start = s;
            this.end = e;
            Person.add(p);
        }

        public Intervals(int s, int e, HashSet<String> p){
            this.start = s;
            this.end = e;
            Person.addAll(p);
        }

        public Intervals(HashSet<String> p){
            Person.addAll(p);
        }
        public Intervals(int prev, int i, Set<String> keySet) {
            this.start = prev;
            this.end = i;
            this.Person.addAll(keySet);
        }
    }

    public static void main(String[] args) {
        List<Intervals> data = new ArrayList<>();
        data.add(new Intervals(10, 100, "P1"));
        data.add(new Intervals(50, 70, "P2"));
        data.add(new Intervals(60, 120, "P3"));
        data.add(new Intervals(150, 300, "P4"));
        data.add(new Intervals(10, 60, "P5"));
        data.add(new Intervals(180, 200, "P6"));
        data.sort((a, b) -> a.start - b.start);

        int min = Integer.MAX_VALUE-1, max = 0 ;
        for(Intervals i : data){
            min = Math.min(min, i.start);
            max = Math.max(max, i.end);
        }
        System.out.println(min + " " + max);

        //now I am thinking of creating a line and iterate over it and make a mark that this person will start from this and end at
        Intervals[] line = new Intervals[max + 1];
        for(Intervals i : data){
            if(line[i.start] == null)
                line[i.start] = new Intervals(i.Person);
            line[i.start].Person.addAll(i.Person);

            if(line[i.end] == null)
                line[i.end] = new Intervals(i.Person);
            line[i.end].Person.addAll(i.Person);
        }

        List<Intervals> ans = new ArrayList<>();
        int prevEnd = -1;
        Set<String> used = new HashSet<>();

        for(int i = 0 ; i < max + 1; i++){
            if(line[i] != null && line[i].Person.size() != 0){
                if(prevEnd != -1 && !used.isEmpty()){
                    ans.add(new Intervals(prevEnd, i, used));
                }
                for (String string : line[i].Person) {
                    if (used.contains(string)){
                        used.remove(string);
                    } else {
                        used.add(string);
                    }
                }
                prevEnd = i;
            }
        }

        System.out.println("StartTime" + "  " + "EndTime" + "  " + "OnCall");
        for (Intervals i : ans) {
            System.out.println(i.start + "  " + i.end + "    " + i.Person);
        }
    }

}
