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
import java.util.Collections;

public class CollectionsPoC {

    public static void main(String[] args) {
        System.out.println("Creating the list...");
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        printElements(list);
        System.out.println("Reversing the elements...");
        Collections.reverse(list);
        printElements(list);

        System.out.println("Swapping the elements around...");
        Collections.swap(list, 0, 3);
        Collections.swap(list, 2, 0);
        printElements(list);

        System.out.println("Alphabetically sorting the elements...");
        Collections.sort(list);
        printElements(list);
    }

    private static void printElements(List<String> list) {
        for (Object o : list) {
            System.out.println(o.toString());
        }
    }
}
