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
import javafx.collections.ObservableMap;
import javafx.collections.MapChangeListener;
import javafx.collections.FXCollections;

public class ObservableMapPoC {

    public static void main(String[] args) {
        // Use Java Collections to create the List.
        Map<String, String> map = new HashMap<>();
        // Now add observability by wrapping it with ObservableList.
        ObservableMap<String, String> observableMap = FXCollections.observableMap(map);
        observableMap.addListener(new MapChangeListener() {
            @Override
            public void onChanged(MapChangeListener.Change change) {
                System.out.println("Detected a change! ");
            }
        });
        // Changes to the observableMap WILL be reported.
        observableMap.put("key 1", "value 1");
        System.out.println("Size: " + observableMap.size());
        // Changes to the underlying map will NOT be reported.
        map.put("key 2", "value 2");
        System.out.println("Size: " + observableMap.size());
    }
}