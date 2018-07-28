package com.guoqiang.commonlib.datacontract;

import java.util.List;

/**
 * @Auther: zgq
 * @Date: 2018/7/16 20:56
 * @Description:
 */
public class PageResultMessage<T> extends MessageBase<T> {
    private int sum;//总数
    private int pageSize;//每页总数
    private int pageIndex;//页索引
    private List<T> list;

    public PageResultMessage(int sum, int pageSize, int pageIndex, List<T> list) {
        this.sum = sum;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.list = list;
    }
}
