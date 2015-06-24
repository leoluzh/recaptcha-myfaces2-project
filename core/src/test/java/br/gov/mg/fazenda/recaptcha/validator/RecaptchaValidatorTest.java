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

import java.io.StringWriter;

import javax.faces.component.html.HtmlInputText;
import javax.faces.validator.ValidatorException;

import org.apache.myfaces.test.base.AbstractJsfTestCase;
import org.apache.myfaces.test.mock.MockResponseWriter;

public class RecaptchaValidatorTest extends AbstractJsfTestCase
{

    private MockResponseWriter writer ;
    private RecaptchaValidator recaptchaValidator;

    public RecaptchaValidatorTest(String name)
    {
        super(name);
    }

    public void setUp() throws Exception
    {
        super.setUp();

        recaptchaValidator = new RecaptchaValidator();
        writer = new MockResponseWriter(new StringWriter(), null, null);
        facesContext.setResponseWriter(writer);
    }

    public void tearDown() throws Exception
    {
        super.tearDown();
        recaptchaValidator = null;
        writer = null;
    }

    public void testValidator() throws Exception
    {
        boolean noExceptionsThrown = true;
        try 
        {
        	recaptchaValidator.validate(facesContext, new HtmlInputText(), null );
        } 
        catch(ValidatorException ve) 
        {
            noExceptionsThrown = false;
        }
        assertTrue(noExceptionsThrown);
    }
    
    
    
}