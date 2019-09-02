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

import java.util.List;
import java.util.ArrayList;

public class ListPoC {

    public static void main(String[] args) {

        // Create a List.
        System.out.println("Creating the List...");
        List<String> list = new ArrayList<>();
        list.add("String one");
        list.add("String two");
        list.add("String three");

        // Print out contents.
        printElements(list);

        // Set a new element at index 0.
        System.out.println("Setting an element...");
        list.set(0, "A new String");
        printElements(list);

        // Search for the newly added String.
        System.out.println("Searching for content...");
        System.out.print("Contains \"A new String\"? ");
        System.out.println(list.contains("A new String"));
        System.out.println("");

        // Create a sublist.
        System.out.println("Creating a sublist...");
        list = list.subList(1, 3);
        printElements(list);

        // Clear all elements.
        System.out.println("Clearing all elements...");
        list.clear();
        printElements(list);
    }

    private static void printElements(List<String> list) {
        System.out.println("Size: " + list.size());
        for (Object o : list) {
            System.out.println(o.toString());
        }
        System.out.println("");
    }
}
