/*
 * The MIT License
 *
 * Copyright 2019 cazucito.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package concurrency;

import java.util.Map;
import java.util.HashMap;

public class MapPoC {

    public static void main(String[] args) {

        // Create a Map.
        Map<String, String> map = new HashMap<>();
        map.put("apple", "fruit");
        map.put("carrot", "vegetable");
        System.out.println("Size: " + map.size());
        System.out.println("Empty? " + map.isEmpty());

        // Pass in keys; print out values.
        System.out.println("Passing in keys and printing out values...");
        System.out.println("Key is apple, value is: " + map.get("apple"));
        System.out.println("Key is carrot, value is: " + map.get("carrot"));
        System.out.println("");

        // Check keys and values.
        System.out.println("Inspecting keys and values:");
        System.out.println("Contains key \"apple\"? "
                + map.containsKey("apple"));
        System.out.println("Contains key \"carrot\"? "
                + map.containsKey("carrot"));
        System.out.println("Contains key \"fruit\"? "
                + map.containsKey("fruit"));
        System.out.println("Contains key \"vegetable\"? "
                + map.containsKey("vegetable"));
        System.out.println("Contains value \"apple\"? "
                + map.containsValue("apple"));
        System.out.println("Contains value \"carrot\"? "
                + map.containsValue("carrot"));
        System.out.println("Contains value \"fruit\"? "
                + map.containsValue("fruit"));
        System.out.println("Contains value \"vegetable\"? "
                + map.containsValue("vegetable"));
        System.out.println("");

        // Remove objects from the map.
        System.out.println("Removing apple from the map...");
        map.remove("apple");
        System.out.println("Size: " + map.size());
        System.out.println("Contains key \"apple\"? "
                + map.containsKey("apple"));
        System.out.println("Invoking map.clear()...");
        map.clear();
        System.out.println("Size: " + map.size());
    }
}
