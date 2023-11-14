package pl.javastart.utils;

import pl.javastart.exception.TemperatureLimitException;

public interface Overclockable {
    double getTemperature();

    double getMaxTemperature();

    int getClockFrequency();

    void setClockFrequency(int clockFrequency);

    void setTemperature(double temperature);

    default void overclock(int clockFrequencyToAdd) {
        double possibleTemperature = getTemperature() +  getTempNeededForGivenClockFrequency(clockFrequencyToAdd);
        if (isLimitExceeded(getMaxTemperature(),possibleTemperature)){
            //check how much frequency we cqn add
            // add this amount of frequency
            // show message how much frequency added
        }
        setTemperature(getTemperature() +  getTempNeededForGivenClockFrequency(clockFrequencyToAdd));
        double diff = getTemperature() - getMaxTemperature();
        try {
            checkTemperatureLimit(possibleTemperature);
        } catch (TemperatureLimitException e) {
            System.err.println("Limit temperatury przekroczony, więc zostało dodano tylko " + getClockFrequencyNeededForTempGiven(diff) + " taktowania");
        }
        if (getMaxTemperature() > getMaxTemperature()) {
            setClockFrequency((int) (getClockFrequency() + getClockFrequencyNeededForTempGiven(diff)));
        } else {
            setClockFrequency(getClockFrequency() + clockFrequencyToAdd);
        }
    }

    default void checkTemperatureLimit(double possibleTemperature) {
        if (isLimitExceeded(getMaxTemperature(), possibleTemperature)) {
            throw new TemperatureLimitException("Limit temperatury przekroczony");
        }
    }

    private boolean isLimitExceeded(double maxTemperature, double possibleTemperature) {
        return possibleTemperature > maxTemperature;
    }

    double getTempNeededForGivenClockFrequency(int clockFrequencyToAdd);

    double getClockFrequencyNeededForTempGiven(double temperature);
}
