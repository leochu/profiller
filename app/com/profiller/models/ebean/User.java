package com.profiller.models.ebean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table( name = "prof_user" )
public class User
    extends BaseModel
{
    @Column( unique = true )
    private String email;

    private String firstName;

    private String lastName;

    @JsonIgnore
    @Column( length = 512 )
    private String secret;

    @JsonIgnore
    @Column( length = 512 )
    private String salt;

    @Column( length = 512 )
    private String emailMD5;

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    public String getSecret()
    {
        return secret;
    }

    public void setSecret( String secret )
    {
        this.secret = secret;
    }

    public String getSalt()
    {
        return salt;
    }

    public void setSalt( String salt )
    {
        this.salt = salt;
    }

    public String getEmailMD5()
    {
        return emailMD5;
    }

    public void setEmailMD5( String emailMD5 )
    {
        this.emailMD5 = emailMD5;
    }

}
