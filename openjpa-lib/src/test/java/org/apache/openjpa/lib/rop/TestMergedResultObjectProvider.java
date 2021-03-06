/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.openjpa.lib.rop;

import java.util.Collections;
import java.util.List;

/**
 * Tests the {@link MergedResultObjectProvider}.
 *
 * @author Abe White
 */
public class TestMergedResultObjectProvider extends ResultListTest {

    @Override
    protected ResultList getResultList(ResultObjectProvider provider) {
        return new WindowResultList(provider, 10);
    }

    @Override
    protected ResultObjectProvider[] getResultObjectProviders(List list) {
        // test 3 merges:
        // 1. first rop empty,
        // 2. neither rop empty
        // 3. both rops empty
        ResultObjectProvider[] merges = new ResultObjectProvider[3];
        merges[0] = new MergedResultObjectProvider(new ResultObjectProvider[]{
            new ListResultObjectProvider(Collections.EMPTY_LIST),
            new ListResultObjectProvider(list), });

        int mid = list.size() / 2;
        List list1 = list.subList(0, mid);
        List list2 = list.subList(mid, list.size());
        merges[1] = new MergedResultObjectProvider(new ResultObjectProvider[]{
            new ListResultObjectProvider(list1),
            new ListResultObjectProvider(list2), });

        merges[2] = new MergedResultObjectProvider(new ResultObjectProvider[]{
            new ListResultObjectProvider(list),
            new ListResultObjectProvider(Collections.EMPTY_LIST), });

        return merges;
    }

}
