package com.bazylev.arrayapp.observer;

import com.bazylev.arrayapp.entity.AbstractArrayWrapper;

public interface Observer {
    void update(AbstractArrayWrapper arrayWrapper);
}