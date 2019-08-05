package iona.pojo;

/**
 * 分页pojo类
 */
public class Pager {
    //第几页
    private int index;
    //分页容量
    private int size;
    private int start;

    public Pager(int index, int size) {
        this.index = index;
        this.size = size;
        this.start = (index-1)*size;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
