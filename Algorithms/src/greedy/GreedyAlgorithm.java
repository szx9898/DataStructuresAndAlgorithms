package greedy;

import java.util.*;
import java.util.stream.Stream;

public class GreedyAlgorithm {
    public static void main(String[] args) {
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        //将各个电台放入到broadcasts中
        HashSet<String> hashSet1 = new HashSet<>();
        HashSet<String> hashSet2 = new HashSet<>();
        HashSet<String> hashSet3 = new HashSet<>();
        HashSet<String> hashSet4 = new HashSet<>();
        HashSet<String> hashSet5 = new HashSet<>();
        Collections.addAll(hashSet1,"北京","上海","天津");
        Collections.addAll(hashSet2,"广州","北京","深圳");
        Collections.addAll(hashSet3,"成都","上海","杭州");
        Collections.addAll(hashSet4,"上海","天津");
        Collections.addAll(hashSet5,"杭州","大连");
        broadcasts.put("k1",hashSet1);
        broadcasts.put("k2",hashSet2);
        broadcasts.put("k3",hashSet3);
        broadcasts.put("k4",hashSet4);
        broadcasts.put("k5",hashSet5);
        //allAreas所有城市
        HashSet<String> allAreas = new HashSet<>();
        Collections.addAll(allAreas,"北京","上海","天津","广州","深圳","成都","杭州","大连");
        //创建list，存放选择的电台集合
        List<String> selects = new ArrayList<>();
        //定义一个临时的集合，在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();
        //定义一个 maxKey，保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的电合的key
        String maxKey = null;
        while (allAreas.size()!=0){//未覆盖完
            //每进行一次while，需要
            maxKey = null;
            for (String key : broadcasts.keySet()) {
                //每进行一次循环，set置为空
                tempSet.clear();
                //当前key能覆盖的地区
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //求出 tempSet和allAreas集合的交集，交集会赋给 tempSet
                tempSet.retainAll(allAreas);
                //如果当前这个集合包含的未覆盖地区的数量，比maxKey指向的集合地区还多，就需要重置maxKey
                if (tempSet.size()>0 && (maxKey==null || tempSet.size()>broadcasts.get(maxKey).size())){
                    maxKey = key;
                }
            }
            if(maxKey!=null){
                selects.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));
            }

        }
        System.out.println("得到的选择结果是"+selects);
    }
}
