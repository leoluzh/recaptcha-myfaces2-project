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
package br.gov.mg.fazenda.recaptcha.component.example;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Managed bean for the recaptcha page example
 */

@RequestScoped
@SuppressWarnings("serial")
public class RecaptchaBean implements Serializable
{

    private static final Log log = LogFactory.getLog(RecaptchaBean.class);

    private String publicKey;
    private String privateKey;
    private boolean renderRecaptcha;
 
    public RecaptchaBean()
    {
        this.renderRecaptcha = false;
    }

    public void configureRecaptcha(ActionEvent evt)
    {
    	renderRecaptcha = true;

        if (log.isInfoEnabled())
        {
            log.info("The component recaptcha public key: '"+publicKey+"' and private key is '"+privateKey+"'.");
        }
    }



    public String getPublicKey() 
    {
		return publicKey;
	}

	public void setPublicKey(String publicKey) 
	{
		this.publicKey = publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) 
	{
		this.privateKey = privateKey;
	}

	public boolean isRenderRecaptcha()
    {
        return renderRecaptcha;
    }

    public void setRenderGreeting(boolean renderRecaptcha)
    {
        this.renderRecaptcha = renderRecaptcha;
    }

    
    public String submitRecaptcha() {
        return "display_recaptcha";
    }
    

}
