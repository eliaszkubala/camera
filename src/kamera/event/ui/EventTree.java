package kamera.event.ui;

import kamera.event.EventWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by TheKing on 03.04.2016.
 */
public class EventTree{

    Map<String, Map<String, List<String>>> myArray;
    private EventWindow _this;

    public EventTree() {
        myArray = new TreeMap<>();
    }

    public void debug() {
        for (Object key : myArray.keySet()) {
            Object value = myArray.get(key);
            System.out.println("#" + key);
            for (Object key2 : myArray.get(key).keySet()) {
                Object value2 = myArray.get(key).get(key2);
                System.out.println("-" + key2);
                for (int i = 0; i < myArray.get(key).get(key2).size(); i++) {
                    System.out.println("--" + myArray.get(key).get(key2).get(i));
                }
            }
        }
    }

    public JPopupMenu getMenu(EventWindow _this) {
        JPopupMenu menu = new JPopupMenu();
        String label1 = null, label2 = null, label3 = null;
        this._this = _this;

        for (Object key : myArray.keySet()) {
            Object value = myArray.get(key);

            JMenu subMenu = new JMenu((String) key);
            label1 = (String) key;
            for (Object key2 : myArray.get(key).keySet()) {
                JMenu subSubMenu = new JMenu((String) key2);
                label2 = (String) key2;
                for (int i = 0; i < myArray.get(key).get(key2).size(); i++) {
                    label3 = myArray.get(key).get(key2).get(i);
                    final JMenuItem item = new JMenuItem(label3);
                    subSubMenu.add(item);
                    final String finalLabel3 = label1;
                    final String finalLabel4 = label2;
                    final String finalLabel5 = label3;
                    item.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            _this.passActionPerformed(finalLabel3, finalLabel4, finalLabel5);
                        }
                    });
                }
                final String finalLabel = label1;
                final String finalLabel1 = label2;
                if (myArray.get(key).get(key2).size() > 0) {
                    subMenu.add(subSubMenu);
                } else {

                    final JMenuItem item = new JMenuItem(label2);
                    subMenu.add(item);
                    item.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            _this.passActionPerformed(finalLabel, finalLabel1);
                        }
                    });
                }
            }
            final String finalLabel2 = label1;
            if (myArray.get(key).isEmpty()) {
                final JMenuItem item = new JMenuItem(label1);
                menu.add(item);
                item.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        _this.passActionPerformed(finalLabel2);
                    }
                });

            } else {
                menu.add(subMenu);
            }
        }
        return menu;
    }

    public void add(String key1) {
        root(key1);
    }

    public void add(String key1, String key2) {
        root(key1);
        branch(key1, key2);
    }

    public void addOld(String key1, String key2, String[] leaf) {
        root(key1);
        branch(key1, key2);
        leaf(key1, key2, leaf);
    }

    public void add(String key1, String key2, String... params) {
        root(key1);
        branch(key1, key2);
        leaf(key1, key2, params);
    }

    public void root(String label) {
        if (!myArray.containsKey(label))
            myArray.put(label, new TreeMap<>());


    }

    public void branch(String label, String value) {
        if (value != null)
            if (!myArray.get(label).containsKey(value))
                myArray.get(label).put(value, new ArrayList<>());

    }

    public void leaf(String label, String label2, String[] value) {
        if (value == null) return;

        if (value.length > 0) {
            myArray.get(label).get(label2).addAll(Arrays.asList(value));
            Collections.sort(myArray.get(label).get(label2), new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    return s1.compareToIgnoreCase(s2);
                }
            });
        }
    }

    static <K, V extends Comparable<? super V>>
    List<Map.Entry<K, V>> entriesSortedByValues(Map<K, V> map) {

        List<Map.Entry<K, V>> sortedEntries = new ArrayList<Map.Entry<K, V>>(map.entrySet());

        Collections.sort(sortedEntries,
                new Comparator<Map.Entry<K, V>>() {
                    @Override
                    public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
                        return e2.getValue().compareTo(e1.getValue());
                    }
                }
        );

        return sortedEntries;
    }

}


