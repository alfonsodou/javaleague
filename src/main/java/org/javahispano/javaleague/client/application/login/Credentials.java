package org.javahispano.javaleague.client.application.login;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 - 2015 GwtBootstrap3
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class Credentials implements Serializable {

    private static final long serialVersionUID = -1626677647077707091L;

    private String password = null;

    private String email = null;

    /** {@inheritDoc} */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }
        Credentials other = (Credentials) obj;
        if (password == null) {
            if (other.password != null) { return false; }
        } else if (!password.equals(other.password)) { return false; }
        if (email == null) {
            if (other.email != null) { return false; }
        } else if (!email.equals(other.email)) { return false; }
        return true;
    }

    /**
     * @return the password
     */
    @NotNull
    @Size(min = 4, max = 12)
    public String getPassword() {
        return password;
    }

    /**
     * @return the email
     */
    @Email
    @Size(min = 4, max = 128)
    public String getEmail() {
        return email;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (password == null ? 0 : password.hashCode());
        result = prime * result + (email == null ? 0 : email.hashCode());
        return result;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Credentials [email=" + email + ", password=" + password + "]";
    }

}
