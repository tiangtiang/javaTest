package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lianglab on 2018/3/1.
 */
public class MergeIntervals {

    /**
     * Given a collection of intervals, merge all overlapping intervals.
     For example,
     Given [1,3],[2,6],[8,10],[15,18],
     return [1,6],[8,10],[15,18].
     按照开始节点的大小排序，然后进行合并
     * @param intervals
     * @return
     */
    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, (o1, o2) -> o1.start-o2.start);
        List<Interval> result = new ArrayList<>();
        for(int i=0;i<intervals.size();i++){
            if(result.size() == 0)
                result.add(intervals.get(i));
            else{
                Interval temp = result.get(result.size()-1);
                Interval now = intervals.get(i);
                if(temp.end<now.start)
                    result.add(now);
                else{
                    if(temp.end<now.end)
                        temp.end = now.end;
                }
            }
        }
        return result;
    }

    /**
     * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
     You may assume that the intervals were initially sorted according to their start times.
     Example 1:
     Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
     Example 2:
     Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
     This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
     思路就是先插入然后在合并
     * @param intervals
     * @param newInterval
     * @return
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if(intervals.size() == 0){
            intervals.add(newInterval);
            return intervals;
        }
        int i=0;
        for(;i<intervals.size();i++){
            if(intervals.get(i).start>=newInterval.start)
                break;
        }
        if(i == intervals.size())
            intervals.add(newInterval);
        else
            intervals.add(i, newInterval);
        return merge(intervals);
    }
}

class Interval{
    int start;
    int end;

    public Interval() {
        start = 0;
        end = 0;
    }

    public Interval(int s, int e) {
        start = s;
        end = e;
    }

    @Override
    public String toString() {
        return "["+start+","+end+"]";
    }
}