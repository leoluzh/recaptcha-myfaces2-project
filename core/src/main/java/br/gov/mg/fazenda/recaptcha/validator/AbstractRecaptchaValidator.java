/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package br.gov.mg.fazenda.recaptcha.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFValidator;
import org.apache.myfaces.commons.validator.ValidatorBase;

import br.gov.mg.fazenda.recaptcha.ReCaptcha;
import br.gov.mg.fazenda.recaptcha.ReCaptchaFactory;
import br.gov.mg.fazenda.recaptcha.ReCaptchaResponse;
import br.gov.mg.fazenda.recaptcha.component.recaptcha.Recaptcha;

/**
 * 
 * @author leonardo luz
 * @version 0.1
 * @since 23/06/2015
 *  
 */
@JSFValidator(
   name = "mycomponents:validateRecaptcha",
   clazz = "br.gov.mg.fazenda.recaptcha.validator.RecaptchaValidator",
   tagClass = "br.gov.mg.fazenda.recaptcha.validator.RecaptchaValidatorTag")
public abstract class AbstractRecaptchaValidator extends ValidatorBase
{
    public static final String VALIDATOR_ID = "br.gov.mg.fazenda.recaptcha.validator.RecaptchaValidator";
    
    public void validate(FacesContext context, UIComponent component, Object value)
            throws ValidatorException
    {
    	
		//get values from parent component
		Recaptcha recaptcha = (Recaptcha)component;
		String siteKey = recaptcha.getDataPublicKey();
		String secretKey = recaptcha.getDataPrivateKey();

		//get values from submit values
		String remoteAddress = context.getExternalContext().getRemoteUser();
		String response = ((HttpServletRequest)context.getExternalContext().getRequest()).getParameter("g-recaptcha-response");

		ReCaptcha reCaptcha = ReCaptchaFactory.newReCaptchaSecure(siteKey, secretKey, false);
		ReCaptchaResponse result = reCaptcha.checkAnswer(response, remoteAddress) ;

		if( !result.isSuccess() ){
			String message  = result.getErrorCodes().get(0);
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,null,message);
			throw new ValidatorException(fm);
		}
    	
    	
    }    
}
