package kamera.common;

import kamera.common.ui.OpaqueJFrame;
import info.monitorenter.gui.chart.*;
import info.monitorenter.gui.chart.traces.Trace2DSimple;
import kamera.common.model.PointFloat;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Chart2d {
    private static Chart2D chart;
    private static Trace2DSimple marker;
    private static Trace2DSimple trace;
    private static float markerMaxY = 0;
    private static float markerMinY = 0;
    private static float dlugoscOdcinka =0;
    private OpaqueJFrame frame;

    public static void setTrace(String path) {
        trace.removeAllPoints();
        markerMinY = markerMaxY = dlugoscOdcinka = 0;
        File file = new File(path);
        RenderSubtitlesFile renderSubtitlesFile = new RenderSubtitlesFile(file.getPath());
        ArrayList<PointFloat> arrayList = renderSubtitlesFile.getFloatArrayList();

        float aktualnyPoziom = 0;
        float ostatniMetr = 0;

        for (int i = 0; i < arrayList.size(); i++) {
            PointFloat pointFloat = arrayList.get(i);
            float kat = pointFloat.getY();
            float aktualnyMetr = pointFloat.getX();
            float roznica = aktualnyMetr - ostatniMetr;
            float zmianaWzgledem0 = (float) (Math.sin(Math.toRadians(kat)) * roznica);

            aktualnyPoziom += zmianaWzgledem0;

            addPintToTrace(aktualnyMetr, aktualnyPoziom);
            ostatniMetr = aktualnyMetr;
            System.out.println(aktualnyPoziom);
            if(aktualnyMetr > dlugoscOdcinka)
            dlugoscOdcinka = aktualnyMetr;
        }
        chart.invalidate();
    }

    public static void setMarker(int x) {
        marker.removeAllPoints();
        marker.setColor(Color.RED);
        marker.setZIndex(100);
        marker.addPoint(x, markerMaxY);
        marker.addPoint(x, markerMinY);
    }
    public static void clearMarker(){
        marker.removeAllPoints();
    }

    private static void addPintToTrace(float x, float y) {
        if (y > markerMaxY) markerMaxY = y;
        if (y < markerMinY) markerMinY = y;

        trace.addPoint(x, y);
    }

    public void setVisible(boolean visible){
        frame.setVisible(visible);
    }

    public Chart2d() {
        chart = new Chart2D();

        trace = new Trace2DSimple();
        marker = new Trace2DSimple();
        chart.addTrace(trace);
        chart.addTrace(marker);

        trace.setName("");
        marker.setName("");

        frame = new OpaqueJFrame("Wykres");

        frame.getContentPane().add(chart);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);

        frame.setSize(800,200);
        frame.setLocationRelativeTo(null);
    }


    public String saveImage(String dir){
        clearMarker();


        BufferedImage bi = chart.snapShot(600, 500);
        try {
            String path = dir + "-chart.jpg";
            System.out.println(path);
            ImageIO.write(bi, "JPEG", new File(path));
            return path;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isVisible() {
        return frame.isVisible();
    }


    public void setPositionAs(JPanel camera) {
        frame.setSize(camera.getWidth(), camera.getHeight());
        Point position = new Point(camera.getLocationOnScreen());
        frame.setLocation(position);

    }

    public void dispose() {
        frame.dispose();
    }

    public float getLength() {
        return dlugoscOdcinka;
    }
}