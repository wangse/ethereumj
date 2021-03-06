/*
 * Copyright (c) [2016] [ <ether.camp> ]
 * This file is part of the ethereumJ library.
 *
 * The ethereumJ library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The ethereumJ library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the ethereumJ library. If not, see <http://www.gnu.org/licenses/>.
 */
package org.ethereum.jsontestsuite.suite;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import java.io.IOException;
import java.util.*;

/**
 * @author Mikhail Kalinin
 * @since 28.09.2017
 */
public class TrieTestSuite {

    List<TrieTestCase> testCases = new ArrayList<>();

    public TrieTestSuite(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JavaType type = mapper.getTypeFactory().
                constructMapType(HashMap.class, String.class, TrieTestCase.class);

        Map<String, TrieTestCase> caseMap = new ObjectMapper().readValue(json, type);

        for (Map.Entry<String, TrieTestCase> e : caseMap.entrySet()) {
            e.getValue().setName(e.getKey());
            testCases.add(e.getValue());
        }

        Collections.sort(testCases, new Comparator<TrieTestCase>() {
            @Override
            public int compare(TrieTestCase t1, TrieTestCase t2) {
                return t1.getName().compareTo(t2.getName());
            }
        });
    }

    public List<TrieTestCase> getTestCases() {
        return testCases;
    }

    @Override
    public String toString() {
        return "TrieTestSuite{" +
                "testCases=" + testCases +
                '}';
    }
}
