package models;

public class User {

    private final String socialTitle;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String birthDate;
    private final boolean offersFlag;
    private final boolean dataPrivacyFlag;
    private final boolean newsletterFlag;
    private final boolean privacyPolicyFlag;

    private User(String socialTitle, String firstName, String lastName, String email, String password, String birthDate,
                 boolean offersFlag, boolean dataPrivacyFlag, boolean newsletterFlag, boolean privacyPolicyFlag) {
        this.socialTitle = socialTitle;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.offersFlag = offersFlag;
        this.dataPrivacyFlag = dataPrivacyFlag;
        this.newsletterFlag = newsletterFlag;
        this.privacyPolicyFlag = privacyPolicyFlag;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private String socialTitle;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String birthDate;
        private boolean offersFlag;
        private boolean dataPrivacyFlag;
        private boolean newsletterFlag;
        private boolean privacyPolicyFlag;

        public UserBuilder socialTitle(String socialTitle) {
            this.socialTitle = socialTitle;
            return this;
        }

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder birthDate(String birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public UserBuilder offersFlag(boolean offersFlag) {
            this.offersFlag = offersFlag;
            return this;
        }

        public UserBuilder dataPrivacyFlag(boolean dataPrivacyFlag) {
            this.dataPrivacyFlag = dataPrivacyFlag;
            return this;
        }

        public UserBuilder newsletterFlag(boolean newsletterFlag) {
            this.newsletterFlag = newsletterFlag;
            return this;
        }

        public UserBuilder privacyPolicyFlag(boolean privacyPolicyFlag) {
            this.privacyPolicyFlag = privacyPolicyFlag;
            return this;
        }

        public User build() {
            return new User(socialTitle, firstName, lastName, email, password, birthDate, offersFlag, dataPrivacyFlag,
                    newsletterFlag, privacyPolicyFlag);
        }

    }

}
