/*
 * Copyright 2014 Dorian Cussen
 * Modifications Copyright(C) 2015 Fred Grott(GrottWorkShop)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.github.shareme.gwsviewmodelutils.library;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Map impl that will limit the size of the map and work on a Least Recently Used basis (LRU)
 * Created by fgrott on 11/1/2015.
 */
@SuppressWarnings("unused")
public class MaxSizeHashMap<K, V> extends LinkedHashMap<K, V> {
    private final int mMaxSize;

    public MaxSizeHashMap(int maxSize) {
        super(
                maxSize,
                maxSize, //load factor is ignored in the Map impl anyhow
                true); //access should rearrange the underlying linkedHashMap so last used gets kicked out first i.e. LRU

        mMaxSize = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > mMaxSize;
    }
}