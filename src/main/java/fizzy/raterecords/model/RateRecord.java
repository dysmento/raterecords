package fizzy.raterecords.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RateRecord {
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");

    private final String lastName;
    private final String firstName;
    private final Gender gender;
    private final String favoriteColor;
    private final LocalDate dateOfBirth;

    public RateRecord(String lastName, String firstName, Gender gender, String favoriteColor, LocalDate dateOfBirth) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.favoriteColor = favoriteColor;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        // "name:McFly, Marty gender:MALE color:orange DOB:6/12/1968"
        return "name:" + lastName + ", " + firstName
                + " gender:" + gender
                + " color:" + favoriteColor
                + " DOB:" + dateOfBirth.format(dateFormatter);
    }

    public static class Builder {
        private String lastName;
        private String firstName;
        private Gender gender;
        private String favoriteColor;
        private LocalDate dateOfBirth;

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder withFavoriteColor(String favoriteColor) {
            this.favoriteColor = favoriteColor;
            return this;
        }

        public Builder withDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public RateRecord build() {
            return new RateRecord(lastName, firstName, gender, favoriteColor, dateOfBirth);
        }
    }
}
