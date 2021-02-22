package net.miracle.community.model;

import java.util.HashMap;
import java.util.Map;

public class ValueDictionary {

    private Map<String, String> map;
    private Integer popularityIndex;

    public ValueDictionary() {
        setMap(new HashMap<>());
        setPopularityIndex(0);
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public Integer getPopularityIndex() {
        return popularityIndex;
    }

    public void setPopularityIndex(Integer popularityIndex) {
        this.popularityIndex = popularityIndex;
    }

    @Override
    public String toString() {
        return "ValueDictionary{" +
                "map=" + map +
                '}';
    }
}
