package nl.kevinberendsen.throne.db.models;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.*;

import javax.persistence.*;

/**
 * Account model
 * Created by nedermail on 25/07/16.
 */
@Entity
@Table(name="mqtt_account")
public class Account extends Model {

    @Id
    public long id;

    @Index(unique = true)
    @Column(nullable = false)
    public String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    public boolean active;

    @Column(length = 20)
    public String name;

    //TODO Add mapping for linked classes.

    /**
     * Creating a new account.
     * @param email must be valid.
     * @param password at least be five characters long and contain a numeric character as well.
     * @param active is either active or not.
     * @param name public name that will be displayed to others.
     */
    public Account(String email, String password, boolean active, String name) {
        // Validating email.
        if(validateEmail(email))
            this.email = email;
        else
            throw new IllegalArgumentException("Invalid email.");

        // Validating password.
        if(validatePassword(password))
            this.password = hashPassword(password);
        else
            throw new IllegalArgumentException("Password doesn't meet the minimum requirements.");


        this.active = active;

        // Checking length constraint of name.
        if(name.length() > 20)
            this.name = name.substring(0, 19);
        else
            this.name = name;
    }

    /**
     * Validates an a email address
     * @param email input email to test whether is valid or not.
     * @return true if is valid otherwise false.
     */
    private boolean validateEmail(String email) {
        //TODO Regex email
        return true;
    }

    /**
     * Validates on a password.
     * @param password input password to test whether it meets the criteria or not
     * @return true if is valid otherwise false.
     */
    private boolean validatePassword(String password) {
        //TODO Regex password
        return true;
    }

    /**
     * Hashes the password and returns the hashed value.
     * @param password input password to hash.
     * @return hashes password in a String.
     */
    private String hashPassword(String password) {
        //TODO Hash password into something. Preferably same algorithm as the website.
        return "";
    }

}
