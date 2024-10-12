package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse {
    private String nowDt;
    private WeatherFact fact;
    private long now;
    private WeatherInfo info;
    private List<Forecast> forecasts;

}

@Getter
@Setter
class WeatherFact {
    private boolean polar;
    private int temp;
    private String icon;
    private int pressureMm;
    private double cloudness;
    private String source;
    private String windDir;
    private int feelsLike;
    private double windGust;
    private long uptime;
    private int windAngle;
    private String condition;
    private boolean isThunder;
    private int uvIndex;
    private int precType;
    private String season;
    private int humidity;
    private int pressurePa;
    private double precStrength;
    private double windSpeed;
    private String daytime;
    private long obsTime;
    private int precProb;


}

@Getter
@Setter
class WeatherInfo {
    private boolean nr;
    private boolean ns;
    private boolean f;
    private int defPressureMm;
    private boolean h;
    private double lon;
    private int zoom;
    private boolean nsr;
    private boolean n;
    private String url;
    private TimeZoneInfo tzinfo;
    private boolean p;
    private int defPressurePa;
    private double lat;


}

@Getter
@Setter
class TimeZoneInfo {
    private boolean dst;
    private int offset;
    private String name;
    private String abbr;
}

@Getter
@Setter
class Forecast {
    private String date;
    private String riseBegin;
    private String sunrise;
    private List<HourlyForecast> hours;
    private Biomet biomet;
    private int week;
    private String moonText;
    private long dateTs;
    private String sunset;
    private Parts parts;
}

@Getter
@Setter
class HourlyForecast {
    private long hourTs;
    private int temp;
    private String icon;
    private int pressureMm;
    private double cloudness;
    private int precPeriod;
    private String windDir;
    private int feelsLike;
    private double windGust;
    private int windAngle;
    private String condition;
    private String hour;
    private int uvIndex;
    private int precType;
    private int humidity;
    private int pressurePa;
    private double precStrength;
    private double windSpeed;
    private double precMm;
    private int precProb;

}

@Getter
@Setter
class Biomet {
    private String condition;
    private int index;

}

@Getter
@Setter
class Parts {
    private PartDetails night;
    private PartDetails morning;
    private PartDetails day;
    private PartDetails evening;

}

@Getter
@Setter
class PartDetails {
    private String icon;
    private int tempMax;
    private double windGust;
    private int windAngle;
    private double freshSnowMm;
    private int tempMin;
    private int tempAvg;
    private int uvIndex;
    private int precType;
    private int humidity;
    private int pressurePa;
    private double windSpeed;
    private double precMm;
    private boolean polar;
    private Biomet biomet;
    private int pressureMm;
    private double cloudness;
    private int precPeriod;
    private String windDir;
    private int feelsLike;

}
