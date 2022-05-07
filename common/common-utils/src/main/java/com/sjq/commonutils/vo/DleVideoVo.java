package com.sjq.commonutils.vo;

/**
 * @Author Kemp
 * @create 2022/4/9 17:32
 */
public class DleVideoVo {
    String fgroup;
    String fpath;

    public DleVideoVo(String fgroup, String fpath) {
        this.fgroup = fgroup;
        this.fpath = fpath;
    }

    public String getFgroup() {
        return fgroup;
    }

    public void setFgroup(String fgroup) {
        this.fgroup = fgroup;
    }

    public String getFpath() {
        return fpath;
    }

    public void setFpath(String fpath) {
        this.fpath = fpath;
    }
}
