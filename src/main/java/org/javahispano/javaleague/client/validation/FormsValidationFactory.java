/**
 * 
 */
package org.javahispano.javaleague.client.validation;

import javax.validation.Validator;

import org.javahispano.javaleague.client.application.login.Credentials;

import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;

/**
 * @author adou
 *
 */
public class FormsValidationFactory extends AbstractGwtValidatorFactory {

    /**
     * The Interface GwtValidator.
     */
    @GwtValidation(Credentials.class)
    public interface GwtValidator extends Validator {
    }

    @Override
    public AbstractGwtValidator createValidator() {
        return GWT.create(GwtValidator.class);
    }

}