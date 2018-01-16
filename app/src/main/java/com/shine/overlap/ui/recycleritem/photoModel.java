package com.shine.overlap.ui.recycleritem;


import com.github.vivchar.rendererrecyclerviewadapter.ViewModel;

/**
 * Created by youngchanlee on 2018. 1. 3..
 */

public class photoModel implements ViewModel {
    private String idx;

    public photoModel(String idx) {
        this.idx = idx;
    }

    public String getIdx() {
        return idx;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final photoModel that = (photoModel) o;

        return idx.equals(that.idx);
    }

    @Override
    public int hashCode() {
        return idx.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + idx + "}";
    }

}
