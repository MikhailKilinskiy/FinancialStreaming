package streams.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.*;

public final class StockData {

    private String symbol;
    private String name;
    private String currency;
    private Double price;
    private Double price_open;
    private Double day_high;
    private Double day_low;
    @JsonProperty("52_week_high")
    private Double week_high_52;
    @JsonProperty("52_week_low")
    private Double week_low_52;
    private Double day_change;
    private Double change_pct;
    private Double close_yesterday;
    private Double market_cap;
    private Double volume;
    private Double volume_avg;
    private Double shares;
    private String stock_exchange_long;
    private String stock_exchange_short;
    private String timezone;
    private String timezone_name;
    private Integer gmt_offset;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date last_trade_time;

    public StockData() {
    }

    public StockData(String symbol,
                     String name,
                     String currency,
                     Double price,
                     Double price_open,
                     Double day_high,
                     Double day_low,
                     Double week_high_52,
                     Double week_low_52,
                     Double day_change,
                     Double change_pct,
                     Double close_yesterday,
                     Double market_cap,
                     Double volume,
                     Double volume_avg,
                     Double shares,
                     String stock_exchange_long,
                     String stock_exchange_short,
                     String timezone,
                     String timezone_name,
                     Integer gmt_offset,
                     Date last_trade_time) {
        this.symbol = symbol;
        this.name = name;
        this.currency = currency;
        this.price = price;
        this.price_open = price_open;
        this.day_high = day_high;
        this.day_low = day_low;
        this.week_high_52 = week_high_52;
        this.week_low_52 = week_low_52;
        this.day_change = day_change;
        this.change_pct = change_pct;
        this.close_yesterday = close_yesterday;
        this.market_cap = market_cap;
        this.volume = volume;
        this.volume_avg = volume_avg;
        this.shares = shares;
        this.stock_exchange_long = stock_exchange_long;
        this.stock_exchange_short = stock_exchange_short;
        this.timezone = timezone;
        this.timezone_name = timezone_name;
        this.gmt_offset = gmt_offset;
        this.last_trade_time = last_trade_time;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice_open() {
        return price_open;
    }

    public void setPrice_open(Double price_open) {
        this.price_open = price_open;
    }

    public Double getDay_high() {
        return day_high;
    }

    public void setDay_high(Double day_high) {
        this.day_high = day_high;
    }

    public Double getDay_low() {
        return day_low;
    }

    public void setDay_low(Double day_low) {
        this.day_low = day_low;
    }

    public Double getWeek_high_52() {
        return week_high_52;
    }

    public void setWeek_high_52(Double week_high_52) {
        this.week_high_52 = week_high_52;
    }

    public Double getWeek_low_52() {
        return week_low_52;
    }

    public void setWeek_low_52(Double week_low_52) {
        this.week_low_52 = week_low_52;
    }

    public Double getDay_change() {
        return day_change;
    }

    public void setDay_change(Double day_change) {
        this.day_change = day_change;
    }

    public Double getChange_pct() {
        return change_pct;
    }

    public void setChange_pct(Double change_pct) {
        this.change_pct = change_pct;
    }

    public Double getClose_yesterday() {
        return close_yesterday;
    }

    public void setClose_yesterday(Double close_yesterday) {
        this.close_yesterday = close_yesterday;
    }

    public Double getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(Double market_cap) {
        this.market_cap = market_cap;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getVolume_avg() {
        return volume_avg;
    }

    public void setVolume_avg(Double volume_avg) {
        this.volume_avg = volume_avg;
    }

    public Double getShares() {
        return shares;
    }

    public void setShares(Double shares) {
        this.shares = shares;
    }

    public String getStock_exchange_long() {
        return stock_exchange_long;
    }

    public void setStock_exchange_long(String stock_exchange_long) {
        this.stock_exchange_long = stock_exchange_long;
    }

    public String getStock_exchange_short() {
        return stock_exchange_short;
    }

    public void setStock_exchange_short(String stock_exchange_short) {
        this.stock_exchange_short = stock_exchange_short;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTimezone_name() {
        return timezone_name;
    }

    public void setTimezone_name(String timezone_name) {
        this.timezone_name = timezone_name;
    }

    public Integer getGmt_offset() {
        return gmt_offset;
    }

    public void setGmt_offset(Integer gmt_offset) {
        this.gmt_offset = gmt_offset;
    }

    public Date getLast_trade_time() {
        return last_trade_time;
    }

    public void setLast_trade_time(Date last_trade_time) {
        this.last_trade_time = last_trade_time;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("***** Stock Details *****\n");
        sb.append("Symbol="+getSymbol()+"\n");
        sb.append("Price="+getPrice()+"\n");
        sb.append("*****************************");
        return sb.toString();
    }

}


