package meow.meowfx.internals.Time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Date {

  private LocalDateTime localDateTime;

  public Date() {
    localDateTime = LocalDateTime.now();
  }

  public Date(int year) {
    localDateTime = LocalDateTime.of(year, 1, 1, 0, 0, 0);
  }

  public Date(int year, int month) {
    localDateTime = LocalDateTime.of(year, month, 1, 0, 0, 0);
  }

  public Date(int year, int month, int day) {
    localDateTime = LocalDateTime.of(year, month, day, 0, 0, 0);
  }

  public Date(int year, int month, int day, int hour) {
    localDateTime = LocalDateTime.of(year, month, day, hour, 0, 0);
  }

  public Date(int year, int month, int day, int hour, int minute) {
    localDateTime = LocalDateTime.of(year, month, day, hour, minute, 0);
  }

  public Date(int year, int month, int day, int hour, int minute, int second) {
    localDateTime = LocalDateTime.of(year, month, day, hour, minute, second);
  }

  public String toIsoDateString() {
    return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH));
  }

  public String toDateString() {
    ZonedDateTime now = ZonedDateTime.of(localDateTime, ZoneId.of("GMT"));
    return now.format(DateTimeFormatter.ofPattern("EEE MMM dd yyyy", Locale.ENGLISH));
  }

  public String toUtcString() {
    ZonedDateTime now = ZonedDateTime.of(localDateTime, ZoneId.of("GMT"));
    return now.withZoneSameInstant(ZoneId.of("GMT")).format(DateTimeFormatter.RFC_1123_DATE_TIME);
  }

  public int getYear() {
    return localDateTime.getYear();
  }

  public int getMonth() {
    return localDateTime.getMonthValue();
  }

  public String getMonthName() {
    return localDateTime.getMonth().name().toLowerCase(Locale.ROOT);
  }

  public int getDay() {
    return localDateTime.getDayOfMonth();
  }

  public String getDayName() {
    return localDateTime.getDayOfWeek().name().toLowerCase(Locale.ROOT);
  }

  public int getHour() {
    return localDateTime.getHour();
  }

  public int getMinute() {
    return localDateTime.getMinute();
  }

  public int getSecond() {
    return localDateTime.getSecond();
  }

  public void setYear(int year) {
    localDateTime = LocalDateTime.of(year, getMonth(), getDay(), getHour(), getMinute(), getSecond());
  }

  public void setMonth(int month) {
    localDateTime = LocalDateTime.of(getYear(), month, getDay(), getHour(), getMinute(), getSecond());
  }

  public void setDay(int day) {
    localDateTime = LocalDateTime.of(getYear(), getMonth(), day, getHour(), getMinute(), getSecond());
  }

  public void setHour(int hour) {
    localDateTime = LocalDateTime.of(getYear(), getMonth(), getDay(), hour, getMinute(), getSecond());
  }

  public void setMinute(int minute) {
    localDateTime = LocalDateTime.of(getYear(), getMonth(), getDay(), getHour(), minute, getSecond());
  }

  public void setSecond(int second) {
    localDateTime = LocalDateTime.of(getYear(), getMonth(), getDay(), getHour(), getMinute(), second);
  }

  public void addSeconds(int seconds) {
    localDateTime = localDateTime.plusSeconds(seconds);
  }

  public void addMinutes(int minutes) {
    localDateTime = localDateTime.plusMinutes(minutes);
  }

  public void addHours(int hours) {
    localDateTime = localDateTime.plusHours(hours);
  }

  public void addDays(int days) {
    localDateTime = localDateTime.plusDays(days);
  }

  public void addMonths(int months) {
    localDateTime = localDateTime.plusMonths(months);
  }

  public void addYears(int years) {
    localDateTime = localDateTime.plusYears(years);
  }

  public void subtractSeconds(int seconds) {
    localDateTime = localDateTime.minusSeconds(seconds);
  }

  public void subtractMinutes(int minutes) {
    localDateTime = localDateTime.minusMinutes(minutes);
  }

  public void subtractHours(int hours) {
    localDateTime = localDateTime.minusHours(hours);
  }

  public void subtractDays(int days) {
    localDateTime = localDateTime.minusDays(days);
  }

  public void subtractMonths(int months) {
    localDateTime = localDateTime.minusMonths(months);
  }

  public void subtractYears(int years) {
    localDateTime = localDateTime.minusYears(years);
  }
}