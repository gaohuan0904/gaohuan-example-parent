package com.gaohuan.pattern.sfp.principle;

/**
 * Created by acer on 2016/7/6.
 */
public class ChartFactory {
    public static Chart getChart() {
        String type = XmlUtil.readChartType();
        Chart chart = null;
        if (type.equalsIgnoreCase("histogram")) {
            chart = new HistogramChart();
        } else if (type.equalsIgnoreCase("pie")) {
            chart = new PieChart();
        } else if (type.equalsIgnoreCase("line")) {
            chart = new LineChart();
        }
        return chart;
    }
}
