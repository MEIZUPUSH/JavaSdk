package com.meizu.push.sdk.server.model.sub;

import java.io.Serializable;
import java.util.List;

/**
 * 标签订阅
 */
public class TagInfo implements Serializable{

    private String pushId;
    private List<Tag> tags;

    public String getPushId() {
        return pushId;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "TagInfo{" +
                "pushId='" + pushId + '\'' +
                ", tags=" + tags +
                '}';
    }
}
